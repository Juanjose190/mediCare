/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.DTO;

/**
 *
 * @author JUAN JOSE
 */
public class DoctorDTO {
    private Long id; 
    private String name; 
    private String speciality; 
    private String email; 
    
    
    
    
    public DoctorDTO(){
    };
    

    public DoctorDTO(Long id, String name, String speciality, String email) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.email = email;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
