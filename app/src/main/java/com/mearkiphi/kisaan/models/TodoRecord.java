package com.mearkiphi.kisaan.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jaison on 23/01/17.
 */

public class TodoRecord {

    @SerializedName("id")
    Integer id;

    @SerializedName("title")
    String title;

    @SerializedName("user_id")
    Integer userId;

    @SerializedName("completed")
    Boolean completed;

    @SerializedName("category")
    String category;

    @SerializedName("type")
    String type;

    @SerializedName("item_name")
    String itemName;

    @SerializedName("location")
    String location;

    @SerializedName("rate")
    Integer rate;

    @SerializedName("sub_category")
    String subCategory;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    String image;



    public TodoRecord(String location, Integer userId, Integer rate, String category, String subCategory, String image) {
        this.location = location;
        this.userId = userId;
        this.rate = rate;
        this.category = category;
        this.subCategory = subCategory;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }
}
