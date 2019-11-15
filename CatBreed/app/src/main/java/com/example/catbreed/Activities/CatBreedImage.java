package com.example.catbreed.Activities;

import com.google.gson.annotations.Expose;


//Class to pull image URL and cat ID
class CatBreedImage {
    @Expose
    private String id;
    @Expose
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}



