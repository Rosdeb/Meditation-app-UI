package com.example.test.Models;

public class Models1 {
    String titile;
    int image;

    public Models1(String titile, int image) {
        this.titile = titile;
        this.image = image;
    }

    public String getTitile() {
        return titile;
    }

    public int getImage() {
        return image;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
