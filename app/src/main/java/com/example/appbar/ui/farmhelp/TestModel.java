package com.example.appbar.ui.farmhelp;

public class TestModel {
    public String imageName;

    public String imageURL;

    public TestModel() {
    }

    public TestModel(String imageName, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }
}
