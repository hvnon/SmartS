package com.shop.kissmartshop.api;

import com.shop.kissmartshop.model.ListProductModel;
import com.shop.kissmartshop.model.ListStaffModel;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.ProductTouchedModel;
import com.shop.kissmartshop.model.ResponseProductTryModel;
import com.shop.kissmartshop.model.ResponseUpdateTokenModel;

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

    @FormUrlEncoded
    @POST("/merlin/addTry.php")
    Call<ResponseProductTryModel> addProductTryAll(@Field("user_id") String userId, @Field("products") String productsTry);

    @FormUrlEncoded
    @POST("/merlin/addPickup.php")
    Call<ResponseProductTryModel> addProductPickup(@Field("user_id") String userId, @Field("products") String productsPickup);

    @FormUrlEncoded
    @POST("/merlin/addBuy.php")
    Call<ResponseProductTryModel> addProductBuy(@Field("user_id") String userId, @Field("products") String productsBuy);

    @FormUrlEncoded
    @POST("/merlin/updateUserToken.php")
    Call<ResponseUpdateTokenModel> updateUserToken(@Field("user_id") String userId, @Field("push_token") String token);

    @FormUrlEncoded
    @POST("/merlin/getPickups.php")
    Call<ProductTouchedModel> getPickups(@Field("user_id") String userId);
}
