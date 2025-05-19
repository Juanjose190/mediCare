package com.hospital.hospital.service;

import com.hospital.hospital.DTO.AppoinmentDTO;
import com.hospital.hospital.ENUM.Status;
import com.hospital.hospital.interfaces.IAppoinment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.hospital.hospital.model.Appoinment;
import com.hospital.hospital.model.Doctor;
import com.hospital.hospital.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hospital.hospital.repository.AppoinmentRepository;
import com.hospital.hospital.repository.DoctorRepository;
import com.hospital.hospital.repository.PatientRepository;

@Service
public class AppoinmentService implements IAppoinment {
    
    @Autowired
    private AppoinmentRepository appoinmentRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    
    
    private AppoinmentDTO convertirADTO(Appoinment appoinment) {
        return new AppoinmentDTO(
                appoinment.getId(),
                appoinment.getDate(),
                appoinment.getStatus().toString(),
                Long.valueOf(appoinment.getPatient().getId()),
                Long.valueOf(appoinment.getDoctor().getId()),
                appoinment.getReason()
        );
    }
    
    
    
    
    
    private Appoinment convertirAEntidad(AppoinmentDTO appoinmentDTO) {
        Appoinment appoinment = new Appoinment();
        
        if (appoinmentDTO.getId() != null) {
            appoinment.setId(appoinmentDTO.getId());
        }
        
        appoinment.setDate(appoinmentDTO.getDate());
        appoinment.setStatus(Status.valueOf(appoinmentDTO.getStatus()));
        
        Optional<Patient> paciente = patientRepository.findById(appoinmentDTO.getPatientId());
        if (paciente.isPresent()) {
            appoinment.setPatient(paciente.get());
        } else {
            throw new RuntimeException("Patient not found with ID: " + appoinmentDTO.getPatientId());
        }
        
        Optional<Doctor> doctor = doctorRepository.findById(appoinmentDTO.getDoctorId());
        if (doctor.isPresent()) {
            appoinment.setDoctor(doctor.get());
        } else {
            throw new RuntimeException("Doctor not found with ID: " + appoinmentDTO.getDoctorId());
        }
        
        String reason = appoinmentDTO.getReason();
        if (reason != null) {
            appoinment.setReason(reason);
        }
        
        return appoinment;
    }
    
    
    
    
    

    @Override
    public List<AppoinmentDTO> obtenerCitasPorPaciente(Long patientId) {
        List<Appoinment> citas = appoinmentRepository.findByPatientId(patientId);
        if (citas == null) {
            return new ArrayList<>();
        }
        return citas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    
    
    
    @Override
    public List<AppoinmentDTO> obtenerCitasPorDoctor(Long doctorId) {
        List<Appoinment> citas = appoinmentRepository.findByDoctorId(doctorId);
        if (citas == null) {
            return new ArrayList<>();
        }
        return citas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    
    
    
    
    @Override
    public List<AppoinmentDTO> obtenerCitasPorFecha(LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(LocalTime.MAX);
        
        List<Appoinment> citas = appoinmentRepository.findByDateBetween(startDateTime, endDateTime);
        if (citas == null) {
            return new ArrayList<>();
        }
        return citas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    
    
    

    @Override
    public List<AppoinmentDTO> obtenerCitasPorRangoFechas(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);
        
        List<Appoinment> citas = appoinmentRepository.findByDateBetween(startDateTime, endDateTime);
        if (citas == null) {
            return new ArrayList<>();
        }
        return citas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    
    
    
    

    @Override
    public boolean doctorTieneCitaEnFecha(Long doctorId, LocalDate date) {
        LocalDateTime startDateTime = date.atStartOfDay();
        LocalDateTime endDateTime = date.atTime(LocalTime.MAX);
        
        try {
            return appoinmentRepository.existsByDoctorIdAndDateBetweenAndStatusNot(
                    doctorId, 
                    startDateTime, 
                    endDateTime, 
                    Status.CANCELED
            );
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    
    

    @Override
    public List<AppoinmentDTO> obtenerProximasCitasPaciente(Long patientId) {
        LocalDateTime now = LocalDateTime.now();
        List<Appoinment> citas = appoinmentRepository.findByPatientIdAndDateAfterAndStatusNot(
                patientId, 
                now, 
                Status.CANCELED
        );
        
        if (citas == null) {
            return new ArrayList<>();
        }
        return citas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    
    
    
    

    @Override
    public List<AppoinmentDTO> obtenerCitasPorDoctorYFecha(Long doctorId, LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);
        
        List<Appoinment> citas = appoinmentRepository.findByDoctorIdAndDateBetween(
                doctorId, 
                startDateTime, 
                endDateTime
        );
        
        if (citas == null) {
            return new ArrayList<>();
        }
        return citas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }
    
    
    
    
    
    
    

    @Override
    public AppoinmentDTO crearCita(AppoinmentDTO appoinmentDTO) {
        if (appoinmentDTO == null || appoinmentDTO.getDate() == null || 
            appoinmentDTO.getDoctorId() == null || appoinmentDTO.getPatientId() == null) {
            throw new IllegalArgumentException("Incomplete appointment data");
        }
    
        try {
            if (doctorTieneCitaEnFecha(appoinmentDTO.getDoctorId(), appoinmentDTO.getDate().toLocalDate())) {
                throw new RuntimeException("The doctor already has an appointment scheduled for that date.");
            }
        } catch (Exception e) {
        }
        
        Appoinment appoinment = convertirAEntidad(appoinmentDTO);
        
        Appoinment citaGuardada = appoinmentRepository.save(appoinment);
        
        return convertirADTO(citaGuardada);
    }
}