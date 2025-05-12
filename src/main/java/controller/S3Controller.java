/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author JUAN JOSE
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import service.S3Service;

@RestController
@RequestMapping("/api/s3")
public class S3Controller {

    @Autowired
    private S3Service s3Service;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("filePath") String filePath,
                             @RequestParam("key") String key) {
        return s3Service.uploadFile(filePath, key);
    }

    @GetMapping("/list")
    public List<String> listFiles() {
        return s3Service.listFiles().stream()
                .map(file -> file.key())
                .toList();
    }

    @GetMapping("/download")
    public String downloadFile(@RequestParam("key") String key,
                               @RequestParam("downloadPath") String downloadPath) {
        return s3Service.downloadFile(key, downloadPath);
    }

    @DeleteMapping("/delete")
    public String deleteFile(@RequestParam("key") String key) {
        return s3Service.deleteFile(key);
    }
}
