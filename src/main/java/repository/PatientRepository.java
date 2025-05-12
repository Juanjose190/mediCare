/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author JUAN JOSE
 */

@EnableJpaRepositories
public  interface PatientRepository extends JpaRepository <Patient, Long>{

    public boolean existByCorreo(String email);


    public boolean existByIdentification(String identification);

    public boolean existsByCorreo(String email);

    public boolean existsByCedula(String identification);

    public boolean existsByIdentification(String identification);
    
}
