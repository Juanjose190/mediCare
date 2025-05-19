/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.repository;

import com.hospital.hospital.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author JUAN JOSE
 */

@EnableJpaRepositories
public  interface PatientRepository extends JpaRepository <Patient, Long>{


 public boolean existsByIdentification(String identification);
    public boolean existsByEmail(String email);

  
    
}
