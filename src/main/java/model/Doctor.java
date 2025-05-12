/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

/**
 *
 * @author JUAN JOSE
 */
@Entity
@Table(name ="doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    
    private String name; 
    private String speciality; 
    private String email; 
    
    
    @OneToMany(mappedBy = "appoinment")
    private List<Appoinment> appoinments;
    
    
    public Doctor(){
    };

    public Doctor(Long id, String name, String speciality, String email, List<Appoinment> appoinments) {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.email = email;
        this.appoinments = appoinments;
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

    public List<Appoinment> getAppoinments() {
        return appoinments;
    }

    public void setAppoinments(List<Appoinment> appoinments) {
        this.appoinments = appoinments;
    }
    
    
}
