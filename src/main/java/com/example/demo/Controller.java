package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Controller {

    @Autowired
    private Service service;

    @Autowired
    private RabbitMQService rabbitMQService;


    @PostMapping("/laiskasybaze")
    public String laiskasYDuombaze(@RequestBody Laiskas l) throws Exception {
        rabbitMQService.sendObjectToQueue(l);
        return "Message sent to queue!";
    }

    @PostMapping("/paveiksliukasybaze")
    public String paveiksliukasYDuombaze(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return "File is missing!";
        }
        byte[] fileBytes = file.getBytes();
        String base64Image = java.util.Base64.getEncoder().encodeToString(fileBytes);
        rabbitMQService.sendObjectToQueue(base64Image);

        return "Base64 image sent to queue!";
    }
}
