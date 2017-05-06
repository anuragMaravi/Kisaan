package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 23/01/17.
 */

public class TodoRecord2 {

    @SerializedName("id")
    Integer id;

    @SerializedName("category")
    String title;

    @SerializedName("image")
    String image;


    public TodoRecord2(String title, String image) {
        this.title = title;
        this.image = image;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}