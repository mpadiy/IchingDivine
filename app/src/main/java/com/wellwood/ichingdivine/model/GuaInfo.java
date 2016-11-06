package com.wellwood.ichingdivine.model;

import android.graphics.Bitmap;

public class GuaInfo {
    Bitmap icon;
    int id;
    String msg;
    String name;

    public GuaInfo(int id, String name, Bitmap icon, String msg) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.msg = msg;
    }
    public GuaInfo() {       // mo args constructor
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.msg = msg;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getIcon() {
        return this.icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
