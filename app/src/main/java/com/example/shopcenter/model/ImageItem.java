package com.example.shopcenter.model;

public class ImageItem {

    private int mID;
    private String mName, mSrc;

    public int getID() {
        return mID;
    }

    public void setID(int ID) {
        mID = ID;
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

    public ImageItem() {
    }

    public ImageItem(int ID, String name, String src) {
        mID = ID;
        mName = name;
        mSrc = src;
    }
}
