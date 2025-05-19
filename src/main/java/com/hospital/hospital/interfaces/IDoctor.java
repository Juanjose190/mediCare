/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import DTO.DoctorDTO;
import java.util.List;
import model.Doctor;

/**
 *
 * @author JUAN JOSE
 */
public interface IDoctor {
    
    DoctorDTO registrarDoctor(DoctorDTO doctorDTO);
    DoctorDTO obtenerDoctorPorId(Long id);
    List<DoctorDTO> listarDoctores();

    public void eliminarDoctorById(Long id);
}
