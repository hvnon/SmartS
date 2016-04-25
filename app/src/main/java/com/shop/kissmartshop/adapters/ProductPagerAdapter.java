package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.utils.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductPagerAdapter extends PagerAdapter {

//    private List<String> mListProducts;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mListProductPhotos;
    private int mSourceToLoadImage;

    public ProductPagerAdapter(Context context, List<String> lstProductPhotos, int sourceImage){
        mContext = context;
        this.mListProductPhotos = new ArrayList<>();
        this.mListProductPhotos.addAll(lstProductPhotos);
        mLayoutInflater  = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSourceToLoadImage = sourceImage;
    }

    @Override
    public int getCount() {
        return mListProductPhotos.size();//mListProducts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_pager_product, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        if (mSourceToLoadImage == Constants.LOAD_IMAGE_FROM_URL) {
            if (!mListProductPhotos.get(position).equalsIgnoreCase("")) {
                Picasso.with(mContext).load(mListProductPhotos.get(position)).into(imageView);
            }
        } else {
            Integer resId = Integer.parseInt(mListProductPhotos.get(position));
            imageView.setImageResource(resId);
        }

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
