package com.shop.kissmartshop.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shop.kissmartshop.R;

/**
 * Created by LENOVO on 4/13/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private ImageView mBack, mSearch;
    private TextView mTextViewTitle;
    private RelativeLayout mRelativeLayoutCart;
    private TextView mTextViewCartNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActionBar();

    }

    private void initActionBar()
    {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
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

    protected void onBack()
    {
        finish();
    }

    protected void onSearch()
    {

    }

    protected void onCartInfo()
    {

    }

    protected void onUpdateCartNumber(String number)
    {
        if (mTextViewCartNumber != null){
            mTextViewCartNumber.setText(number);
        }
    }
}
