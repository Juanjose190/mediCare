/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import ENUM.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

/**
 *
 * @author JUAN JOSE
 */
@Entity
@Table(name = "appoinments")
public class Appoinment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    
    private LocalDateTime date; 
    
    
    @Enumerated(EnumType.STRING)
    private Status status; 
    
    
    
    

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    
    private String reason;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient; 
    
    
    
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; 
    
    
    public Appoinment(){
    }; 

    
    
    public Appoinment(Long id, LocalDateTime date, Status status, Patient patient, Doctor doctor) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.patient = patient;
        this.doctor = doctor;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    
    
    
}
