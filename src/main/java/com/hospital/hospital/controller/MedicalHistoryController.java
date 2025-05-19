package com.hospital.hospital.controller;

import com.hospital.hospital.DTO.MedicalHistoryDTO;
import com.hospital.hospital.interfaces.IMedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/medical-history")
@CrossOrigin(origins = "*") 
public class MedicalHistoryController {

    @Autowired
    private IMedicalHistory medicalHistoryService;

    @PostMapping("/{patientId}/upload")
    public ResponseEntity<?> uploadMedicalHistory(@PathVariable Long patientId, 
                                               @RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Recibiendo solicitud para subir archivo para paciente ID: " + patientId);
            System.out.println("Nombre del archivo: " + file.getOriginalFilename());
            System.out.println("Tamaño del archivo: " + file.getSize());
            
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("El archivo está vacío");
            }
            
            MedicalHistoryDTO result = medicalHistoryService.crearHistoriaMedica(patientId, file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getMedicalHistoryByPatient(@PathVariable Long patientId) {
        try {
            MedicalHistoryDTO history = medicalHistoryService.obtenerHistoriaPorPaciente(patientId);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicalHistoryDTO>> getAllMedicalHistories() {
        List<MedicalHistoryDTO> histories = medicalHistoryService.obtenerTodasLasHistorias();
        return ResponseEntity.ok(histories);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        try {
            MedicalHistoryDTO updated = medicalHistoryService.actualizarHistoriaMedica(medicalHistoryDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedicalHistory(@PathVariable Long id) {
        try {
            medicalHistoryService.eliminarHistoriaMedica(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}