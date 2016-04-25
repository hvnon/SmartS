package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.activities.ProductDetailActivity;
import com.shop.kissmartshop.model.ProductRecentActivitiesModel;
import com.shop.kissmartshop.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductRecentlyAdapter extends RecyclerView.Adapter<ProductRecentlyAdapter.ProductViewHolder>{

    private List<ProductRecentActivitiesModel> mLstProducts;
    private Context mContext;

    public ProductRecentlyAdapter(Context context, List<ProductRecentActivitiesModel> products){
        this.mContext = context;
        this.mLstProducts = new ArrayList<>();
        this.mLstProducts.addAll(products);
    }

    @Override
    public int getItemCount() {
        return mLstProducts.size();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_recently, viewGroup, false);
        return new ProductViewHolder(view);
    }

    public void updateData(List<ProductRecentActivitiesModel> lstProducts)
    {
        mLstProducts.clear();
        mLstProducts.addAll(lstProducts);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, final int i) {
        productViewHolder.productDescription.setText(mLstProducts.get(i).getDescription());
        productViewHolder.pricePromotion.setText(mLstProducts.get(i).getPricePromotion());
        productViewHolder.priceOriginal.setText(mLstProducts.get(i).getPriceOriginal());
//        productViewHolder.productPhoto.setImageResource(mLstProducts.get(i).getPhotoId());
        if(!mLstProducts.get(i).getImage().equalsIgnoreCase("")) {
            Picasso.with(mContext).load(mLstProducts.get(i).getImage()).into(productViewHolder.productPhoto);
        }

        productViewHolder.amountOfTouch.setText(String.valueOf(mLstProducts.get(i).getAmountOfTouch()));
        productViewHolder.amountOfTry.setText(String.valueOf(mLstProducts.get(i).getAmountOfTry()));
        productViewHolder.amountOfBuy.setText(String.valueOf(mLstProducts.get(i).getAmountOfBuy()));

        switch (mLstProducts.get(i).getProductState())
        {
            case Constants.PRODUCT_STATE_BUYING:
                productViewHolder.amountOfBuy.setBackgroundResource(R.drawable.ic_product_info_highlight);
                productViewHolder.amountOfTouch.setBackgroundResource(R.drawable.ic_product_info);
                productViewHolder.amountOfTry.setBackgroundResource(R.drawable.ic_product_info);

                productViewHolder.textBuy.setTextColor(mContext.getResources().getColor(R.color.pink));
                productViewHolder.textTouch.setTextColor(mContext.getResources().getColor(R.color.light_grey));
                productViewHolder.textTry.setTextColor(mContext.getResources().getColor(R.color.light_grey));

                productViewHolder.textBuy.setText(mContext.getString(R.string.buying));
                productViewHolder.textTouch.setText(mContext.getString(R.string.touched));
                productViewHolder.textTry.setText(mContext.getString(R.string.tried));

                break;

            case Constants.PRODUCT_STATE_TOUCHING:
                productViewHolder.amountOfBuy.setBackgroundResource(R.drawable.ic_product_info);
                productViewHolder.amountOfTouch.setBackgroundResource(R.drawable.ic_product_info_highlight);
                productViewHolder.amountOfTry.setBackgroundResource(R.drawable.ic_product_info);

                productViewHolder.textBuy.setTextColor(mContext.getResources().getColor(R.color.light_grey));
                productViewHolder.textTouch.setTextColor(mContext.getResources().getColor(R.color.pink));
                productViewHolder.textTry.setTextColor(mContext.getResources().getColor(R.color.light_grey));

                productViewHolder.textBuy.setText(mContext.getString(R.string.buy));
                productViewHolder.textTouch.setText(mContext.getString(R.string.touching));
                productViewHolder.textTry.setText(mContext.getString(R.string.tried));
                break;

            case Constants.PRODUCT_STATE_TRYING:
                productViewHolder.amountOfBuy.setBackgroundResource(R.drawable.ic_product_info);
                productViewHolder.amountOfTouch.setBackgroundResource(R.drawable.ic_product_info);
                productViewHolder.amountOfTry.setBackgroundResource(R.drawable.ic_product_info_highlight);

                productViewHolder.textBuy.setTextColor(mContext.getResources().getColor(R.color.light_grey));
                productViewHolder.textTouch.setTextColor(mContext.getResources().getColor(R.color.light_grey));
                productViewHolder.textTry.setTextColor(mContext.getResources().getColor(R.color.pink));

                productViewHolder.textBuy.setText(mContext.getString(R.string.buy));
                productViewHolder.textTouch.setText(mContext.getString(R.string.touched));
                productViewHolder.textTry.setText(mContext.getString(R.string.trying));
                break;
        }

        productViewHolder.priceOriginal.setPaintFlags(productViewHolder.priceOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        productViewHolder.cvProductInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductRecentActivitiesModel product = mLstProducts.get(i);
                Intent iProductDetail = new Intent(mContext, ProductDetailActivity.class);
                iProductDetail.putExtra(Constants.EXTRA_PRODUCT_DETAIL, product);
                mContext.startActivity(iProductDetail);
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        CardView cvProductInfo;
        TextView productDescription;
        TextView pricePromotion;
        TextView priceOriginal;
        ImageView productPhoto;
        TextView amountOfTouch;
        TextView textTouch;
        TextView amountOfTry;
        TextView textTry;
        TextView amountOfBuy;
        TextView textBuy;

        ProductViewHolder(View itemView) {
            super(itemView);
            cvProductInfo = (CardView)itemView.findViewById(R.id.card_view_product);
            productDescription = (TextView)itemView.findViewById(R.id.tv_product_description);
            pricePromotion = (TextView)itemView.findViewById(R.id.tv_product_price_promotion);
            priceOriginal = (TextView)itemView.findViewById(R.id.tv_product_price);
            productPhoto = (ImageView)itemView.findViewById(R.id.iv_product_photo);
            amountOfTouch = (TextView)itemView.findViewById(R.id.tv_amount_touch);
            textTouch = (TextView)itemView.findViewById(R.id.tv_text_touch);
            amountOfTry = (TextView)itemView.findViewById(R.id.tv_amount_try);
            textTry = (TextView)itemView.findViewById(R.id.tv_text_try);
            amountOfBuy = (TextView)itemView.findViewById(R.id.tv_amount_buy);
            textBuy = (TextView)itemView.findViewById(R.id.tv_text_buy);
        }
    }

}