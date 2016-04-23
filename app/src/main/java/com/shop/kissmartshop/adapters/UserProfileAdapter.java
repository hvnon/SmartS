package com.shop.kissmartshop.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shop.kissmartshop.R;
import com.shop.kissmartshop.model.ListUserModel;
import com.shop.kissmartshop.model.UserModel;
import com.squareup.picasso.Picasso;

/**
 * Created by LENOVO on 4/12/2016.
 */
public class UserProfileAdapter extends RecyclerView.Adapter<UserProfileAdapter.UserViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(UserModel item);
    }

    private ListUserModel mListUsers;
    private Context mContext;

    private OnItemClickListener listener;

    public UserProfileAdapter(Context context, ListUserModel users, OnItemClickListener listener){
        this.mContext = context;
        this.mListUsers = users;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        if (mListUsers.getUsers() != null) {
            return mListUsers.getUsers().size();
        }
        return 0;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_profile, viewGroup, false);
        return new UserViewHolder(view);
    }

    public void updateData(ListUserModel lstUsers)
    {
        mListUsers = lstUsers;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final UserViewHolder userViewHolder, final int i) {
        userViewHolder.mTextViewUsername.setText(mListUsers.getUsers().get(i).getUsername());
        Picasso.with(mContext).load(mListUsers.getUsers().get(i).getProfile_pic()).into(userViewHolder.mImageViewUserPic);

        userViewHolder.mLinearLayoutUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(mListUsers.getUsers().get(i));
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewUsername;
        ImageView mImageViewUserPic;
        LinearLayout mLinearLayoutUserProfile;

        UserViewHolder(View itemView) {
            super(itemView);

            mTextViewUsername = (TextView)itemView.findViewById(R.id.tv_username);
            mImageViewUserPic = (ImageView)itemView.findViewById(R.id.iv_user_pic);
            mLinearLayoutUserProfile = (LinearLayout)itemView.findViewById(R.id.ln_user_profile);
        }
    }

}