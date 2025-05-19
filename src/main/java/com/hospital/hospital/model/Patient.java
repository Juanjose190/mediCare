package com.hospital.hospital.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author JUAN JOSE
 */


@Entity 
@Table(name = "patients")
public class Patient {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    private String name;
    private int age; 
    private String email; 
    private String adress;
    private String phone; 
    private String identification; 
    
    @OneToMany(mappedBy ="patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appoinment> appoinments;
    
    
    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)  // Añadido cascade
    private MedicalHistory medicalHistory; 
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)  // Añadida relación con Notification
    private List<Notification> notifications;
    
    
    public Patient(){
    
    }; 

    
   
    public Patient(Long id, String name, int age, String email, String adress, String phone, List<Appoinment> appoinments, MedicalHistory medicalHistory, String identification) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
        this.appoinments = appoinments;
        this.medicalHistory = medicalHistory;
        this.identification = identification;
    }
    
    
    
    //get and set

    public Long getId() {  
        return id;
    }

    public void setId(Long id) {  
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Appoinment> getAppoinments() {
        return appoinments;
    }

    public void setAppoinments(List<Appoinment> appoinments) {
        this.appoinments = appoinments;
    }

    public MedicalHistory getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(MedicalHistory medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
    
    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    
    
}