package com.example.model;

public class ProductImage {
    private int productID;
    private String img;

    public ProductImage() {
    }

    public ProductImage(int productID, String img) {
        this.productID = productID;
        this.img = img;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
