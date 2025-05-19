/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.DTO;
import com.hospital.hospital.ENUM.NotificationType;
import com.hospital.hospital.model.Notification;
/**
 *
 * @author JUAN JOSE
 */




public class NotificationDTO {
    private String message;
    private NotificationType type;

    public NotificationDTO() {}

    public NotificationDTO(String message, NotificationType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
