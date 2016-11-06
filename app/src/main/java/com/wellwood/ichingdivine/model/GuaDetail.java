package com.wellwood.ichingdivine.model;

import android.graphics.Bitmap;

public class GuaDetail {
    String content;
    Bitmap icon;
    int id;
    String title;

    public GuaDetail(int id, String title, Bitmap icon, String content) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.content = content;
    }

    public GuaDetail() {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.content = content;
    }



    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
