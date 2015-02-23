package com.kay.zhihu.entity;

import java.io.Serializable;

/**
 * Created by mac on 15-2-3.
 */
public class News implements Serializable{
    private int id;
    private String title;
    private String image;

    public News() {
    }

    public News(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {

        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
