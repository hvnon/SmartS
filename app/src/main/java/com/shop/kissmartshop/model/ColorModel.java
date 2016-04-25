package com.shop.kissmartshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LENOVO on 4/24/2016.
 */
public class ColorModel implements Serializable {

    private String color_id;
    private String color_name;
    private String color_hex;
    private List<SizeModel> sizes;

    public String getColor_id() {
        return color_id;
    }

    public List<SizeModel> getSizes() {
        return sizes;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }

    public void setSizes(List<SizeModel> sizes) {
        this.sizes = sizes;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_hex() {
        return color_hex;
    }

    public void setColor_hex(String color_hex) {
        this.color_hex = color_hex;
    }
}
