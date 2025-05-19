/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.service;

import com.hospital.hospital.DTO.PatientDTO;
import com.hospital.hospital.interfaces.IPatient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.hospital.hospital.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.hospital.repository.PatientRepository;

/**
 *
 * @author JUAN JOSE
 */
@Service 
public class PatientService implements IPatient{
    @Autowired
    private PatientRepository patientRepository; 
    
    @Override 
    public PatientDTO registrarPaciente(PatientDTO dto){
        
        boolean exist = patientRepository.existsByEmail(dto.getEmail()) ||
                patientRepository.existsByIdentification(dto.getIdentification());
        
        if(exist){
            throw new RuntimeException("This patient already exists");
        }
        
        
        Patient patient = new Patient(); 
        patient.setName(dto.getName()); 
        patient.setEmail(dto.getEmail());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());
        
        
        patientRepository.save(patient); 
        return dto; 
        
                
    }
    
    @Override
    public PatientDTO obtenerPacientePorId(Long id){
      Patient patient = patientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("This patient does not exist"));
  
        return new PatientDTO(
        patient.getId(),
        patient.getName(),
        patient.getAge(),
        patient.getEmail(),
        patient.getAdress(),
        patient.getPhone(),
        patient.getIdentification()
    );
    }
    
    
    @Override
    public List<PatientDTO> listarPacientes(){
        return patientRepository.findAll()
                .stream()
                .map(patient -> new PatientDTO(
              patient.getId(),
             patient.getName(),
              patient.getAge(),
            patient.getEmail(),
            patient.getAdress(),
            patient.getPhone(),
      patient.getIdentification()
            ))
            .collect(Collectors.toList());
    }

    
   
public void eliminarPacientePorId(Long id) {
    if (!patientRepository.existsById(id)) {
        throw new RuntimeException("Patient not found");
    }
    patientRepository.deleteById(id);
}

    
    
        
    

}
