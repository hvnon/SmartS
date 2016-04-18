package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.kissmartshop.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductPagerAdapter extends PagerAdapter {

//    private List<String> mListProducts;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<Integer> mListProductPhotos;

    public ProductPagerAdapter(Context context, List<Integer> lstProductPhotos){
        mContext = context;
        mListProductPhotos = new ArrayList<>();
        mListProductPhotos.addAll(lstProductPhotos);
        mLayoutInflater  = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        imageView.setImageResource(mListProductPhotos.get(position));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
