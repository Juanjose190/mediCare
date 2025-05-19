/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.interfaces;

import com.hospital.hospital.DTO.PatientDTO;
import java.util.List;

/**
 *
 * @author JUAN JOSE
 */
public interface IPatient {
    PatientDTO registrarPaciente(PatientDTO dto); 
    PatientDTO obtenerPacientePorId(Long id);
    List<PatientDTO> listarPacientes();
}
