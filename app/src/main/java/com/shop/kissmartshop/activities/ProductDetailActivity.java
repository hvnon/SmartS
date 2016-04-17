package com.shop.kissmartshop.activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.DirectionalViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.adapters.ProductPagerAdapter;
import com.shop.kissmartshop.utils.CommonUtils;
import com.viewpagerindicator.CirclePageIndicator;

public class ProductDetailActivity extends BaseActivity {

    private DirectionalViewPager mViewPagerProduct;
    private CirclePageIndicator mPageIndicatorProduct;

    private LinearLayout mLinearLayoutSizeColor;
    private LinearLayout mLinearLayoutAddToCart;
    private TextView mTextViewSelected;
    private TextView mTextViewPriceOriginal;
    private TextView mTextViewNumberAddToCart;
    private FloatingActionButton mFABAddToFavourite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        mViewPagerProduct = (DirectionalViewPager)findViewById(R.id.view_pager_product);
        mViewPagerProduct.setOrientation(DirectionalViewPager.VERTICAL);
        ProductPagerAdapter adapter = new ProductPagerAdapter(this);
        mViewPagerProduct.setAdapter(adapter);

        mPageIndicatorProduct = (CirclePageIndicator)findViewById(R.id.page_indicator_product);
        mPageIndicatorProduct.setViewPager(mViewPagerProduct);
        mPageIndicatorProduct.setFillColor(getResources().getColor(R.color.pink));
        mPageIndicatorProduct.setStrokeColor(getResources().getColor(R.color.grey));

        LinearLayout lnProductColors = (LinearLayout)findViewById(R.id.ln_product_colors);


        for(int i=0;i<2;i++) {
            TextView tvSizeColor = new TextView(this);
            tvSizeColor.setTextColor(this.getResources().getColor(R.color.white));
            tvSizeColor.setBackgroundResource(R.drawable.circle);
            tvSizeColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF5623")));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtils.dpToPx(this, 35), CommonUtils.dpToPx(this, 35));
            tvSizeColor.setGravity(Gravity.CENTER);
            tvSizeColor.setLayoutParams(params);

            LinearLayout lnSizeColor = new LinearLayout(this);
            LinearLayout.LayoutParams paramSizeColor = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lnSizeColor.setOrientation(LinearLayout.VERTICAL);
            paramSizeColor.rightMargin = CommonUtils.dpToPx(this, 10);
            paramSizeColor.gravity = Gravity.CENTER;
            lnSizeColor.setLayoutParams(paramSizeColor);
            lnSizeColor.setGravity(Gravity.CENTER);

            ImageView ivSizeIcon = new ImageView(this);
            LinearLayout.LayoutParams paramSizeIcon = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ivSizeIcon.setLayoutParams(paramSizeIcon);
            ivSizeIcon.setImageResource(R.drawable.ic_cart_bottom_action);
            ivSizeIcon.setVisibility(View.GONE);

            lnSizeColor.addView(ivSizeIcon);
            lnSizeColor.addView(tvSizeColor);
            lnProductColors.addView(lnSizeColor);

            tvSizeColor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tvSelected = (TextView) v;
                    if (mTextViewSelected == tvSelected) {
                        mLinearLayoutSizeColor.setVisibility(View.GONE);
                        mTextViewSelected = null;
                    } else {
                        mLinearLayoutSizeColor.setVisibility(View.VISIBLE);
                        mTextViewSelected = tvSelected;
                        String size = mTextViewSelected.getText().toString();
                        reselectSize(mLinearLayoutSizeColor, size);
                    }
                }
            });
        }

        mLinearLayoutSizeColor = (LinearLayout)findViewById(R.id.ln_size_color);
        mTextViewPriceOriginal = (TextView)findViewById(R.id.tv_price_original);
        mTextViewPriceOriginal.setPaintFlags(mTextViewPriceOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        mTextViewNumberAddToCart = (TextView)findViewById(R.id.tv_number_add_to_card);
        mLinearLayoutAddToCart = (LinearLayout)findViewById(R.id.ln_add_to_cart);
        mLinearLayoutAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTextViewNumberAddToCart.setText("(1)");
            }
        });

        mFABAddToFavourite = (FloatingActionButton)findViewById(R.id.fab_add_favourite);
        mFABAddToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFABAddToFavourite.setImageResource(R.drawable.ic_favorite_product_detail);
                mFABAddToFavourite.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.pink)));
            }
        });
        createSize();
    }

    private void createSize()
    {
        final LinearLayout lnSizeColorFirst = (LinearLayout)findViewById(R.id.ln_size_color_first);
        for(int i = 6; i<12; i++) {

            final FrameLayout lnSize = new FrameLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            lnSize.setLayoutParams(params);

            TextView tvSize = new TextView(this);
            FrameLayout.LayoutParams paramsSize = new FrameLayout.LayoutParams(CommonUtils.dpToPx(this, 30), CommonUtils.dpToPx(this, 30));
            paramsSize.gravity = Gravity.CENTER;
            tvSize.setGravity(Gravity.CENTER);
            tvSize.setLayoutParams(paramsSize);
            tvSize.setText(String.valueOf(i));
            lnSize.addView(tvSize);
            lnSizeColorFirst.addView(lnSize);
            tvSize.setTag(i);

            tvSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectSize(v);
                }
            });
        }

        final LinearLayout lnSizeColorSecond = (LinearLayout)findViewById(R.id.ln_size_color_second);
        for(int i = 12; i<18; i++) {

            final FrameLayout lnSize = new FrameLayout(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            lnSize.setLayoutParams(params);

            TextView tvSize = new TextView(this);
            FrameLayout.LayoutParams paramsSize = new FrameLayout.LayoutParams(CommonUtils.dpToPx(this, 30), CommonUtils.dpToPx(this, 30));
            paramsSize.gravity = Gravity.CENTER;
            tvSize.setGravity(Gravity.CENTER);
            tvSize.setLayoutParams(paramsSize);
            tvSize.setText(String.valueOf(i));
            lnSize.addView(tvSize);
            lnSizeColorSecond.addView(lnSize);
            tvSize.setTag(i);

            tvSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectSize(v);
                }
            });
        }
    }

    private void reselectSize(ViewGroup viewGroup, String value)
    {
        for(int i=0; i<viewGroup.getChildCount();i++)
        {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                reselectSize((ViewGroup) view, value);
            } else if ((view instanceof TextView)) {
                String val = ((TextView)view).getText().toString();
                if(value.equalsIgnoreCase(val)) {
                    view.setBackgroundResource(R.drawable.button_selector_blue);
                } else {
                    view.setBackgroundColor(getResources().getColor(R.color.transparent));
                }
            }
        }
    }

    private void selectSize(View v)
    {
        int value = Integer.parseInt(v.getTag().toString());
        v.setBackgroundResource(R.drawable.button_selector_blue);
        deselectSize(mLinearLayoutSizeColor, v);
        mTextViewSelected.setText(String.valueOf(value));
        mLinearLayoutSizeColor.setVisibility(View.GONE);
    }

    private void deselectSize(ViewGroup viewGroup, View v)
    {
        for(int i=0; i<viewGroup.getChildCount();i++)
        {
            View view = viewGroup.getChildAt(i);
            if (view instanceof ViewGroup) {
                deselectSize((ViewGroup)view, v);
            } else if ((view instanceof TextView)&& view != v) {
                view.setBackgroundColor(getResources().getColor(R.color.transparent));
            }
        }
    }
}

