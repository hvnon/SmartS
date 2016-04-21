package com.shop.kissmartshop.activities;

import android.content.Context;
import android.content.Intent;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.api.APIHelper;
import com.shop.kissmartshop.custom.CustomLinearLayoutManager;
import com.shop.kissmartshop.adapters.ProductPagerAdapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.shop.kissmartshop.adapters.ProductRecentlyAdapter;
import com.shop.kissmartshop.model.ListProductModel;
import com.shop.kissmartshop.model.ProductModel;
import com.shop.kissmartshop.model.ProductRecentActivitiesModel;
import com.shop.kissmartshop.utils.Constants;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pickme.bluestone_sdk.BlueStone;
import pickme.bluestone_sdk.BluestoneManager;


public class RecentlyActivitiesActivity extends BaseActivity {

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

        List<Integer> lstProductPhotos = new ArrayList<>();
        lstProductPhotos.add(R.drawable.slider1);
        lstProductPhotos.add(R.drawable.slider2);
        lstProductPhotos.add(R.drawable.slider3);
        mProductPagerAdapter = new ProductPagerAdapter(this, lstProductPhotos);
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
                if (!isLaunchProductDetail) {
                    isLaunchProductDetail = true;
                    ProductRecentActivitiesModel product = lstProductRecently.get(0);
                    Intent iProductDetail = new Intent(mContext, ProductDetailActivity.class);
                    iProductDetail.putExtra(Constants.EXTRA_PRODUCT_DETAIL, product);
                    mContext.startActivity(iProductDetail);
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
                ProductRecentActivitiesModel productRecently = new ProductRecentActivitiesModel(product.getProduct_name(), product.getPrice(), "12.3", R.drawable.shoes1,  r.nextInt(100), 10, 2, r.nextInt(3));
                productRecently.setProduct_id(product.getProduct_id());
                productRecently.setImage(product.getImage());
                productRecently.setProduct_name(product.getProduct_name());
                lstProductRecently.add(productRecently);
            }

            mProductAdapter.updateData(lstProductRecently);


//            lstProductRecently.add(new ProductRecentActivitiesModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.4", "21.2", R.drawable.shoes2, 40,3,2, Constants.PRODUCT_STATE_TOUCHING));
//            lstProductRecently.add(new ProductRecentActivitiesModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.5", "23.1", R.drawable.shoes3, 45, 5, 6, Constants.PRODUCT_STATE_TRYING));
//            lstProductRecently.add(new ProductRecentActivitiesModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.5", "23.1", R.drawable.shoes4, 45, 5, 6, Constants.PRODUCT_STATE_TRYING));
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBluestoneManager.stopScan();
    }
}
