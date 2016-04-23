package com.shop.kissmartshop.model;

import java.util.List;

/**
 * Created by LENOVO on 4/24/2016.
 */
public class ProductPickupModel {
    String date;
    List<ProductModel> products;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}
