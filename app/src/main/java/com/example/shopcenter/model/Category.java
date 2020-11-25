package com.example.shopcenter.model;

public class Category {

    private int mId;
    private String mName;
    private int mParent;
    private ImageCategory mImageCategory;

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

    public int getParent() {
        return mParent;
    }

    public void setParent(int parent) {
        mParent = parent;
    }

    public ImageCategory getImageCategory() {
        return mImageCategory;
    }

    public void setImageCategory(ImageCategory imageCategory) {
        mImageCategory = imageCategory;
    }

    public Category() {
    }

    public Category(int id, String name, int parent, ImageCategory imageCategory) {
        mId = id;
        mName = name;
        mParent = parent;
        mImageCategory = imageCategory;
    }
}
