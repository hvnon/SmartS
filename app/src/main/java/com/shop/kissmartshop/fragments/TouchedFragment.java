package com.shop.kissmartshop.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.activities.TouchCartActivity;
import com.shop.kissmartshop.adapters.ProductTouchAdapter;
import com.shop.kissmartshop.custom.SimpleDividerItemDecoration;
import com.shop.kissmartshop.data.DatabaseHelper;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.model.SizeColorModel;
import com.shop.kissmartshop.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/13/2016.
 */
public class TouchedFragment extends Fragment {

    private List<ProductCartTouchModel> mListProductInTouched;

    private RecyclerView mRecyclerViewProductTouched;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_touched, container, false);

        mRecyclerViewProductTouched = (RecyclerView)view.findViewById(R.id.recycler_view_list_product_touched);
        mRecyclerViewProductTouched.setHasFixedSize(true);
        mRecyclerViewProductTouched.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        LinearLayoutManager customLayout = new LinearLayoutManager(getActivity());
        mRecyclerViewProductTouched.setLayoutManager(customLayout);

        ProductTouchAdapter adapter = new ProductTouchAdapter(getActivity(), mListProductInTouched);
        mRecyclerViewProductTouched.setAdapter(adapter);

        return view;
    }


    private void initializeData(){
        mListProductInTouched = new ArrayList<>();

        List<SizeColorModel> lstColorSizes = new ArrayList<>();
        SizeColorModel sizeColor1 = new SizeColorModel();
        sizeColor1.setColor("#111111");
        sizeColor1.setSize("12");
        lstColorSizes.add(sizeColor1);

        SizeColorModel sizeColor2 = new SizeColorModel();
        sizeColor2.setColor("#1122ee");
        sizeColor2.setSize("10");
        lstColorSizes.add(sizeColor2);

        ProductCartTouchModel model1 = new ProductCartTouchModel("1", "Fashionable Men's Athletic Shoes With Color Matching and Letter", "$14.4", "$12.3", R.drawable.example, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
        mListProductInTouched.add(model1);

        ProductCartTouchModel model2= new ProductCartTouchModel("2", "Fashionable Men's Athletic Shoes With Color Matching and Letter", "$12.4", "$21.2", R.drawable.example1, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
        mListProductInTouched.add(model2);

        ProductCartTouchModel model3 = new ProductCartTouchModel("3", "Fashionable Men's Athletic Shoes With Color Matching and Letter", "$12.5", "$23.1", R.drawable.example, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
        mListProductInTouched.add(model3);

        Dao<ProductCartTouchModel, Integer> productCartTouchDao;
        try {
            productCartTouchDao =((TouchCartActivity)getActivity()).getHelper().getProductCartTouchDao();
            productCartTouchDao.createOrUpdate(model1);
            productCartTouchDao.createOrUpdate(model2);
            productCartTouchDao.createOrUpdate(model3);
        }catch (Exception e){
             e.printStackTrace();
        }
    }


}
