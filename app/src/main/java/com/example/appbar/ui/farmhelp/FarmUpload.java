package com.example.appbar.ui.farmhelp;

public class FarmUpload {
    public String imageName;
    public String imageURL;

    public FarmUpload(){

    }

    public FarmUpload(String name, String url) {
        this.imageName = name;
        this.imageURL = url;
    }

    public String getImageName() {
        return imageName;
    }
    public String getImageURL() {
        return imageURL;
    }
}
