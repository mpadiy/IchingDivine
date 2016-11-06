package com.wellwood.ichingdivine.model;

/**
 * Created by waterwoodwell on 2016/6/16.
 */
public class BookInfo {
    String mBookname;
    String mAuthor;
    public BookInfo(String name, String author) {
        mBookname = name;
        mAuthor = author;
    }


    public String getName() {
        return this.mBookname;
    }

    public void setName(String name) {
        this.mBookname = name;
    }

    public String getAuthor() {
        return this.mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }









}
