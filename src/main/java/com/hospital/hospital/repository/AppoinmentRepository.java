package com.hospital.hospital.repository;

import com.hospital.hospital.ENUM.Status;
import java.time.LocalDateTime;
import java.util.List;
import com.hospital.hospital.model.Appoinment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



public interface AppoinmentRepository extends JpaRepository<Appoinment, Long> {
    
    @Query("SELECT a FROM Appoinment a WHERE a.patient.id = :patientId")
    List<Appoinment> findByPatientId(@Param("patientId") Long patientId);
    
    @Query("SELECT a FROM Appoinment a WHERE a.doctor.id = :doctorId")
    List<Appoinment> findByDoctorId(@Param("doctorId") Long doctorId);
    
    @Query("SELECT a FROM Appoinment a WHERE a.date BETWEEN :startDate AND :endDate")
    List<Appoinment> findByDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT COUNT(a) > 0 FROM Appoinment a WHERE a.doctor.id = :doctorId AND a.date BETWEEN :startDate AND :endDate AND a.status != :status")
    boolean existsByDoctorIdAndDateBetweenAndStatusNot(@Param("doctorId") Long doctorId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("status") Status status);
    
    @Query("SELECT a FROM Appoinment a WHERE a.patient.id = :patientId AND a.date > :dateTime AND a.status != :status")
    List<Appoinment> findByPatientIdAndDateAfterAndStatusNot(@Param("patientId") Long patientId, @Param("dateTime") LocalDateTime dateTime, @Param("status") Status status);
    
    @Query("SELECT a FROM Appoinment a WHERE a.doctor.id = :doctorId AND a.date BETWEEN :startDate AND :endDate")
    List<Appoinment> findByDoctorIdAndDateBetween(@Param("doctorId") Long doctorId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}