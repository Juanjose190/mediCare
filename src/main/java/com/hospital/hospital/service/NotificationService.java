/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DTO.NotificationDTO;
import ENUM.NotificationType;
import Interfaces.NotificationObserver;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author JUAN JOSE
 */

@Service
public class NotificationService {
    
     private final List<NotificationObserver> observers = new ArrayList<>();

    public void subscribe(NotificationObserver observer) {
        observers.add(observer);
    }

    public void unsubscribe(NotificationObserver observer) {
        observers.remove(observer);
    }

    public void sendNotification(String message, NotificationType type) {
        NotificationDTO notification = new NotificationDTO(message, type);
        for (NotificationObserver observer : observers) {
            observer.notify(notification);
        }
    }
}
