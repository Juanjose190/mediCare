/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import DTO.MedicalHistoryDTO;
import java.util.List;

/**
 *
 * @author JUAN JOSE
 */
public interface IMedicalHistory {
    
 
    MedicalHistoryDTO crearHistoriaMedica(MedicalHistoryDTO medicalHistoryDTO);

    MedicalHistoryDTO obtenerHistoriaPorPaciente(Long patientId);

    List<MedicalHistoryDTO> obtenerTodasLasHistorias();

    MedicalHistoryDTO actualizarHistoriaMedica(MedicalHistoryDTO medicalHistoryDTO);

    void eliminarHistoriaMedica(Long id);

    public MedicalHistoryDTO getMedicalHistoryByPatient(Long patientId);
}
