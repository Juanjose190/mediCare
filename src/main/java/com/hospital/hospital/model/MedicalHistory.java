package com.hospital.hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 *
 * @author JUAN JOSE
 */
@Entity
@Table(name="medical_history")
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    
    private String documentUrl; 
    
    
    @OneToOne
    @JoinColumn(name = "patient_id")  
    private Patient patient; 
    
    

    public MedicalHistory(){
    };
    
    
    
   //constructir 
    public MedicalHistory(Long id, String documentUrl, Patient patient) {
        this.id = id;
        this.documentUrl = documentUrl;
        this.patient = patient;
    }
    
    
    //get and set

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
}