package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private Repository repository;

    public void laiskasYDuombaze(String gavejas, String turinys) throws SQLException {
        repository.laiskasYDuombaze(gavejas, turinys);
    }
    public void paveiksliukasYDuombaze(byte[] base64Image) throws SQLException {
        repository.paveiksliukasYDuombaze(base64Image);
    }
}
