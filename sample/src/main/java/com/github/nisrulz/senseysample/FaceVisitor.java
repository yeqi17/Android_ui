package com.github.nisrulz.senseysample;

public class FaceVisitor {

    private String name;
    private String date;
    private int imageId;

    public FaceVisitor(String name, String date, int imageId) {
        this.name = name;
        this.date = date;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
