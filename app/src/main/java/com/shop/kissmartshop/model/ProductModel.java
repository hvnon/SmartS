package com.shop.kissmartshop.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductModel implements Serializable {

    protected String product_id;
    protected String product_name;
    protected String price;
    protected String bluestone;
    protected String image;
    protected List<ColorModel> colors;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBluestone() {
        return bluestone;
    }

    public void setBluestone(String bluestone) {
        this.bluestone = bluestone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ColorModel> getColors() {
        return colors;
    }

    public void setColors(List<ColorModel> colors) {
        this.colors = colors;
    }
}
