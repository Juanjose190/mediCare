/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.service;

import com.hospital.hospital.DTO.DoctorDTO;
import com.hospital.hospital.interfaces.IDoctor;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.hospital.hospital.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.hospital.repository.DoctorRepository;

/**
 *
 * @author JUAN JOSE
 */
@Service
public class ServiceDoctor implements IDoctor {
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    
    @Override
    public DoctorDTO registrarDoctor(DoctorDTO doctorDTO){
        
        boolean exists = doctorRepository.existsByEmail(doctorDTO.getEmail());
        
        if(exists){
            throw new RuntimeException("This doctor already exists");
        }
        
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setEmail(doctorDTO.getEmail());
        doctor.setSpeciality(doctorDTO.getSpeciality());
        
        
        doctorRepository.save(doctor);
        return doctorDTO;
    }
    
    
    
    @Override
    public DoctorDTO obtenerDoctorPorId(Long id){
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("This doctor does not exists"));
        
        return new DoctorDTO(
                doctor.getId(),
                doctor.getEmail(),
                doctor.getName(),
                doctor.getSpeciality()
                
        );
    }
    
    
     @Override
    public List<DoctorDTO> listarDoctores() {
        return doctorRepository.findAll()
        .stream()
        .map(doctor -> new DoctorDTO(
            doctor.getId(),
            doctor.getName(),
            doctor.getSpeciality(),
            doctor.getEmail()
        ))
        .collect(Collectors.toList());
}



    public void eliminarDoctorById(Long id){
         doctorRepository.deleteById(id);
    }


}
