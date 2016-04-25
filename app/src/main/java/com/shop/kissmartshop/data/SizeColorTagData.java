package com.shop.kissmartshop.data;

import android.widget.TextView;

import com.shop.kissmartshop.model.SizeModel;

import java.util.List;

/**
 * Created by LENOVO on 4/24/2016.
 */
public class SizeColorTagData {
    TextView tvBgSizeColor;
    List<SizeModel> lstSizes;

    private String sizeSelected;
    private String sizeIdSelected;
    private String colorSelected;
    private String colorIdSelected;

    public TextView getTvBgSizeColor() {
        return tvBgSizeColor;
    }

    public void setTvBgSizeColor(TextView tvBgSizeColor) {
        this.tvBgSizeColor = tvBgSizeColor;
    }

    public List<SizeModel> getLstSizes() {
        return lstSizes;
    }

    public void setLstSizes(List<SizeModel> lstSizes) {
        this.lstSizes = lstSizes;
    }

    public String getSizeSelected() {
        return sizeSelected;
    }

    public void setSizeSelected(String sizeSelected) {
        this.sizeSelected = sizeSelected;
    }

    public String getSizeIdSelected() {
        return sizeIdSelected;
    }

    public void setSizeIdSelected(String sizeIdSelected) {
        this.sizeIdSelected = sizeIdSelected;
    }

    public String getColorSelected() {
        return colorSelected;
    }

    public void setColorSelected(String colorSelected) {
        this.colorSelected = colorSelected;
    }

    public String getColorIdSelected() {
        return colorIdSelected;
    }

    public void setColorIdSelected(String colorIdSelected) {
        this.colorIdSelected = colorIdSelected;
    }
}
