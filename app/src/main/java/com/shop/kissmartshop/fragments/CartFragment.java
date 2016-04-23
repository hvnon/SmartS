package com.shop.kissmartshop.fragments;

import android.content.Context;
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
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.activities.PaymentActivity;
import com.shop.kissmartshop.activities.TouchCartActivity;
import com.shop.kissmartshop.adapters.ProductCartAdapter;
import com.shop.kissmartshop.api.APIHelper;
import com.shop.kissmartshop.custom.SimpleDividerItemDecoration;
import com.shop.kissmartshop.model.ProductCartTouchModel;
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
    private TextView mTextViewTotalPrice;

    private Context mContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListProductInCart = new ArrayList<>();
        mListProductInCart.addAll(CommonUtils.lstProductCart);

        mContext = getActivity();
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
        mTextViewTotalPrice = (TextView)view.findViewById(R.id.tv_total_price);

        mProductCartAdapter = new ProductCartAdapter(getActivity(), mListProductInCart);
        mRecyclerViewProductTouched.setAdapter(mProductCartAdapter);

        mButtonCheckout = (Button)view.findViewById(R.id.btn_checkout);
        mButtonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mButtonCheckout.getText().toString().equalsIgnoreCase(getResources().getString(R.string.check_out))) {
//                    calculatePrice();
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
                JsonArray arr = new JsonArray();
                for(ProductCartTouchModel product : mListProductInCart){
                    JsonObject obj = new JsonObject();
                    obj.addProperty("product_id", product.getProduct_id());
                    obj.addProperty("color_id", "463");
                    obj.addProperty("size_id", "302");
                    arr.add(obj);
                }

                String prodArr = arr.toString();
                (new APIHelper()).addProductTry(mContext, prodArr, mHanlderPostProduct);

//                mProductCartAdapter.updateData(mListProductInCart);
//
//                mButtonTry.setBackgroundResource(R.drawable.button_selector_grey);
//                mButtonTry.setText(getResources().getString(R.string.cancel_try));
//
//                mHanlderProductUpdateStatus.sendEmptyMessageDelayed(0, 2000);
            }
        });

        updateData();

        return view;
    }

    private void calculatePrice()
    {
        double totalPrice = 0.0;
        for(ProductCartTouchModel product : mListProductInCart){
            totalPrice += Double.parseDouble(product.getPricePromotion().toString());
        }
        mTextViewTotalPrice.setText(String.format("$%.2f", totalPrice));
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
        if(mListProductInCart != null) {
            mListProductInCart.clear();
            mListProductInCart.addAll(CommonUtils.lstProductCart);


            if (mProductCartAdapter != null) {
                mProductCartAdapter.updateData(mListProductInCart);
            }
            checkExistProduct();
            calculatePrice();
        }

    }

    private Handler mHanlderPostProduct = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            for(ProductCartTouchModel product : mListProductInCart){
                product.setProdStatus(Constants.PRODUCT_STATUS_SENT);
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
                CommonUtils.lstProductCart.clear();
                CommonUtils.countProdInCart = 0;
                ((TouchCartActivity)getActivity()).onUpdateCartNumber();
                break;
            case Constants.PAYMENT_CANCEL:
                break;
            case Constants.PAYMENT_FAILED:
                break;
        }
    }
}
