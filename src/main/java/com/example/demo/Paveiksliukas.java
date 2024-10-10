package com.example.demo;

public class Paveiksliukas {

    private int id;
    private byte[] base64Image;
    private String url;

    public Paveiksliukas(int id, byte[] base64Image, String url) {
        this.id = id;
        this.base64Image = base64Image;
        this.url = url;
    }

    public Paveiksliukas(byte[] base64Image, String url) {
        this.base64Image = base64Image;
        this.url = url;
    }

    public Paveiksliukas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(byte[] base64Image) {
        this.base64Image = base64Image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
