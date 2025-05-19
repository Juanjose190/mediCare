package controller;

import DTO.AppoinmentDTO;
import ENUM.Status;
import Interfaces.IAppoinment;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appoinments")
@CrossOrigin(origins = "*")
public class AppoinmentController {
    
    @Autowired
    private IAppoinment appoinmentService;
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppoinmentDTO>> getCitasPorPaciente(@PathVariable Long patientId) {
        try {
            List<AppoinmentDTO> citas = appoinmentService.obtenerCitasPorPaciente(patientId);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppoinmentDTO>> getCitasPorDoctor(@PathVariable Long doctorId) {
        try {
            List<AppoinmentDTO> citas = appoinmentService.obtenerCitasPorDoctor(doctorId);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/date")
    public ResponseEntity<List<AppoinmentDTO>> getCitasPorFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            List<AppoinmentDTO> citas = appoinmentService.obtenerCitasPorFecha(date);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/date-range")
    public ResponseEntity<List<AppoinmentDTO>> getCitasPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<AppoinmentDTO> citas = appoinmentService.obtenerCitasPorRangoFechas(startDate, endDate);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/doctor/{doctorId}/availability")
    public ResponseEntity<Boolean> checkDoctorDisponibilidad(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            boolean tieneCita = appoinmentService.doctorTieneCitaEnFecha(doctorId, date);
            return new ResponseEntity<>(!tieneCita, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/patient/{patientId}/upcoming")
    public ResponseEntity<List<AppoinmentDTO>> getProximasCitasPaciente(@PathVariable Long patientId) {
        try {
            List<AppoinmentDTO> citas = appoinmentService.obtenerProximasCitasPaciente(patientId);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/doctor/{doctorId}/date-range")
    public ResponseEntity<List<AppoinmentDTO>> getCitasPorDoctorYFecha(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        try {
            List<AppoinmentDTO> citas = appoinmentService.obtenerCitasPorDoctorYFecha(doctorId, startDate, endDate);
            return new ResponseEntity<>(citas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping
    public ResponseEntity<AppoinmentDTO> crearCita(@RequestBody AppoinmentDTO appoinmentDTO) {
        try {
            if (appoinmentDTO.getStatus() == null) {
                appoinmentDTO.setStatus(Status.PENDING.toString());
            }
            
            AppoinmentDTO nuevaCita = appoinmentService.crearCita(appoinmentDTO);
            return new ResponseEntity<>(nuevaCita, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}