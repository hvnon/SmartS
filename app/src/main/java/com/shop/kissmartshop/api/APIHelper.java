package com.shop.kissmartshop.api;

import android.content.Context;
import android.os.Handler;

import com.shop.kissmartshop.model.ListProductModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LENOVO on 4/21/2016.
 */
public class APIHelper {
    public static String HOST_NAME = "http://hoa.do/merlin/";

    private void getProducts(Context context, final Handler handler)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ListProductModel> lstProdObjs = service.getProducts();

        lstProdObjs.enqueue(new Callback<ListProductModel>() {
            @Override
            public void onResponse(Call<ListProductModel> call, Response<ListProductModel> response) {
                ListProductModel products = response.body();

            }

            @Override
            public void onFailure(Call<ListProductModel> call, Throwable t) {

            }
        });
    }

}
