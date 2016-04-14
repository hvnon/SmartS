package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.model.ProductRecentActivitiesModel;
import com.shop.kissmartshop.model.SizeColorModel;
import com.shop.kissmartshop.utils.CommonUtils;
import com.shop.kissmartshop.utils.Constants;

import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductTouchCartAdapter extends RecyclerView.Adapter<ProductTouchCartAdapter.ProductViewHolder>{

    private List<ProductCartTouchModel> mLstProducts;
    private Context mContext;

    public ProductTouchCartAdapter(Context context, List<ProductCartTouchModel> products){
        this.mContext = context;
        this.mLstProducts = products;
    }

    @Override
    public int getItemCount() {
        return mLstProducts.size();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_product_touched_cart, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        productViewHolder.productDescription.setText(mLstProducts.get(i).getDescription());
        productViewHolder.pricePromotion.setText(mLstProducts.get(i).getPricePromotion());
        productViewHolder.priceOriginal.setText(mLstProducts.get(i).getPriceOriginal());
        productViewHolder.productPhoto.setImageResource(mLstProducts.get(i).getPhotoId());


        List<SizeColorModel> lstSizeColors = mLstProducts.get(i).getLstSizeColors();
        for(SizeColorModel mode : lstSizeColors) {
            TextView tvSizeColor = new TextView(mContext);
            tvSizeColor.setText(mode.getSize());
            tvSizeColor.setTextColor(mContext.getResources().getColor(R.color.white));
            tvSizeColor.setBackgroundResource(R.drawable.circle);
            tvSizeColor.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(mode.getColor())));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtils.dpToPx(mContext, 25), CommonUtils.dpToPx(mContext, 25));
            tvSizeColor.setGravity(Gravity.CENTER);
            params.rightMargin = CommonUtils.dpToPx(mContext, 10);
            tvSizeColor.setLayoutParams(params);

            productViewHolder.lnSizeColor.addView(tvSizeColor);
        }

        productViewHolder.priceOriginal.setPaintFlags(productViewHolder.priceOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        productViewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag From Left
        productViewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, productViewHolder.swipeLayout.findViewById(R.id.bottom_add_to_cart));

        productViewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Right, productViewHolder.swipeLayout.findViewById(R.id.bottom_delete));

//        productViewHolder.swipeLayout.setRightSwipeEnabled(false);

        // Handling different events when swiping
        productViewHolder.swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView productDescription;
        TextView pricePromotion;
        TextView priceOriginal;
        ImageView productPhoto;
        LinearLayout lnSizeColor;
        TextView prodStatus;

        ProductViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout)itemView.findViewById(R.id.swipe_layout);
            productDescription = (TextView)itemView.findViewById(R.id.tv_product_description);
            pricePromotion = (TextView)itemView.findViewById(R.id.tv_product_price_promotion);
            priceOriginal = (TextView)itemView.findViewById(R.id.tv_product_price);
            productPhoto = (ImageView)itemView.findViewById(R.id.iv_product_photo);
            lnSizeColor = (LinearLayout)itemView.findViewById(R.id.ln_size_color);
            prodStatus = (TextView)itemView.findViewById(R.id.tv_status);
        }
    }

}