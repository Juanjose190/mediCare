/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.DTO;
import java.time.LocalDateTime;
/**
 *
 * @author JUAN JOSE
 */
public class AppoinmentDTO {
    
    private Long id;
    private LocalDateTime date;
    private String status;  
    private Long patientId;
    private Long doctorId;
    private String reason;  
    
    
    public AppoinmentDTO(Long id, LocalDateTime date, String status, Long patientId, Long doctorId) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }
    
  
    public AppoinmentDTO(Long id, LocalDateTime date, String status, Long patientId, Long doctorId, String reason) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.reason = reason;
    }
    
    //get and set
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Long getPatientId() {
        return patientId;
    }
    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
    public Long getDoctorId() {
        return doctorId;
    }
    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
}