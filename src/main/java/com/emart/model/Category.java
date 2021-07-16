package com.emart.model;

public class Category {
    private String name;
    private int id;
    private String imgUrl;

    public Category() {
    }

    public Category(String name, int id, String imgUrl) {
        this.name = name;
        this.id = id;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
