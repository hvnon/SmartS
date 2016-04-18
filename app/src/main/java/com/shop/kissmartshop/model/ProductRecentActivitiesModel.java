package com.shop.kissmartshop.model;


import java.io.Serializable;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductRecentActivitiesModel implements Serializable {
    private String description;
    private String pricePromotion;
    private String priceOriginal;
    private int photoId;
    private int amountOfTouch;
    private int amountOfTry;
    private int amountOfBuy;
    private int productState;

    public ProductRecentActivitiesModel(String description, String pricePromotion, String priceOriginal, int photoId, int amountOfTouch, int amountOfTry, int amountOfBuy, int prodState)
    {
        this.description = description;
        this.priceOriginal = priceOriginal;
        this.pricePromotion = pricePromotion;
        this.photoId = photoId;
        this.amountOfTouch = amountOfTouch;
        this.amountOfTry = amountOfTry;
        this.amountOfBuy = amountOfBuy;
        this.productState = prodState;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPricePromotion() {
        return pricePromotion;
    }

    public void setPricePromotion(String pricePromotion) {
        this.pricePromotion = pricePromotion;
    }

    public String getPriceOriginal() {
        return priceOriginal;
    }

    public void setPriceOriginal(String priceOriginal) {
        this.priceOriginal = priceOriginal;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getAmountOfTouch() {
        return amountOfTouch;
    }

    public void setAmountOfTouch(int amountOfTouch) {
        this.amountOfTouch = amountOfTouch;
    }

    public int getAmountOfTry() {
        return amountOfTry;
    }

    public void setAmountOfTry(int amountOfTry) {
        this.amountOfTry = amountOfTry;
    }

    public int getAmountOfBuy() {
        return amountOfBuy;
    }

    public void setAmountOfBuy(int amountOfBuy) {
        this.amountOfBuy = amountOfBuy;
    }

    public int getProductState() {
        return productState;
    }

    public void setProductState(int productState) {
        this.productState = productState;
    }

}
