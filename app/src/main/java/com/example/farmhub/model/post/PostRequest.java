package com.example.farmhub.model.post;

import java.io.File;

public class PostRequest {

    private String description;
    private File image;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
