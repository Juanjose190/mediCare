/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.DTO;

/**
 *
 * @author JUAN JOSE
 */
public class PatientDTO {
    
    private Long id;
    private String name;
    private int age; 
    private String email; 
    private String adress;
    private String phone; 
    private String identification; 
    
    
    public PatientDTO() {
   
}
    

    public PatientDTO(Long id, String name, int age, String email, String adress, String phone, String identification) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.adress = adress;
        this.phone = phone;
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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }
    
}
