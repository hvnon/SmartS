package com.shop.kissmartshop.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.j256.ormlite.dao.Dao;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.activities.PaymentActivity;
import com.shop.kissmartshop.activities.TouchCartActivity;
import com.shop.kissmartshop.adapters.ProductCartAdapter;
import com.shop.kissmartshop.adapters.ProductTouchAdapter;
import com.shop.kissmartshop.custom.SimpleDividerItemDecoration;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.model.SizeColorModel;
import com.shop.kissmartshop.utils.CommonUtils;
import com.shop.kissmartshop.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/13/2016.
 */
public class CartFragment extends Fragment{

    private List<ProductCartTouchModel> mListProductInCart;

    private RecyclerView mRecyclerViewProductTouched;
    private Button mButtonTry;
    private Button mButtonCheckout;
    private ProductCartAdapter mProductCartAdapter;
    private LinearLayout mLinearLayoutBottomAction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListProductInCart = new ArrayList<>();
        mListProductInCart.addAll(CommonUtils.lstProductCart);
//        initializeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        mRecyclerViewProductTouched = (RecyclerView)view.findViewById(R.id.recycler_view_list_product_cart);
        mRecyclerViewProductTouched.setHasFixedSize(true);
        mRecyclerViewProductTouched.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        LinearLayoutManager customLayout = new LinearLayoutManager(getActivity());
        mRecyclerViewProductTouched.setLayoutManager(customLayout);
        mLinearLayoutBottomAction = (LinearLayout)view.findViewById(R.id.ln_bottom_action_cart);

        mProductCartAdapter = new ProductCartAdapter(getActivity(), mListProductInCart);
        mRecyclerViewProductTouched.setAdapter(mProductCartAdapter);

        mButtonCheckout = (Button)view.findViewById(R.id.btn_checkout);
        mButtonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mButtonCheckout.getText().toString().equalsIgnoreCase(getResources().getString(R.string.check_out))) {
                    mButtonTry.setVisibility(View.GONE);
                    mButtonCheckout.setText(getResources().getString(R.string.pay_now));
                } else {
                    Intent iPayment = new Intent(getActivity(), PaymentActivity.class);
                    startActivityForResult(iPayment, 0);
                }
            }
        });

        mButtonTry = (Button)view.findViewById(R.id.btn_try);
        mButtonTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(ProductCartTouchModel product : mListProductInCart){
                    product.setProdStatus(Constants.PRODUCT_STATUS_SENT);
                }

                mProductCartAdapter.updateData(mListProductInCart);

                mButtonTry.setBackgroundResource(R.drawable.button_selector_grey);
                mButtonTry.setText(getResources().getString(R.string.cancel_try));

                mHanlderProductUpdateStatus.sendEmptyMessageDelayed(0, 2000);
            }
        });



        return view;
    }

    private void checkExistProduct()
    {
        if(mListProductInCart.size() > 0){
            mLinearLayoutBottomAction.setVisibility(View.VISIBLE);
        } else {
            mLinearLayoutBottomAction.setVisibility(View.GONE);
        }
    }

    public void updateData()
    {
        mListProductInCart.clear();
        mListProductInCart.addAll(CommonUtils.lstProductCart);

//        Dao<ProductCartTouchModel, Integer> productCartTouchDao;
//        try {
//            productCartTouchDao =((TouchCartActivity)getActivity()).getHelper().getProductCartTouchDao();
//            mListProductInCart.addAll(productCartTouchDao.queryForAll());
//        }catch (Exception e){}
        mProductCartAdapter.updateData(mListProductInCart);
        checkExistProduct();
    }

//    private void initializeData(){
//        mListProductInCart = new ArrayList<>();
//
//        List<SizeColorModel> lstColorSizes = new ArrayList<>();
//        SizeColorModel sizeColor1 = new SizeColorModel();
//        sizeColor1.setColor("#111111");
//        sizeColor1.setSize("12");
//        lstColorSizes.add(sizeColor1);
//
//        SizeColorModel sizeColor2 = new SizeColorModel();
//        sizeColor2.setColor("#1122ee");
//        sizeColor2.setSize("10");
//        lstColorSizes.add(sizeColor2);
//
//        mListProductInCart.add(new ProductCartTouchModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "$14.4", "$12.3", R.drawable.example, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING));
//        mListProductInCart.add(new ProductCartTouchModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "$12.4", "$21.2", R.drawable.example1, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING));
//        mListProductInCart.add(new ProductCartTouchModel("Fashionable Men's Athletic Shoes With Color Matching and Letter", "$12.5", "$23.1", R.drawable.example, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING));
//    }

    private Handler mHanlderProductUpdateStatus = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            for(ProductCartTouchModel product : mListProductInCart){
                product.setProdStatus(Constants.PRODUCT_STATUS_READY);
            }

            mProductCartAdapter.updateData(mListProductInCart);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        switch(resultCode)
        {
            case Constants.PAYMENT_SUCCESS:
                mButtonTry.setVisibility(View.VISIBLE);
                mButtonTry.setBackgroundResource(R.drawable.button_selector_blue);
                mButtonCheckout.setVisibility(View.VISIBLE);
                mLinearLayoutBottomAction.setVisibility(View.GONE);
                mListProductInCart.clear();
                mProductCartAdapter.updateData(mListProductInCart);
                mButtonCheckout.setText(getResources().getString(R.string.check_out));
                mButtonTry.setText(getResources().getString(R.string.try_all));
                break;
            case Constants.PAYMENT_CANCEL:
                break;
            case Constants.PAYMENT_FAILED:
                break;
        }
    }
}
