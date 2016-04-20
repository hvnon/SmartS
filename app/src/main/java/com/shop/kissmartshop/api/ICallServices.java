package com.shop.kissmartshop.api;

import com.shop.kissmartshop.model.ListProductModel;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by LENOVO on 4/21/2016.
 */
public interface ICallServices {

    @POST("/getProducts")
    Call<ListProductModel> getProducts();
}
