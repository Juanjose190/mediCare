/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hospital.hospital.interfaces;

/**
 *
 * @author JUAN JOSE
 */

import com.hospital.hospital.DTO.NotificationDTO;

public interface NotificationObserver {
    void notify(NotificationDTO notification);
}
