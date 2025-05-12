/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author JUAN JOSE
 */

@EnableJpaRepositories
public interface DoctorRepository extends JpaRepository <Doctor, Long>{

    public boolean existsByEmail(String email);
    
}
