/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.interfaces;

import com.hospital.hospital.DTO.MedicalHistoryDTO;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author JUAN JOSE
 */
public interface IMedicalHistory {
    
 

    MedicalHistoryDTO obtenerHistoriaPorPaciente(Long patientId);

    List<MedicalHistoryDTO> obtenerTodasLasHistorias();

    MedicalHistoryDTO actualizarHistoriaMedica(MedicalHistoryDTO medicalHistoryDTO);

    void eliminarHistoriaMedica(Long id);

    public MedicalHistoryDTO getMedicalHistoryByPatient(Long patientId);
    
    MedicalHistoryDTO crearHistoriaMedica(Long patientId, MultipartFile file);
}
