package com.example.shopcenter.model;

public class ImageCategory {

    private int mId;
    private String mName;
    private String mSrc;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSrc() {
        return mSrc;
    }

    public void setSrc(String src) {
        mSrc = src;
    }

    public ImageCategory() {
    }

    public ImageCategory(int id, String name, String src) {
        mId = id;
        mName = name;
        mSrc = src;
    }
}
