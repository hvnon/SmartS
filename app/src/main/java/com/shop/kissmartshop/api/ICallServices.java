package com.shop.kissmartshop.api;

import com.shop.kissmartshop.model.ListProductModel;
import com.shop.kissmartshop.model.ListStaffModel;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.ProductPage;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by LENOVO on 4/21/2016.
 */
public interface ICallServices {

    @FormUrlEncoded
    @POST("/merlin/getProducts.php")
    Call<ListProductModel> getProducts(@Field("page") String page);

    @FormUrlEncoded
    @POST("/merlin/getStaffs.php")
    Call<ListStaffModel> getStaffs(@Field("in_store") String number);

    @FormUrlEncoded
    @POST("/merlin/getUsers.php")
    Call<ListUserModel> getUsers(@Field("in_store") String number);
}
