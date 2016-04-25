package com.shop.kissmartshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LENOVO on 4/24/2016.
 */
public class SizeModel implements Serializable {

    private String size_id;
    private String size;

    public String getSize_id() {
        return size_id;
    }

    public void setSize_id(String size_id) {
        this.size_id = size_id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
