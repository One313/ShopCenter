package com.example.shopcenter.model;

import java.util.Objects;

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

    public Category(int id) {
        mId = id;
    }

    public Category(int id, String name) {
        this(id);
        mName = name;
    }

    public Category(int id, String name, int parent, ImageCategory imageCategory) {
        this(id, name);
        mParent = parent;
        mImageCategory = imageCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return mId == category.mId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }
}
