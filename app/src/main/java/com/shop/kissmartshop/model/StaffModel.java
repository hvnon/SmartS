package com.shop.kissmartshop.model;

/**
 * Created by LENOVO on 4/21/2016.
 */
public class StaffModel {
    private String staff_id;
    private String first_name;
    private String profile_pic;

    public StaffModel(String id, String firstName, String pic)
    {
        this.staff_id = id;
        this.first_name = firstName;
        this.profile_pic = pic;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }
}
