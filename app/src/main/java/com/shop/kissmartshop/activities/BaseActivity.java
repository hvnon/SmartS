package com.shop.kissmartshop.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.model.StaffModel;
import com.shop.kissmartshop.utils.AlertDialogUtiils;
import com.shop.kissmartshop.utils.CommonUtils;
import com.shop.kissmartshop.utils.Constants;

/**
 * Created by LENOVO on 4/13/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ImageView mBack, mSearch;
    private TextView mTextViewTitle;
    private RelativeLayout mRelativeLayoutCart;
    private TextView mTextViewCartNumber;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

        mContext = this;

    }

    private void initActionBar()
    {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
//        mActionBar.setDisplayHomeAsUpEnabled(true);
        LayoutInflater mInflater = LayoutInflater.from(this);

        View customView = mInflater.inflate(R.layout.custom_action_bar, null);
        mBack = (ImageView)customView.findViewById(R.id.iv_back);
        mTextViewTitle = (TextView)customView.findViewById(R.id.tv_title);
        mSearch = (ImageView)customView.findViewById(R.id.iv_search);
        mRelativeLayoutCart = (RelativeLayout)customView.findViewById(R.id.rl_cart);
        mTextViewCartNumber = (TextView)customView.findViewById(R.id.tv_num_of_product);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearch();
            }
        });

        mRelativeLayoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCartInfo();
            }
        });

        mActionBar.setCustomView(customView);
        mActionBar.setDisplayShowCustomEnabled(true);


    }

    @Override
    protected void onResume() {
        onUpdateCartNumber();
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageBroadcastReceiver,
                new IntentFilter(Constants.MESSAGE_NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageBroadcastReceiver);
    }

    protected void onBack()
    {
        finish();
    }

    protected void onSearch()
    {

    }

    protected void setTitle(String title)
    {
        mTextViewTitle.setText(title);
    }

    protected void hideActionButton()
    {
        mRelativeLayoutCart.setVisibility(View.GONE);
        mSearch.setVisibility(View.GONE);
    }

    protected void onCartInfo()
    {
        Intent iTouchCart = new Intent(getApplicationContext(), TouchCartActivity.class);
        startActivity(iTouchCart);
    }

    public void onUpdateCartNumber()
    {
        if (mTextViewCartNumber != null){
            if(CommonUtils.countProdInCart > 0) {
                mTextViewCartNumber.setVisibility(View.VISIBLE);
                mTextViewCartNumber.setText(String.valueOf(CommonUtils.countProdInCart));
            } else {
                mTextViewCartNumber.setVisibility(View.GONE);
            }

        }
    }

    protected void onUpdateIncreaseCartNumber()
    {
        CommonUtils.countProdInCart += 1;
        if (mTextViewCartNumber != null){
            mTextViewCartNumber.setVisibility(View.VISIBLE);
            mTextViewCartNumber.setText(String.valueOf(CommonUtils.countProdInCart));
        }
    }

    private BroadcastReceiver mMessageBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(Constants.MESSAGE_NOTIFICATION);
//            new AlertDialogUtiils().showDialog(mContext, message);
            Gson gson = new Gson();
            JsonObject objData = new JsonObject();
            objData = gson.fromJson(message, JsonObject.class);
            JsonArray arrProds = objData.getAsJsonArray("products");
            if (arrProds != null) {
                for (int i = 0; i < arrProds.size(); i++) {
                    JsonObject prod = arrProds.get(i).getAsJsonObject();
                    String prodId = prod.get("product_id").getAsString();
                    String prodStatus = prod.get("status").getAsString();
                    for (ProductCartTouchModel product : CommonUtils.lstProductCart) {
                        if (product.getProduct_id().equalsIgnoreCase(prodId)) {
                            if (prodStatus.equalsIgnoreCase("ready")) {
                                product.setProdStatus(Constants.PRODUCT_STATUS_READY);
                            } else if (prodStatus.equalsIgnoreCase("not found")) {
                                product.setProdStatus(Constants.PRODUCT_STATUS_NOT_FOUND);
                            }
                        }
                    }
                }
                Intent iUpdateProdStatusNotification = new Intent(Constants.UPDATE_CART_PRODUCT_STATUS);
                LocalBroadcastManager.getInstance(mContext).sendBroadcast(iUpdateProdStatusNotification);
            } else {
                JsonElement eStaff = objData.get("assigned");
                if(eStaff != null) {
                    String staffId = objData.get("assigned").getAsString();
                    for(StaffModel staff : CommonUtils.lstStaff){
                        if(staff.getStaff_id().equalsIgnoreCase(staffId)){
                            new AlertDialogUtiils().showDialogStaffInform(mContext, staff.getFirst_name(), staff.getProfile_pic());
                        }
                    }
                }

            }
        }
    };
}
