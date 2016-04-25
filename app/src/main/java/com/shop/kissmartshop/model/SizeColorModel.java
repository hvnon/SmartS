package com.shop.kissmartshop.model;

import java.io.Serializable;

/**
 * Created by LENOVO on 4/14/2016.
 */
public class SizeColorModel implements Serializable {

    private String colorId;
    private String sizeId;

    private String color;

    private String size;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }
}
