package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.shop.kissmartshop.R;

import java.util.List;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class ProductPagerAdapter extends PagerAdapter {

//    private List<String> mListProducts;
    int[] mListProducts = {R.drawable.example, R.drawable.example1};
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ProductPagerAdapter(Context context){
        mContext = context;
        mLayoutInflater  = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListProducts.length;//mListProducts.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_pager_product, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mListProducts[position]);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
