package com.shop.kissmartshop.activities;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ProductCartTouchModel;
import com.shop.kissmartshop.utils.Constants;

public class PaymentActivity extends BaseActivity {

    LinearLayout mLinearLayoutSelectCard;
    RelativeLayout mRelativeLayoutProcessingResultCard;
    LinearLayout mLinearLayoutProcessingCard;
    LinearLayout mLinearLayoutProcessingResult;

    ImageView mImageViewVisa;
    ImageView mImageViewMaestro;
    ImageView mImageViewMastercard;
    ImageView mImageViewAmericanExpress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mLinearLayoutSelectCard = (LinearLayout)findViewById(R.id.ln_select_card);
        mRelativeLayoutProcessingResultCard = (RelativeLayout)findViewById(R.id.rl_processing_result);
        mLinearLayoutProcessingCard = (LinearLayout)findViewById(R.id.ln_processing_card);
        mLinearLayoutProcessingResult = (LinearLayout)findViewById(R.id.ln_result_processing_card);

        mImageViewAmericanExpress = (ImageView)findViewById(R.id.iv_american_express);
        mImageViewAmericanExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingCard();
            }
        });

        mImageViewMaestro = (ImageView)findViewById(R.id.iv_maestro);
        mImageViewMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingCard();
            }
        });

        mImageViewMastercard = (ImageView)findViewById(R.id.iv_mastercard);
        mImageViewMastercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingCard();
            }
        });

        mImageViewVisa = (ImageView)findViewById(R.id.iv_visa);
        mImageViewVisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processingCard();
            }
        });
    }

    private void processingCard()
    {
        mLinearLayoutSelectCard.setVisibility(View.GONE);
        mRelativeLayoutProcessingResultCard.setVisibility(View.VISIBLE);

        mHanlderPaymentProcessing.sendEmptyMessageDelayed(0, 2000);
    }

    private Handler mHanlderPaymentProcessing = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mLinearLayoutProcessingCard.setVisibility(View.GONE);
            mLinearLayoutProcessingResult.setVisibility((View.VISIBLE));

            mHanlderPaymentComplete.sendEmptyMessageDelayed(0, 2000);
        }
    };

    private Handler mHanlderPaymentComplete = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            setResult(Constants.PAYMENT_SUCCESS);
            finish();
        }
    };
}
