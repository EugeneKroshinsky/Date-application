package com.example.DateApplication.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class FileStorageService {

    public String storeFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        File uploadedFile = new File(System.getProperty("user.dir") + "/src/main/resources/static/images/" + file.getOriginalFilename());
        file.transferTo(uploadedFile);
        return "/images/" + file.getOriginalFilename();
    }
}
