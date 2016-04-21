package com.shop.kissmartshop.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.adapters.UserProfileAdapter;
import com.shop.kissmartshop.api.APIHelper;
import com.shop.kissmartshop.custom.SpacesItemDecoration;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserProfileActivity extends BaseActivity {

    private RecyclerView mRecyclerViewUserProfile;
    private ListUserModel mLstUsers;
    private UserProfileAdapter mUserAdapter;

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
                Intent iRecentActivities = new Intent(UserProfileActivity.this, RecentlyActivitiesActivity.class);
                startActivity(iRecentActivities);
            }
        });
        mRecyclerViewUserProfile.setAdapter(mUserAdapter);


    }

    private void initData()
    {
        mLstUsers = new ListUserModel();
        (new APIHelper()).getUsers(this, mHanlderGetUser);
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
}
