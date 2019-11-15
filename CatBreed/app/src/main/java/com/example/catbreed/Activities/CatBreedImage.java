package com.example.catbreed.Activities;

import com.example.catbreed.model.Cat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class CatBreedImage {
    //@SerializedName("breeds")
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



