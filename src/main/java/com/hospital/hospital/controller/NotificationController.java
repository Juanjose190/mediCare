/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.controller;

import com.hospital.hospital.DTO.NotificationDTO;
import com.hospital.hospital.interfaces.NotificationObserver;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hospital.hospital.service.NotificationService;

/**
 *
 * @author JUAN JOSE
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController implements NotificationObserver {

    @Autowired
    private NotificationService notificationService;

    @PostConstruct
    public void init() {
        notificationService.subscribe(this);
    }

    @PostMapping("/send")
    public String sendNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.sendNotification(notificationDTO.getMessage(), notificationDTO.getType());
        return "Notification sent";
    }

    @Override
    public void notify(NotificationDTO notification) {
        System.out.println("Received notification: " + notification.getType() + " - " + notification.getMessage());
    }
}