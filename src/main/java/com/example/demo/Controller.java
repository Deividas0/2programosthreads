package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class Controller {

    @Autowired
    private Service service;


    @PostMapping("/laiskasybaze")
    public String laiskasYDuombaze(@RequestBody Laiskas l) throws SQLException {
        service.laiskasYDuombaze(l.getGavejas(), l.getTurinys());
        return "Success";
    }

    @PostMapping("/paveiksliukasybaze")
    public String paveiksliukasYDuombaze(@RequestBody byte[] base64Image) throws SQLException {
        service.paveiksliukasYDuombaze(base64Image);
        return "Success";
    }
}
