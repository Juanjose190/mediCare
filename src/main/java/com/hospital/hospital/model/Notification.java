package com.hospital.hospital.model;

import jakarta.persistence.Entity;
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
@Table(name ="notifiations")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    
    private String message; 
    private LocalDateTime timeStap; 
  
    @ManyToOne 
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    
    
    public Notification(){
    }; 

    
    
    
    
    public Notification(Long id, String message, LocalDateTime timeStap, Patient patient) {
        this.id = id;
        this.message = message;
        this.timeStap = timeStap;
        this.patient = patient;
    }
    
    
    
    //get and set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStap() {
        return timeStap;
    }

    public void setTimeStap(LocalDateTime timeStap) {
        this.timeStap = timeStap;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
}