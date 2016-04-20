package com.shop.kissmartshop.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by LENOVO on 4/14/2016.
 */
public class SizeColorModel implements Serializable {

    private String colorSizeId;

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
}
