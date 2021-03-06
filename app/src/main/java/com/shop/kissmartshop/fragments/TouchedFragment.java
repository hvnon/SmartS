package com.shop.kissmartshop.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.adapters.ProductTouchAdapter;
import com.shop.kissmartshop.api.APIHelper;
import com.shop.kissmartshop.custom.SimpleDividerItemDecoration;
import com.shop.kissmartshop.model.ColorModel;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.model.ProductModel;
import com.shop.kissmartshop.model.ProductPickupModel;
import com.shop.kissmartshop.model.ProductTouchedModel;
import com.shop.kissmartshop.model.SizeColorModel;
import com.shop.kissmartshop.model.SizeModel;
import com.shop.kissmartshop.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 4/13/2016.
 */
public class TouchedFragment extends Fragment {

    private List<ProductCartTouchModel> mListProductInTouched;

    private RecyclerView mRecyclerViewProductTouched;
    private ProductTouchAdapter mProductAdapter;

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

        mProductAdapter = new ProductTouchAdapter(getActivity(), mListProductInTouched);
        mRecyclerViewProductTouched.setAdapter(mProductAdapter);

        return view;
    }


    private void initializeData(){
        mListProductInTouched = new ArrayList<>();
        (new APIHelper()).getTouchedProducts(getActivity(), mHanlderGetProduct);

//        List<SizeColorModel> lstColorSizes = new ArrayList<>();
//        SizeColorModel sizeColor1 = new SizeColorModel();
//        sizeColor1.setColor("#111111");
//        sizeColor1.setSize("12");
//        lstColorSizes.add(sizeColor1);
//
//        SizeColorModel sizeColor2 = new SizeColorModel();
//        sizeColor2.setColor("#1122ee");
//        sizeColor2.setSize("10");
//        lstColorSizes.add(sizeColor2);

//        ProductCartTouchModel model1 = new ProductCartTouchModel("1", "Fashionable Men's Athletic Shoes With Color Matching and Letter", "14.4", "12.3", R.drawable.shoes10, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
//        mListProductInTouched.add(model1);
//
//        ProductCartTouchModel model2= new ProductCartTouchModel("2", "Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.4", "21.2", R.drawable.shoes11, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
//        mListProductInTouched.add(model2);
//
//        ProductCartTouchModel model3 = new ProductCartTouchModel("3", "Fashionable Men's Athletic Shoes With Color Matching and Letter", "12.5", "23.1", R.drawable.shoes12, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
//        mListProductInTouched.add(model3);

//        Dao<ProductCartTouchModel, Integer> productCartTouchDao;
//        try {
//            productCartTouchDao =((TouchCartActivity)getActivity()).getHelper().getProductCartTouchDao();
//            productCartTouchDao.createOrUpdate(model1);
//            productCartTouchDao.createOrUpdate(model2);
//            productCartTouchDao.createOrUpdate(model3);
//        }catch (Exception e){
//             e.printStackTrace();
//        }
    }

    private Handler mHanlderGetProduct = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ProductTouchedModel touchedProduct = (ProductTouchedModel)msg.obj;
            for(ProductPickupModel prodPickup : touchedProduct.getPickups()) {
                for(ProductModel prod : prodPickup.getProducts()) {
                    List<SizeColorModel> lstColorSizes = new ArrayList<>();
//                    for(ColorModel color : prod.getColors()) {
//                        SizeColorModel sizeColor = new SizeColorModel();
//                        sizeColor.setColor(color.getColor_hex());
//
//                        List<SizeModel> size = color.getSizes();
//                        if(size != null && size.size() > 0) {
//                            sizeColor.setSize(size.get(0).getSize());
//                        }
//
//                        lstColorSizes.add(sizeColor);
//                    }
                    double priceOriginal = Double.parseDouble(prod.getPrice()) + 5;
                    ProductCartTouchModel model = new ProductCartTouchModel(prod.getProduct_name(), prod.getPrice(), String.format("%.2f", priceOriginal), R.drawable.shoes10, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
                    model.setProduct_id(prod.getProduct_id());
                    model.setImage(prod.getImage());
                    model.setProduct_name(prod.getProduct_name());
                    model.setBluestone(prod.getBluestone());
                    mListProductInTouched.add(model);
                }

            }
            mProductAdapter.updateData(mListProductInTouched);

//            for (ProductModel product : products.getProducts()) {
//
//                List<SizeColorModel> lstColorSizes = new ArrayList<>();
//                SizeColorModel sizeColor1 = new SizeColorModel();
//                sizeColor1.setColor("#111111");
//                sizeColor1.setSize("12");
//                lstColorSizes.add(sizeColor1);
//
//                SizeColorModel sizeColor2 = new SizeColorModel();
//                sizeColor2.setColor("#1122ee");
//                sizeColor2.setSize("10");
//                lstColorSizes.add(sizeColor2);
//
//                ProductCartTouchModel model = new ProductCartTouchModel(product.getProduct_name(), product.getPrice(), "12.3", R.drawable.shoes10, lstColorSizes, Constants.PRODUCT_STATUS_NOTHING);
//                model.setProduct_id(product.getProduct_id());
//                model.setImage(product.getImage());
//                model.setProduct_name(product.getProduct_name());
//                mListProductInTouched.add(model);
//            }

        }
    };

}
