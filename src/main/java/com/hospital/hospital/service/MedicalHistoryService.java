package com.hospital.hospital.service;

import com.hospital.hospital.DTO.MedicalHistoryDTO;
import com.hospital.hospital.interfaces.IMedicalHistory;
import com.hospital.hospital.model.MedicalHistory;
import com.hospital.hospital.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hospital.hospital.repository.MedicalHistoryRepository;
import com.hospital.hospital.repository.PatientRepository;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalHistoryService implements IMedicalHistory {

    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private S3Client s3Client;
    
    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;


    @Override
    public MedicalHistoryDTO obtenerHistoriaPorPaciente(Long patientId) {
        Optional<MedicalHistory> medicalHistory = medicalHistoryRepository.findByPatientId(patientId);
        if (!medicalHistory.isPresent()) {
            throw new RuntimeException("Medical history not found for patient " + patientId);
        }
        MedicalHistory history = medicalHistory.get();
        return new MedicalHistoryDTO(history.getId(), history.getDocumentUrl());
    }

    @Override
    public List<MedicalHistoryDTO> obtenerTodasLasHistorias() {
        List<MedicalHistory> histories = medicalHistoryRepository.findAll(); 
        return histories.stream()
                        .map(history -> new MedicalHistoryDTO(history.getId(), history.getDocumentUrl()))
                        .collect(Collectors.toList());
    }

    @Override
    public MedicalHistoryDTO actualizarHistoriaMedica(MedicalHistoryDTO medicalHistoryDTO) {
        Optional<MedicalHistory> existingHistory = medicalHistoryRepository.findById(medicalHistoryDTO.getId());
        if (!existingHistory.isPresent()) {
            throw new RuntimeException("Medical history not found");
        }

        MedicalHistory history = existingHistory.get();
        history.setDocumentUrl(medicalHistoryDTO.getDocumentUrl());

        medicalHistoryRepository.save(history);

        return new MedicalHistoryDTO(history.getId(), history.getDocumentUrl());
    }

    @Override
    public void eliminarHistoriaMedica(Long id) {
        medicalHistoryRepository.deleteById(id);
    }

    @Override
    public MedicalHistoryDTO getMedicalHistoryByPatient(Long patientId) {
        return obtenerHistoriaPorPaciente(patientId);
    }
    
    @Override
    public MedicalHistoryDTO crearHistoriaMedica(Long patientId, MultipartFile file) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));
        
        try {
        
            String fileName = "medical_history_" + patientId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            
        
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .contentType(file.getContentType())
                    .build();
            
      
            System.out.println("Intentando subir archivo: " + fileName);
            System.out.println("Bucket: " + bucketName);
            System.out.println("Content Type: " + file.getContentType());
            System.out.println("File Size: " + file.getSize());
            
            try {
           
                PutObjectResponse response = s3Client.putObject(
                    putObjectRequest, 
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
                );
                
           
                System.out.println("Archivo subido exitosamente. ETag: " + response.eTag());
            } catch (S3Exception e) {
                System.err.println("Error de S3: " + e.getMessage());
                System.err.println("Código de error: " + e.awsErrorDetails().errorCode());
                System.err.println("Mensaje de error: " + e.awsErrorDetails().errorMessage());
                throw new RuntimeException("Error al subir a S3: " + e.getMessage(), e);
            }
            
         
            String documentUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", 
                                              bucketName, region, fileName);
            
            MedicalHistory medicalHistory;
            Optional<MedicalHistory> existingHistory = medicalHistoryRepository.findByPatientId(patientId);
            
            if (existingHistory.isPresent()) {
                medicalHistory = existingHistory.get();
                medicalHistory.setDocumentUrl(documentUrl);
            } else {
                medicalHistory = new MedicalHistory();
                medicalHistory.setPatient(patient);
                medicalHistory.setDocumentUrl(documentUrl);
            }
            
            MedicalHistory savedHistory = medicalHistoryRepository.save(medicalHistory);
            return new MedicalHistoryDTO(savedHistory.getId(), savedHistory.getDocumentUrl());
        } catch (IOException e) {
            System.err.println("Error de IO: " + e.getMessage());
            throw new RuntimeException("Error procesando el archivo: " + e.getMessage(), e);
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al procesar la historia médica: " + e.getMessage(), e);
        }
    }
}