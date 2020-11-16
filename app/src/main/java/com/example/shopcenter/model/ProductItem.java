package com.example.shopcenter.model;

public class ProductItem {

    private int mID;
    private String
            mName,
            mDescription,
            mPrice;

    private ImageItem[] mImages;

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

    public ProductItem() {
    }

    public ProductItem(int ID, String name, String description, String price, ImageItem[] images) {
        mID = ID;
        mName = name;
        mDescription = description;
        mPrice = price;
        mImages = images;
    }
}
