package com.example.pet_book;

public class DataClass {

    private String uploadCategory;
    private String uploadName;
    private String uploadAgg;
    private String uploadBreeding;
    private String uploadSex;
    private String uploadColor;
    private String uploadNote;
    private String uploadImage;

    public String getUploadCategory() {
        return uploadCategory;
    }

    public String getUploadName() {
        return uploadName;
    }

    public String getUploadAgg() {
        return uploadAgg;
    }

    public String getUploadBreeding() {
        return uploadBreeding;
    }

    public String getUploadSex() {
        return uploadSex;
    }

    public String getUploadColor() {
        return uploadColor;
    }

    public String getUploadNote() {
        return uploadNote;
    }

    public String getUploadImage() {
        return uploadImage;
    }

    public DataClass(String uploadCategory, String uploadName, String uploadAgg, String uploadBreeding, String uploadSex, String uploadColor, String uploadNote, String uploadImage) {
        this.uploadCategory = uploadCategory;
        this.uploadName = uploadName;
        this.uploadAgg = uploadAgg;
        this.uploadBreeding = uploadBreeding;
        this.uploadSex = uploadSex;
        this.uploadColor = uploadColor;
        this.uploadNote = uploadNote;
        this.uploadImage = uploadImage;
    }

    public DataClass() {

    }
}