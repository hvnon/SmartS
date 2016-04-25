package com.shop.kissmartshop.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.adapters.ProductPagerAdapter;
import com.shop.kissmartshop.adapters.ProductRecentlyAdapter;
import com.shop.kissmartshop.api.APIHelper;
import com.shop.kissmartshop.model.ListProductModel;
import com.shop.kissmartshop.model.ProductModel;
import com.shop.kissmartshop.model.ProductRecentActivitiesModel;
import com.shop.kissmartshop.utils.Constants;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pickme.bluestone_sdk.BlueStone;
import pickme.bluestone_sdk.BluestoneManager;


public class RecentlyActivitiesActivity extends BaseActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private ViewPager mViewPagerProduct;
    private ProductPagerAdapter mProductPagerAdapter;
    private RecyclerView mRecyclerViewRecentlyActivities;
    private CirclePageIndicator mPageIndicatorProduct;
    private LinearLayout mLinearLayoutCart;

    private List<ProductRecentActivitiesModel> lstProductRecently;
    private ProductRecentlyAdapter mProductAdapter;

    private BluestoneManager mBluestoneManager;
    private Context mContext;
    private Boolean isLaunchProductDetail = false;
//    private ProgressDialogUtils mProgressDialogUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_activities);

        mContext = this;
        //initActionBar();
        initializeData();

        mViewPagerProduct = (ViewPager)findViewById(R.id.pager_product);
        mRecyclerViewRecentlyActivities = (RecyclerView)findViewById(R.id.recycler_view_list_recently_activities);
        mPageIndicatorProduct = (CirclePageIndicator)findViewById(R.id.page_indicator_product);
        mLinearLayoutCart = (LinearLayout)findViewById(R.id.ln_cart);
//        mScrollViewActivities = (ScrollView)findViewById(R.id.sv_product_content);
        mLinearLayoutCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCartInfo();
            }
        });

        List<String> lstProductPhotos = new ArrayList<>();
        lstProductPhotos.add(String.valueOf(R.drawable.slider1));
        lstProductPhotos.add(String.valueOf(R.drawable.slider2));
        lstProductPhotos.add(String.valueOf(R.drawable.slider3));
        mProductPagerAdapter = new ProductPagerAdapter(this, lstProductPhotos, Constants.LOAD_IMAGE_FROM_RESOUCE);
        mViewPagerProduct.setAdapter(mProductPagerAdapter);

        mRecyclerViewRecentlyActivities.setNestedScrollingEnabled(false);
        mRecyclerViewRecentlyActivities.setHasFixedSize(false);
        LinearLayoutManager customLayout = new LinearLayoutManager(this);
        mRecyclerViewRecentlyActivities.setLayoutManager(customLayout);

        mProductAdapter = new ProductRecentlyAdapter(this, lstProductRecently);
        mRecyclerViewRecentlyActivities.setAdapter(mProductAdapter);

        mPageIndicatorProduct.setViewPager(mViewPagerProduct);
        mPageIndicatorProduct.setFillColor(getResources().getColor(R.color.pink));
        mPageIndicatorProduct.setStrokeColor(getResources().getColor(R.color.grey));

        mBluestoneManager = new BluestoneManager(this);
        mBluestoneManager.setListener(mBlueStoneListener);
        mBluestoneManager.updateRange(-85);

//        if (checkPlayServices()) {
//            mProgressDialogUtils = new ProgressDialogUtils(this, "", getString(R.string.connecting));
//            mProgressDialogUtils.show();
//            // Start IntentService to register this application with GCM.
//            Intent intent = new Intent(this, ShopRegistrationIntentService.class);
//            startService(intent);
//        }
    }

    private void initializeData(){

        lstProductRecently = new ArrayList<>();
        (new APIHelper()).getProducts(this, mHanlderGetProduct);

    }

    private BluestoneManager.BlueStoneListener mBlueStoneListener = new BluestoneManager.BlueStoneListener() {

        @Override
        public void onBlueStoneCallBack(BlueStone blueStone, boolean inRange, String UUID, int major, int minor) {
//            Product current = products.get(blueStone.id);
            if (inRange) {
                Log.i("BlueStone ID", "Blue Stone ID : " + blueStone.id);
                if (!isLaunchProductDetail && lstProductRecently != null && lstProductRecently.size() > 0) {
                    for(ProductRecentActivitiesModel prod : lstProductRecently) {
                        if(prod.getBluestone().equalsIgnoreCase(blueStone.id)) {
//                            JsonArray arr = new JsonArray();
//                            JsonObject obj = new JsonObject();
//                            obj.addProperty("product_id", prod.getProduct_id());
//                            obj.addProperty("color_id", "463");
//                            obj.addProperty("size_id", "302");
//                            arr.add(obj);
//                            String prodArr = arr.toString();
//                            new APIHelper().addProductPickup(mContext, prodArr);
                            new APIHelper().addProductPickup(mContext, prod.getProduct_id());

                            isLaunchProductDetail = true;
                            Intent iProductDetail = new Intent(mContext, ProductDetailActivity.class);
                            iProductDetail.putExtra(Constants.EXTRA_PRODUCT_DETAIL, prod);
                            mContext.startActivity(iProductDetail);
                        }
                    }
                }
            } else {
                isLaunchProductDetail = false;
            }
        }

        @Override
        public void onScanStart() {
            invalidateOptionsMenu();
        }

        @Override
        public void onScanStop() {
            invalidateOptionsMenu();
        }
    };

    private Handler mHanlderGetProduct = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ListProductModel products = (ListProductModel)msg.obj;

            for(ProductModel product : products.getProducts()) {
                Random r = new Random();
                double priceOriginal = Double.parseDouble(product.getPrice()) + 5;
                ProductRecentActivitiesModel productRecently = new ProductRecentActivitiesModel(product.getProduct_name(), product.getPrice(), String.format("%.2f", priceOriginal), R.drawable.shoes1,  r.nextInt(100), 10, 2, r.nextInt(3));
                productRecently.setProduct_id(product.getProduct_id());
                productRecently.setImage(product.getImage());
                productRecently.setProduct_name(product.getProduct_name());
                productRecently.setBluestone(product.getBluestone());
                productRecently.setColors(product.getColors());
                lstProductRecently.add(productRecently);
            }

            mProductAdapter.updateData(lstProductRecently);


//            lstProductRecently.add(new ProductRecentActivitiesModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.4", "21.2", R.drawable.shoes2, 40,3,2, Constants.PRODUCT_STATE_TOUCHING));
//            lstProductRecently.add(new ProductRecentActivitiesModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.5", "23.1", R.drawable.shoes3, 45, 5, 6, Constants.PRODUCT_STATE_TRYING));
//            lstProductRecently.add(new ProductRecentActivitiesModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.5", "23.1", R.drawable.shoes4, 45, 5, 6, Constants.PRODUCT_STATE_TRYING));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
//                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mBluestoneManager.stopScan();
        }catch (Exception e){}
    }

//    private BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            SharedPreferences sharedPreferences =
//                    PreferenceManager.getDefaultSharedPreferences(context);
//            boolean sentToken = sharedPreferences
//                    .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
//            if (sentToken) {
//
//            } else {
//
//            }
//
//            if (mProgressDialogUtils != null) {
//                mProgressDialogUtils.hide();
//            }
//        }
//    };
//
//    private boolean checkPlayServices() {
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
//        if (resultCode != ConnectionResult.SUCCESS) {
//            if (apiAvailability.isUserResolvableError(resultCode)) {
//                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
//                        .show();
//            } else {
//                //Show message here inform user this device doesn't support play service
//               new AlertDialogUtiils().showDialog(mContext, getString(R.string.unsupport_play_service));
//            }
//            return false;
//        }
//        return true;
//    }
}

