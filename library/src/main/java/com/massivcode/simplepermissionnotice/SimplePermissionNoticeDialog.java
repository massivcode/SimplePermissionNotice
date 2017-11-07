package com.massivcode.simplepermissionnotice;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.massivcode.simplepermissionnotice.adapters.PermissionInfoAdapter;
import com.massivcode.simplepermissionnotice.models.PermissionInfo;
import com.massivcode.simplepermissionnotice.models.UiConfig;

import java.util.ArrayList;

/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 11:26
 */

@SuppressWarnings("unchecked")
public class SimplePermissionNoticeDialog extends DialogFragment {

  private ArrayList<PermissionInfo> mMandatoryPermissions;
  private ArrayList<PermissionInfo> mOptionalPermissions;
  private String[] mMandatoryPermissionCodes;
  private String[] mOptionalPermissionCodes;
  private String[] mPermissionCodes;
  private UiConfig mDialogUiConfig;
  private int mItemDividerDrawableResourceId;
  private static SimplePermissionNoticeCallback sCallback;

  private LinearLayout mRootContainer;
  private LinearLayout mMandatoryPermissionContainer;
  private TextView mMandatoryPermissionTitleTextView;
  private RecyclerView mMandatoryPermissionRecyclerView;
  private LinearLayout mOptionalPermissionContainer;
  private TextView mOptionalPermissionTitleTextView;
  private RecyclerView mOptionalPermissionRecyclerView;
  private TextView mOkTextView;

  public static SimplePermissionNoticeDialog newInstance(
      ArrayList<PermissionInfo> mandatoryPermissions,
      ArrayList<PermissionInfo> optionalPermissions,
      UiConfig dialogUiConfig,
      int itemDividerDrawableResourceId,
      SimplePermissionNoticeCallback callback) {
    SimplePermissionNoticeDialog dialog = new SimplePermissionNoticeDialog();

    Bundle args = new Bundle();
    args.putSerializable("mandatoryPermissions", mandatoryPermissions);
    args.putSerializable("optionalPermissions", optionalPermissions);
    args.putSerializable("uiConfig", dialogUiConfig);
    args.putInt("dividerResourceId", itemDividerDrawableResourceId);
    dialog.setArguments(args);

    sCallback = callback;
    return dialog;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setCancelable(false);

    Bundle args = getArguments();

    if (savedInstanceState != null) {
      args = savedInstanceState;
    }

    mMandatoryPermissions = (ArrayList<PermissionInfo>) args
        .getSerializable("mandatoryPermissions");
    mOptionalPermissions = (ArrayList<PermissionInfo>) args.getSerializable("optionalPermissions");
    mDialogUiConfig = (UiConfig) args.getSerializable("uiConfig");
    mItemDividerDrawableResourceId = args.getInt("dividerResourceId", -1);

    makeCallbackArrays();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putSerializable("mandatoryPermissions", mMandatoryPermissions);
    outState.putSerializable("optionalPermissions", mOptionalPermissions);
    outState.putSerializable("uiConfig", mDialogUiConfig);
    outState.putInt("dividerResourceId", mItemDividerDrawableResourceId);
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
    System.arraycopy(mOptionalPermissionCodes, 0, mPermissionCodes, mandatoryPermissionLength,
        optionalPermissionLength);
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
    return inflater.inflate(R.layout.dialog_simple_permission_notice, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    mRootContainer = (LinearLayout) view.findViewById(R.id.root_container);
    mMandatoryPermissionContainer = (LinearLayout) view.findViewById(R.id.mandatory_container_ll);
    mMandatoryPermissionTitleTextView = (TextView) view.findViewById(R.id.mandatory_title_tv);
    mMandatoryPermissionRecyclerView = (RecyclerView) view.findViewById(R.id.mandatory_rv);
    mOptionalPermissionContainer = (LinearLayout) view.findViewById(R.id.optional_container_ll);
    mOptionalPermissionTitleTextView = (TextView) view.findViewById(R.id.optional_title_tv);
    mOptionalPermissionRecyclerView = (RecyclerView) view.findViewById(R.id.optional_rv);
    mOkTextView = (TextView) view.findViewById(R.id.ok_tv);

    init();
  }

  private void init() {
    mRootContainer.setBackgroundColor(mDialogUiConfig.getBackgroundColor());
    mMandatoryPermissionTitleTextView.setText(mDialogUiConfig.getMandatoryPermissionTitle());
    mMandatoryPermissionTitleTextView.setTextColor(mDialogUiConfig.getTitleColor());
    mOptionalPermissionTitleTextView.setText(mDialogUiConfig.getOptionalPermissionTitle());
    mOptionalPermissionTitleTextView.setTextColor(mDialogUiConfig.getTitleColor());

    mOkTextView.setOnClickListener(mOnOkClickListener);
    mOkTextView.setTextColor(mDialogUiConfig.getOkColor());
    mOkTextView.setText(mDialogUiConfig.getOkString());

    setPermissionList(mMandatoryPermissions, mMandatoryPermissionContainer,
        mMandatoryPermissionRecyclerView);
    setPermissionList(mOptionalPermissions, mOptionalPermissionContainer,
        mOptionalPermissionRecyclerView);
  }

  private void setPermissionList(ArrayList<PermissionInfo> target, LinearLayout container,
      RecyclerView recyclerView) {
    boolean isEmpty = target == null || target.isEmpty();

    if (isEmpty) {
      container.setVisibility(View.GONE);
    } else {
      container.setVisibility(View.VISIBLE);

      PermissionInfoAdapter adapter = new PermissionInfoAdapter(
          mDialogUiConfig.getItemLayoutResourceId(), target);
      recyclerView.setAdapter(adapter);

      if (mItemDividerDrawableResourceId != -1) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),
            DividerItemDecoration.VERTICAL);
        dividerItemDecoration
            .setDrawable(ContextCompat.getDrawable(getActivity(), mItemDividerDrawableResourceId));
        recyclerView.addItemDecoration(dividerItemDecoration);
      }

      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

  }

  private View.OnClickListener mOnOkClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      if (sCallback != null) {
        sCallback
            .onDismiss(mPermissionCodes, mMandatoryPermissionCodes, mOptionalPermissionCodes);
        dismiss();
      }
    }
  };

  @Override
  public void onDestroyView() {
    if (getDialog() != null && getRetainInstance()) {
      getDialog().setDismissMessage(null);
    }
    super.onDestroyView();
  }
}
