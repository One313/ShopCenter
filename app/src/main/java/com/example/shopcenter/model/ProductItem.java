package com.example.shopcenter.model;

import java.util.Objects;

public class ProductItem {

    private int mID;
    private String
            mName,
            mDescription,
            mPrice;
    private ImageItem[] mImages;
    private Category[] mCategories;

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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public ImageItem[] getImages() {
        return mImages;
    }

    public void setImages(ImageItem[] images) {
        mImages = images;
    }

    public Category[] getCategories() {
        return mCategories;
    }

    public void setCategories(Category[] categories) {
        mCategories = categories;
    }

    public ProductItem(int ID, String name, String description, String price, ImageItem[] images, Category[] categories) {
        mID = ID;
        mName = name;
        mDescription = description;
        mPrice = price;
        mImages = images;
        mCategories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItem that = (ProductItem) o;
        return mID == that.mID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mID);
    }
}
