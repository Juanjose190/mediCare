/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.controller;

import com.hospital.hospital.DTO.PatientDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hospital.hospital.service.PatientService;

/**
 *
 * @author JUAN JOSE
 */

@RestController
@RequestMapping("/api/pacientes")
public class PatientController {
    
    @Autowired
    private PatientService patientService;
    
    
    
     @PostMapping
    public PatientDTO registrarPaciente(@RequestBody PatientDTO patientDTO) {
        return patientService.registrarPaciente(patientDTO);
    }
    
    
    @GetMapping("/{id}")
    public PatientDTO obtenerPacientePorId(@PathVariable Long id) {
        return patientService.obtenerPacientePorId(id);
    }

    
     @GetMapping
    public List<PatientDTO> listarPacientes() {
        return patientService.listarPacientes();
    }
    
    
    
    @DeleteMapping("/{id}")
    public void eliminarPacientePorId(@PathVariable Long id) {
        patientService.eliminarPacientePorId(id);
    }
}
