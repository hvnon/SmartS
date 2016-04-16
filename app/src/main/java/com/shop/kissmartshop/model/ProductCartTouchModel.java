package com.shop.kissmartshop.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by LENOVO on 4/14/2016.
 */
public class ProductCartTouchModel implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

    @DatabaseField
    private String productId;

    @DatabaseField
    private String description;

    @DatabaseField
    private String pricePromotion;

    @DatabaseField
    private String priceOriginal;

    @DatabaseField
    private int photoId;

    @ForeignCollectionField
    private Collection<SizeColorModel> lstSizeColors;

    @DatabaseField
    private int prodStatus;


    public ProductCartTouchModel(String productId, String description, String pricePromotion, String priceOriginal, int photoId, Collection<SizeColorModel> lstSizeColors, int prodStatus)
    {
        this.productId = productId;
        this.description = description;
        this.pricePromotion = pricePromotion;
        this.priceOriginal = priceOriginal;
        this.photoId = photoId;
        this.lstSizeColors = lstSizeColors;
        this.prodStatus = prodStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public Collection<SizeColorModel> getLstSizeColors() {
        return lstSizeColors;
    }

    public void setLstSizeColors(List<SizeColorModel> lstSizeColors) {
        this.lstSizeColors = lstSizeColors;
    }

    public int getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(int prodStatus) {
        this.prodStatus = prodStatus;
    }
}
