package com.shop.kissmartshop.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.shop.kissmartshop.R;

/**
 * Created by LENOVO on 4/23/2016.
 */
public class AlertDialogUtiils {

    private AlertDialog mAlertDialog;
    private Dialog mStaffDialog;

    public void showDialog(Context context,String message)
    {

        mAlertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.title_alert_dialog))
                .setMessage(message)
                .setNegativeButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

    }

    public void showDialogStaffInform(Context context, String message)
    {
        mStaffDialog = new Dialog(context);
        mStaffDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mStaffDialog.setContentView(R.layout.item_dialog_staff_inform);
        int height = mStaffDialog.getWindow().getAttributes().height;
        mStaffDialog.getWindow().setLayout(getWidthWindow((Activity)context), height);
        Button dialogButton = (Button) mStaffDialog.findViewById(R.id.btn_ok);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mStaffDialog.dismiss();
            }
        });

        mStaffDialog.show();
    }

    private int getWidthWindow(Activity context)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        Display display = context.getWindowManager().getDefaultDisplay();
        display.getMetrics(metrics);
        return (metrics.widthPixels*90/100);
    }
}
