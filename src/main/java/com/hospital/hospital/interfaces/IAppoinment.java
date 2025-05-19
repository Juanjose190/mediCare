/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.interfaces;

import com.hospital.hospital.DTO.AppoinmentDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author JUAN JOSE
 */
public interface IAppoinment {
    
     
    List<AppoinmentDTO> obtenerCitasPorPaciente(Long patientId);
    
    List<AppoinmentDTO> obtenerCitasPorDoctor(Long doctorId);
    
    List<AppoinmentDTO> obtenerCitasPorFecha(LocalDate date);

    List<AppoinmentDTO> obtenerCitasPorRangoFechas(LocalDate startDate, LocalDate endDate);
    
    boolean doctorTieneCitaEnFecha(Long doctorId, LocalDate date);
    
    List<AppoinmentDTO> obtenerProximasCitasPaciente(Long patientId);
    
    List<AppoinmentDTO> obtenerCitasPorDoctorYFecha(Long doctorId, LocalDate start, LocalDate end);
    
    AppoinmentDTO crearCita(AppoinmentDTO appoinmentDTO);
}
