package com.shop.kissmartshop.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.shop.kissmartshop.R;
import com.shop.kissmartshop.adapters.UserProfileAdapter;
import com.shop.kissmartshop.api.APIHelper;
import com.shop.kissmartshop.custom.SpacesItemDecoration;
import com.shop.kissmartshop.gcm.QuickstartPreferences;
import com.shop.kissmartshop.gcm.ShopRegistrationIntentService;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.UserModel;
import com.shop.kissmartshop.utils.AlertDialogUtiils;
import com.shop.kissmartshop.utils.CommonUtils;
import com.shop.kissmartshop.utils.ProgressDialogUtils;

public class UserProfileActivity extends BaseActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private RecyclerView mRecyclerViewUserProfile;
    private ListUserModel mLstUsers;
    private UserProfileAdapter mUserAdapter;
    private ProgressDialogUtils mProgressDialogUtils;

    private APIHelper mAPIHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        setTitle(getString(R.string.title_activity_user_profile));
        hideActionButton();

        initData();

        mRecyclerViewUserProfile = (RecyclerView)findViewById(R.id.recycler_view_list_user);
        GridLayoutManager gridUserProfile = new GridLayoutManager(this, 3);
        mRecyclerViewUserProfile.setLayoutManager(gridUserProfile);
        mRecyclerViewUserProfile.setHasFixedSize(true);
        mRecyclerViewUserProfile.addItemDecoration(new SpacesItemDecoration(20));

        mUserAdapter = new UserProfileAdapter(this, mLstUsers, new UserProfileAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UserModel item) {
                CommonUtils.sUserId = item.getUser_id();
                mAPIHelper.updateUserToken(mContext, mHanlderUpdateUser);
            }
        });
        mRecyclerViewUserProfile.setAdapter(mUserAdapter);


        if (checkPlayServices()) {
            mProgressDialogUtils = new ProgressDialogUtils(this, "", getString(R.string.connecting));
            mProgressDialogUtils.show();
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, ShopRegistrationIntentService.class);
            startService(intent);
        }
    }

    private void initData()
    {
        mLstUsers = new ListUserModel();
        mAPIHelper = new APIHelper();
        mAPIHelper.getUsers(this, mHanlderGetUser);
        mAPIHelper.getStaffs(this);
//        List<UserModel> userModels = new ArrayList<>();
//        for(int i=0; i <7; i++) {
//            UserModel user = new UserModel();
//            user.setUsername("Huy Le");
//            user.setProfile_pic("http://hoa.do/merlin/img/customer/Abela.jpg");
//            user.photoId = R.drawable.slider1;
//            userModels.add(user);
//        }
//        mLstUsers.setUsers(userModels);
    }

    private Handler mHanlderGetUser = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mLstUsers = (ListUserModel)msg.obj;
            mUserAdapter.updateData(mLstUsers);
        }
    };

    private Handler mHanlderUpdateUser = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Intent iRecentActivities = new Intent(UserProfileActivity.this, RecentlyActivitiesActivity.class);
            startActivity(iRecentActivities);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }

    private BroadcastReceiver mRegistrationBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            SharedPreferences sharedPreferences =
//                    PreferenceManager.getDefaultSharedPreferences(context);
//            boolean sentToken = sharedPreferences
//                    .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);

//            if (sentToken) {
//                mAPIHelper.updateUserToken(mContext, mHanlderUpdateUser);
//            }

            if (mProgressDialogUtils != null) {
                mProgressDialogUtils.hide();
            }
        }
    };

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                //Show message here inform user this device doesn't support play service
                new AlertDialogUtiils().showDialog(mContext, getString(R.string.unsupport_play_service));
            }
            return false;
        }
        return true;
    }
}
