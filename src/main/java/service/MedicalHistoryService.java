package service;

import DTO.MedicalHistoryDTO;
import Interfaces.IMedicalHistory;
import model.MedicalHistory;
import model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MedicalHistoryRepository;
import repository.PatientRepository;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.File;
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

    private final String bucketName = "historiasclinicasarq"; 

    @Override
    public MedicalHistoryDTO crearHistoriaMedica(MedicalHistoryDTO medicalHistoryDTO) {
        Optional<Patient> patient = patientRepository.findById(medicalHistoryDTO.getId());
        if (!patient.isPresent()) {
            throw new RuntimeException("Patient not found");
        }

    
        String documentUrl = uploadFileToS3(medicalHistoryDTO.getDocumentUrl());

   
        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setDocumentUrl(documentUrl);
        medicalHistory.setPatient(patient.get());

        medicalHistory = medicalHistoryRepository.save(medicalHistory);

   
        return new MedicalHistoryDTO(medicalHistory.getId(), medicalHistory.getDocumentUrl());
    }

    private String uploadFileToS3(String documentPath) {
        File file = new File(documentPath); 

        s3Client.putObject(PutObjectRequest.builder()
                .bucket(bucketName)
                .key(file.getName()) 
                .build(), RequestBody.fromFile(file));

      
        return "https://" + bucketName + ".s3.amazonaws.com/" + file.getName();
    }

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
}
