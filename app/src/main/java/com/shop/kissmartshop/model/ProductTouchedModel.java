package com.shop.kissmartshop.model;

import java.util.List;

/**
 * Created by LENOVO on 4/24/2016.
 */
public class ProductTouchedModel {
    private String user_id;
    private List<ProductPickupModel> pickups;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public List<ProductPickupModel> getPickups() {
        return pickups;
    }

    public void setPickups(List<ProductPickupModel> pickups) {
        this.pickups = pickups;
    }
}
