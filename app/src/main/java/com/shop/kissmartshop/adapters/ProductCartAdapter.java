package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.model.SizeColorModel;
import com.shop.kissmartshop.utils.CommonUtils;

import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductCartAdapter extends RecyclerSwipeAdapter<ProductCartAdapter.ProductViewHolder> {

    private List<ProductCartTouchModel> mLstProducts;
    private Context mContext;

    public ProductCartAdapter(Context context, List<ProductCartTouchModel> products){
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
    public void onBindViewHolder(final ProductViewHolder productViewHolder, final int i) {
        productViewHolder.mTextViewProductDescription.setText(mLstProducts.get(i).getDescription());
        productViewHolder.mTextViewPricePromotion.setText(mLstProducts.get(i).getPricePromotion());
        productViewHolder.mTextViewPriceOriginal.setText(mLstProducts.get(i).getPriceOriginal());
        productViewHolder.mImageViewProductPhoto.setImageResource(mLstProducts.get(i).getPhotoId());


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

            productViewHolder.mLnSizeColor.addView(tvSizeColor);
        }

        productViewHolder.mTextViewPriceOriginal.setPaintFlags(productViewHolder.mTextViewPriceOriginal.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        productViewHolder.mSwipeLayoutProduct.setShowMode(SwipeLayout.ShowMode.PullOut);

        // Drag From Left
        productViewHolder.mSwipeLayoutProduct.addDrag(SwipeLayout.DragEdge.Left, productViewHolder.mSwipeLayoutProduct.findViewById(R.id.bottom_add_to_cart));

        productViewHolder.mSwipeLayoutProduct.addDrag(SwipeLayout.DragEdge.Right, productViewHolder.mSwipeLayoutProduct.findViewById(R.id.bottom_delete));

        productViewHolder.mLnBottomDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productViewHolder.mSwipeLayoutProduct.close(true);
                productViewHolder.mSwipeLayoutProduct.setSwipeEnabled(false);
                productViewHolder.mLnConfirmDelete.setVisibility(View.VISIBLE);
            }
        });

        productViewHolder.mTextViewRemoved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemManger.removeShownLayouts(productViewHolder.mSwipeLayoutProduct);
                mLstProducts.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i, mLstProducts.size());
                mItemManger.closeAllItems();
            }
        });

        productViewHolder.mTextViewUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productViewHolder.mSwipeLayoutProduct.close(true);
                productViewHolder.mSwipeLayoutProduct.setSwipeEnabled(true);
                productViewHolder.mLnConfirmDelete.setVisibility(View.GONE);
            }
        });
//        productViewHolder.swipeLayout.setRightSwipeEnabled(false);

//        // Handling different events when swiping
//        productViewHolder.mSwipeLayoutProduct.addSwipeListener(new SwipeLayout.SwipeListener() {
//            @Override
//            public void onClose(SwipeLayout layout) {
//                //when the SurfaceView totally cover the BottomView.
//            }
//
//            @Override
//            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
//                //you are swiping.
//            }
//
//            @Override
//            public void onStartOpen(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onOpen(SwipeLayout layout) {
//                //when the BottomView totally show.
//            }
//
//            @Override
//            public void onStartClose(SwipeLayout layout) {
//
//            }
//
//            @Override
//            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
//                //when user's hand released.
//            }
//        });

        mItemManger.bindView(productViewHolder.itemView, i);

    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_layout;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout mSwipeLayoutProduct;
        TextView mTextViewProductDescription;
        TextView mTextViewPricePromotion;
        TextView mTextViewPriceOriginal;
        ImageView mImageViewProductPhoto;
        LinearLayout mLnSizeColor;
        TextView mTextViewProdStatus;

        LinearLayout mLnBottomDelete;
        LinearLayout mLnConfirmDelete;
        TextView mTextViewRemoved;
        TextView mTextViewUndo;

        ProductViewHolder(View itemView) {
            super(itemView);
            mSwipeLayoutProduct = (SwipeLayout)itemView.findViewById(R.id.swipe_layout);
            mTextViewProductDescription = (TextView)itemView.findViewById(R.id.tv_product_description);
            mTextViewPricePromotion = (TextView)itemView.findViewById(R.id.tv_product_price_promotion);
            mTextViewPriceOriginal = (TextView)itemView.findViewById(R.id.tv_product_price);
            mImageViewProductPhoto = (ImageView)itemView.findViewById(R.id.iv_product_photo);
            mLnSizeColor = (LinearLayout)itemView.findViewById(R.id.ln_size_color);
            mTextViewProdStatus = (TextView)itemView.findViewById(R.id.tv_status);
            mLnConfirmDelete = (LinearLayout)itemView.findViewById(R.id.ln_confirm_delete);
            mLnBottomDelete = (LinearLayout)itemView.findViewById(R.id.bottom_delete);
            mTextViewRemoved = (TextView)itemView.findViewById(R.id.tv_remove);
            mTextViewUndo = (TextView)itemView.findViewById(R.id.tv_undo);
        }
    }

}