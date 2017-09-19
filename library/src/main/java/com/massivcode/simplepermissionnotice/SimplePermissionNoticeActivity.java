package com.massivcode.simplepermissionnotice;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.massivcode.simplepermissionnotice.adapters.PermissionInfoAdapter;
import com.massivcode.simplepermissionnotice.models.PermissionInfo;
import com.massivcode.simplepermissionnotice.models.UiConfig;

import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class SimplePermissionNoticeActivity extends AppCompatActivity {
    private ArrayList<PermissionInfo> mMandatoryPermissions;
    private ArrayList<PermissionInfo> mOptionalPermissions;
    private String[] mMandatoryPermissionCodes;
    private String[] mOptionalPermissionCodes;
    private String[] mPermissionCodes;
    private UiConfig mDialogUiConfig;
    private int mItemDividerDrawableResourceId;
    private int mActivityLayoutResourceId;

    private LinearLayout mRootContainer;
    private LinearLayout mMandatoryPermissionContainer;
    private TextView mMandatoryPermissionTitleTextView;
    private RecyclerView mMandatoryPermissionRecyclerView;
    private LinearLayout mOptionalPermissionContainer;
    private TextView mOptionalPermissionTitleTextView;
    private RecyclerView mOptionalPermissionRecyclerView;
    private TextView mOkTextView;

    private static SimplePermissionNoticeCallback sCallback;

    public static void startActivity(Activity activity, Intent intent, SimplePermissionNoticeCallback callback) {
        activity.startActivity(intent);
        sCallback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setup(savedInstanceState);
    }

    private void setup(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mMandatoryPermissions = (ArrayList<PermissionInfo>) savedInstanceState.getSerializable("mandatoryPermissions");
            mOptionalPermissions = (ArrayList<PermissionInfo>) savedInstanceState.getSerializable("optionalPermissions");
            mDialogUiConfig = (UiConfig) savedInstanceState.getSerializable("uiConfig");
            mItemDividerDrawableResourceId = savedInstanceState.getInt("dividerResourceId", -1);
            mActivityLayoutResourceId = savedInstanceState.getInt("activityLayoutResourceId", -1);
        } else {
            Intent intent = getIntent();
            mMandatoryPermissions = (ArrayList<PermissionInfo>) intent.getSerializableExtra("mandatoryPermissions");
            mOptionalPermissions = (ArrayList<PermissionInfo>) intent.getSerializableExtra("optionalPermissions");
            mDialogUiConfig = (UiConfig) intent.getSerializableExtra("uiConfig");
            mItemDividerDrawableResourceId = intent.getIntExtra("dividerResourceId", -1);
            mActivityLayoutResourceId = intent.getIntExtra("activityLayoutResourceId", -1);
        }

        if (mActivityLayoutResourceId != -1) {
            setContentView(mActivityLayoutResourceId);
        } else {
            setContentView(R.layout.activity_simple_permission_notice);
        }

        Resources resources = getResources();
        mRootContainer = (LinearLayout) findViewById(resources.getIdentifier("root_container", "id", getPackageName()));
        mMandatoryPermissionContainer = (LinearLayout) findViewById(resources.getIdentifier("mandatory_container_ll", "id", getPackageName()));
        mMandatoryPermissionTitleTextView = (TextView) findViewById(resources.getIdentifier("mandatory_title_tv", "id", getPackageName()));
        mMandatoryPermissionRecyclerView = (RecyclerView) findViewById(resources.getIdentifier("mandatory_rv", "id", getPackageName()));
        mOptionalPermissionContainer = (LinearLayout) findViewById(resources.getIdentifier("optional_container_ll", "id", getPackageName()));
        mOptionalPermissionTitleTextView = (TextView) findViewById(resources.getIdentifier("optional_title_tv", "id", getPackageName()));
        mOptionalPermissionRecyclerView = (RecyclerView) findViewById(resources.getIdentifier("optional_rv", "id", getPackageName()));
        mOkTextView = (TextView) findViewById(resources.getIdentifier("ok_tv", "id", getPackageName()));

        makeCallbackArrays();
    }

    private void makeCallbackArrays() {
        mMandatoryPermissionCodes = new String[mMandatoryPermissions.size()];
        mOptionalPermissionCodes = new String[mOptionalPermissions.size()];
        mPermissionCodes = new String[mMandatoryPermissions.size() + mOptionalPermissions.size()];

        for (int i = 0; i < mMandatoryPermissions.size(); i++) {
            String permissionCode = mMandatoryPermissions.get(i).getPermissionCode();
            mMandatoryPermissionCodes[i] = permissionCode;
        }

        for (int i = 0; i < mOptionalPermissions.size(); i++) {
            String permissionCode = mOptionalPermissions.get(i).getPermissionCode();
            mOptionalPermissionCodes[i] = permissionCode;
        }

        int mandatoryPermissionLength = mMandatoryPermissionCodes.length;
        int optionalPermissionLength = mOptionalPermissionCodes.length;

        System.arraycopy(mMandatoryPermissionCodes, 0, mPermissionCodes, 0, mandatoryPermissionLength);
        System.arraycopy(mOptionalPermissionCodes, 0, mPermissionCodes, mandatoryPermissionLength, optionalPermissionLength);

        initViews();
    }

    private void initViews() {
        mRootContainer.setBackgroundColor(mDialogUiConfig.getBackgroundColor());
        mMandatoryPermissionTitleTextView.setText(mDialogUiConfig.getMandatoryPermissionTitle());
        mMandatoryPermissionTitleTextView.setTextColor(mDialogUiConfig.getTitleColor());
        mOptionalPermissionTitleTextView.setText(mDialogUiConfig.getOptionalPermissionTitle());
        mOptionalPermissionTitleTextView.setTextColor(mDialogUiConfig.getTitleColor());

        mOkTextView.setOnClickListener(mOnOkClickListener);
        mOkTextView.setTextColor(mDialogUiConfig.getOkColor());
        mOkTextView.setText(mDialogUiConfig.getOkString());

        setPermissionList(mMandatoryPermissions, mMandatoryPermissionContainer, mMandatoryPermissionRecyclerView);
        setPermissionList(mOptionalPermissions, mOptionalPermissionContainer, mOptionalPermissionRecyclerView);
    }

    private boolean setPermissionList(ArrayList<PermissionInfo> target, LinearLayout container, RecyclerView recyclerView) {
        boolean isEmpty = target == null;

        if (isEmpty) {
            container.setVisibility(View.GONE);
        } else {
            container.setVisibility(View.VISIBLE);

            PermissionInfoAdapter adapter = new PermissionInfoAdapter(mDialogUiConfig.getItemLayoutResourceId(), target);
            recyclerView.setAdapter(adapter);


            if (mItemDividerDrawableResourceId != -1) {
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(SimplePermissionNoticeActivity.this, DividerItemDecoration.VERTICAL);
                dividerItemDecoration.setDrawable(ContextCompat.getDrawable(SimplePermissionNoticeActivity.this, mItemDividerDrawableResourceId));
                recyclerView.addItemDecoration(dividerItemDecoration);
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(SimplePermissionNoticeActivity.this));
        }

        return isEmpty;
    }

    private View.OnClickListener mOnOkClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (sCallback != null) {
                sCallback.onDismiss(mPermissionCodes, mMandatoryPermissionCodes, mOptionalPermissionCodes);
                finish();
            }
        }
    };

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("mandatoryPermissions", mMandatoryPermissions);
        outState.putSerializable("optionalPermissions", mOptionalPermissions);
        outState.putSerializable("uiConfig", mDialogUiConfig);
        outState.putInt("dividerResourceId", mItemDividerDrawableResourceId);
        outState.putInt("activityLayoutResourceId", mActivityLayoutResourceId);
    }

    @Override
    public void onBackPressed() {

    }
}
