package com.shop.kissmartshop.model;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Created by LENOVO on 4/14/2016.
 */
public class SizeColorModel implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

//    @DatabaseField(foreign = true)
//    private ProductCartTouchModel product;

    @DatabaseField
    private String colorSizeId;

    @DatabaseField
    private String color;

    @DatabaseField
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
