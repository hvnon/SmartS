package com.shop.kissmartshop.api;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ListProductModel;
import com.shop.kissmartshop.model.ListStaffModel;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.ProductTouchedModel;
import com.shop.kissmartshop.model.ResponseProductTryModel;
import com.shop.kissmartshop.model.ResponseUpdateTokenModel;
import com.shop.kissmartshop.utils.CommonUtils;
import com.shop.kissmartshop.utils.ProgressDialogUtils;

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

    private ProgressDialogUtils mProgressDialogUtils;

    private void showProgressDialog(Context context) {

        if(mProgressDialogUtils == null) {
            mProgressDialogUtils = new ProgressDialogUtils(context, "", context.getString(R.string.connecting));
        }
        mProgressDialogUtils.show();
    }

    private void closeDialog() {
        if(mProgressDialogUtils != null){
            mProgressDialogUtils.hide();
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

        Call<ListProductModel> lstProdObjs = service.getProducts("-1");

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

    public void getTouchedProducts(Context context, final Handler handler)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ProductTouchedModel> lstProdObjs = service.getPickups(CommonUtils.sUserId);

        lstProdObjs.enqueue(new Callback<ProductTouchedModel>() {
            @Override
            public void onResponse(Call<ProductTouchedModel> call, Response<ProductTouchedModel> response) {
                ProductTouchedModel products = response.body();
                Message msg = Message.obtain();
                msg.obj = products;
                handler.sendMessage(msg);
                closeDialog();
            }

            @Override
            public void onFailure(Call<ProductTouchedModel> call, Throwable t) {
                closeDialog();
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

        Call<ListUserModel> lstUserObjs = service.getUsers("1");

        lstUserObjs.enqueue(new Callback<ListUserModel>() {
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

    public void getStaffs(Context context)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ListStaffModel> lstUserObjs = service.getStaffs("1");

        lstUserObjs.enqueue(new Callback<ListStaffModel>() {
            @Override
            public void onResponse(Call<ListStaffModel> call, Response<ListStaffModel> response) {
                ListStaffModel staffModel = response.body();
                CommonUtils.lstStaff.addAll(staffModel.getStaffs());
                closeDialog();
            }

            @Override
            public void onFailure(Call<ListStaffModel> call, Throwable t) {

            }
        });
    }

    public void addProductTry(Context context, String productArr , final Handler handler)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ResponseProductTryModel> lstProdObjs = service.addProductTryAll(CommonUtils.sUserId, productArr);

        lstProdObjs.enqueue(new Callback<ResponseProductTryModel>() {
            @Override
            public void onResponse(Call<ResponseProductTryModel> call, Response<ResponseProductTryModel> response) {
                ResponseProductTryModel resProd = response.body();
                Message msg = Message.obtain();
                msg.obj = resProd;
                handler.sendMessage(msg);
                closeDialog();
            }

            @Override
            public void onFailure(Call<ResponseProductTryModel> call, Throwable t) {
                closeDialog();
            }
        });
    }

    public void addProductPickup(Context context, String productArr)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ResponseProductTryModel> lstProdObjs = service.addProductPickup(CommonUtils.sUserId, productArr);

        lstProdObjs.enqueue(new Callback<ResponseProductTryModel>() {
            @Override
            public void onResponse(Call<ResponseProductTryModel> call, Response<ResponseProductTryModel> response) {
//                ResponseProductTryModel resProd = response.body();
                closeDialog();
            }

            @Override
            public void onFailure(Call<ResponseProductTryModel> call, Throwable t) {
                closeDialog();
            }
        });
    }

    public void addProductBuy(Context context, String productArr)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ResponseProductTryModel> lstProdObjs = service.addProductBuy(CommonUtils.sUserId, productArr);

        lstProdObjs.enqueue(new Callback<ResponseProductTryModel>() {
            @Override
            public void onResponse(Call<ResponseProductTryModel> call, Response<ResponseProductTryModel> response) {
//                ResponseProductTryModel resProd = response.body();
//                Message msg = Message.obtain();
//                msg.obj = resProd;
//                handler.sendMessage(msg);
                closeDialog();
            }

            @Override
            public void onFailure(Call<ResponseProductTryModel> call, Throwable t) {
                closeDialog();
            }
        });
    }

    public void updateUserToken(Context context, final Handler handler)
    {
        showProgressDialog(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ICallServices service = retrofit.create(ICallServices.class);

        Call<ResponseUpdateTokenModel> tokenObj = service.updateUserToken(CommonUtils.sUserId, CommonUtils.token);

        tokenObj.enqueue(new Callback<ResponseUpdateTokenModel>() {
            @Override
            public void onResponse(Call<ResponseUpdateTokenModel> call, Response<ResponseUpdateTokenModel> response) {
                ResponseUpdateTokenModel resUser = response.body();
                Message msg = Message.obtain();
                msg.obj = resUser;
                handler.sendMessage(msg);
                closeDialog();
            }

            @Override
            public void onFailure(Call<ResponseUpdateTokenModel> call, Throwable t) {
                handler.sendEmptyMessage(0);
            }
        });
    }

}
