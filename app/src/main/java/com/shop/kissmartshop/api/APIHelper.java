package com.shop.kissmartshop.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ListProductModel;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.ProductPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LENOVO on 4/21/2016.
 */
public class APIHelper {
    public static final String HOST_NAME = "http://hoa.do";

    private ProgressDialog mProgressDialog;

    private void showProgressDialog(Context context) {

        if(mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setMessage(context.getString(R.string.connecting));
        }
        mProgressDialog.show();
    }

    private void closeDialog() {
        if(mProgressDialog != null && mProgressDialog.isShowing()){
            mProgressDialog.hide();
        }
    }

    public void getProducts(Context context, final Handler handler)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ListProductModel> lstProdObjs = service.getProducts("1");

        lstProdObjs.enqueue(new Callback<ListProductModel>() {
            @Override
            public void onResponse(Call<ListProductModel> call, Response<ListProductModel> response) {
                ListProductModel products = response.body();
                Message msg = Message.obtain();
                msg.obj = products;
                handler.sendMessage(msg);
                closeDialog();
            }

            @Override
            public void onFailure(Call<ListProductModel> call, Throwable t) {

            }
        });
    }

    public void getUsers(Context context, final Handler handler)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ListUserModel> lstProdObjs = service.getUsers("1");

        lstProdObjs.enqueue(new Callback<ListUserModel>() {
            @Override
            public void onResponse(Call<ListUserModel> call, Response<ListUserModel> response) {
                ListUserModel users = response.body();
                Message msg = Message.obtain();
                msg.obj = users;
                handler.sendMessage(msg);
                closeDialog();
            }

            @Override
            public void onFailure(Call<ListUserModel> call, Throwable t) {

            }
        });
    }

}
