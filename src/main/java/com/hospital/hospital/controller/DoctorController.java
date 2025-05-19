/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DTO.DoctorDTO;
import Interfaces.IDoctor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JUAN JOSE
 */

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private IDoctor doctorService;

    @PostMapping
    public DoctorDTO registrarDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.registrarDoctor(doctorDTO);
    }

    @GetMapping("/{id}")
    public DoctorDTO obtenerDoctorPorId(@PathVariable Long id) {
        return doctorService.obtenerDoctorPorId(id);
    }

    @GetMapping
    public List<DoctorDTO> listarDoctores() {
        return doctorService.listarDoctores();
    }

    @DeleteMapping("/{id}")
    public void eliminarDoctor(@PathVariable Long id) {
        doctorService.eliminarDoctorById(id);
    }
}