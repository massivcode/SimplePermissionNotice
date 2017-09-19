package com.massivcode.simplepermissionnotice.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.massivcode.simplepermissionnotice.models.PermissionInfo;

import java.util.ArrayList;

/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 11:45
 */

public class PermissionInfoAdapter extends RecyclerView.Adapter<PermissionInfoAdapter.PermissionInfoViewHolder> {

    @LayoutRes
    private int mItemLayoutResourceId;

    private ArrayList<PermissionInfo> mData;
    private Context mContext;

    public PermissionInfoAdapter(int mItemLayoutResourceId, ArrayList<PermissionInfo> mData) {
        this.mItemLayoutResourceId = mItemLayoutResourceId;
        this.mData = mData;
    }

    @Override
    public PermissionInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return new PermissionInfoViewHolder(LayoutInflater.from(mContext).inflate(mItemLayoutResourceId, parent, false));
    }

    @Override
    public void onBindViewHolder(PermissionInfoViewHolder holder, int position) {
        PermissionInfo item = getItem(position);

        holder.mIconImageView.setImageResource(item.getPermissionIconResourceId());
        holder.mTitleTextView.setText(item.getPermissionTitle());
        holder.mDescriptionTextView.setText(item.getPermissionDescription());
    }

    public PermissionInfo getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class PermissionInfoViewHolder extends RecyclerView.ViewHolder {

        ImageView mIconImageView;
        TextView mTitleTextView, mDescriptionTextView;

        PermissionInfoViewHolder(View itemView) {
            super(itemView);

            int iconImageViewResourceId = mContext.getResources().getIdentifier("icon_iv", "id", mContext.getPackageName());
            int titleTextViewResourceId = mContext.getResources().getIdentifier("title_tv", "id", mContext.getPackageName());
            int descriptionTextViewResourceId = mContext.getResources().getIdentifier("description_tv", "id", mContext.getPackageName());

            mIconImageView = (ImageView) itemView.findViewById(iconImageViewResourceId);
            mTitleTextView = (TextView) itemView.findViewById(titleTextViewResourceId);
            mDescriptionTextView = (TextView) itemView.findViewById(descriptionTextViewResourceId);
        }
    }
}
