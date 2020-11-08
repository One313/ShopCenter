package com.example.shopcenter.model;

public class ProductItem {

    private int mID;
    private String
            mName,
            mPrice,
            mWeight;

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

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getWeight() {
        return mWeight;
    }

    public void setWeight(String weight) {
        mWeight = weight;
    }

    public ImageItem[] getImages() {
        return mImages;
    }

    public void setImages(ImageItem[] images) {
        mImages = images;
    }

    public ProductItem() {
    }

    public ProductItem(int ID, String name, String price, String weight, ImageItem[] images) {
        mID = ID;
        mName = name;
        mPrice = price;
        mWeight = weight;
        mImages = images;
    }
}
