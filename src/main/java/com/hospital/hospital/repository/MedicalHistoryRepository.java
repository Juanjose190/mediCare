/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.Optional;
import model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author JUAN JOSE
 */

@EnableJpaRepositories
public interface MedicalHistoryRepository extends JpaRepository <MedicalHistory, Long> {

    public MedicalHistory save(MedicalHistory medicalHistory);

    public Optional<MedicalHistory> findByPatientId(Long patientId);


    
}
