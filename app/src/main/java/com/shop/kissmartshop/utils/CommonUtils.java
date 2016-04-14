package com.shop.kissmartshop.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by LENOVO on 4/14/2016.
 */
public class CommonUtils {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
