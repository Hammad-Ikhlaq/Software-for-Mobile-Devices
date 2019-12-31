package com.example.muhammadfakhar.pro;

public class Cuisines {
    private String Name;
    private String Image;

    public Cuisines() {
    }

    public Cuisines(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
