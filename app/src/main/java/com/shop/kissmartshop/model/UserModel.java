package com.shop.kissmartshop.model;

/**
 * Created by LENOVO on 4/21/2016.
 */
public class UserModel {

    private String user_id;
    private String username;
    private String profile_pic;
    private String cart_actioned;
    private String buy_count;
    private String try_count;
    private String touch_count;
    private String cart_count;
    private String cart_status;
    private String push_token;
    private String timestamp;
    private String is_online;

    public Integer photoId;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getCart_actioned() {
        return cart_actioned;
    }

    public void setCart_actioned(String cart_actioned) {
        this.cart_actioned = cart_actioned;
    }

    public String getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(String buy_count) {
        this.buy_count = buy_count;
    }

    public String getTry_count() {
        return try_count;
    }

    public void setTry_count(String try_count) {
        this.try_count = try_count;
    }

    public String getTouch_count() {
        return touch_count;
    }

    public void setTouch_count(String touch_count) {
        this.touch_count = touch_count;
    }

    public String getCart_count() {
        return cart_count;
    }

    public void setCart_count(String cart_count) {
        this.cart_count = cart_count;
    }

    public String getCart_status() {
        return cart_status;
    }

    public void setCart_status(String cart_status) {
        this.cart_status = cart_status;
    }

    public String getPush_token() {
        return push_token;
    }

    public void setPush_token(String push_token) {
        this.push_token = push_token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getIs_online() {
        return is_online;
    }

    public void setIs_online(String is_online) {
        this.is_online = is_online;
    }
}
