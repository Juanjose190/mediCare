/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author JUAN JOSE
 */
public class MedicalHistoryDTO {
    
    private Long id; 
    private String documentUrl; 

    
    
    
    public MedicalHistoryDTO(Long id, String documentUrl) {
        this.id = id;
        this.documentUrl = documentUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }
    
    
    
    
}
