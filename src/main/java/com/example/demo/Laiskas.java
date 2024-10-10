package com.example.demo;

import java.time.LocalDateTime;

public class Laiskas {
    private int id;
    private String gavejas;
    private String turinys;

    public Laiskas(int id, String gavejas, String turinys) {
        this.id = id;
        this.gavejas = gavejas;
        this.turinys = turinys;
    }

    public Laiskas(String gavejas, String turinys) {
        this.gavejas = gavejas;
        this.turinys = turinys;
    }

    public Laiskas(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGavejas() {
        return gavejas;
    }

    public void setGavejas(String gavejas) {
        this.gavejas = gavejas;
    }

    public String getTurinys() {
        return turinys;
    }

    public void setTurinys(String turinys) {
        this.turinys = turinys;
    }

}
