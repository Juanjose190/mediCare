package controller;

import DTO.MedicalHistoryDTO;
import Interfaces.IMedicalHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    @Autowired
    private IMedicalHistory medicalHistoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalHistoryDTO createMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        return medicalHistoryService.crearHistoriaMedica(medicalHistoryDTO);
    }

    @GetMapping("/patient/{patientId}")
    public MedicalHistoryDTO getMedicalHistoryByPatient(@PathVariable Long patientId) {
        return medicalHistoryService.obtenerHistoriaPorPaciente(patientId);
    }

    @GetMapping("/all")
    public List<MedicalHistoryDTO> getAllMedicalHistories() {
        return medicalHistoryService.obtenerTodasLasHistorias();
    }

    @PutMapping("/update")
    public MedicalHistoryDTO updateMedicalHistory(@RequestBody MedicalHistoryDTO medicalHistoryDTO) {
        return medicalHistoryService.actualizarHistoriaMedica(medicalHistoryDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedicalHistory(@PathVariable Long id) {
        medicalHistoryService.eliminarHistoriaMedica(id);
    }
}
