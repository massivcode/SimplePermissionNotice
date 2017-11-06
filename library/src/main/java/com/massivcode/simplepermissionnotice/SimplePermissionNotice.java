package com.massivcode.simplepermissionnotice;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v4.content.PermissionChecker;
import android.text.TextUtils;
import com.massivcode.simplepermissionnotice.constants.DangerousPermissions;
import com.massivcode.simplepermissionnotice.models.PermissionInfo;
import com.massivcode.simplepermissionnotice.models.UiConfig;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 10:25
 */

public class SimplePermissionNotice {

  private static SimplePermissionNotice sInstance = null;
  private LinkedHashMap<String, PermissionInfo> mPermissionSettings;
  private UiConfig mDialogUiConfig;

  @DrawableRes
  private int mItemDividerDrawableResourceId = -1;

  private SimplePermissionNotice(Context context, UiConfig.Builder builder,
      PermissionInfo... permissionInfos) {
    this(context, builder, -1, permissionInfos);
  }

  private SimplePermissionNotice(Context context, UiConfig.Builder builder,
      int itemDividerDrawableResourceId, PermissionInfo... permissionInfos) {
    mPermissionSettings = new LinkedHashMap<>();

    for (PermissionInfo each : permissionInfos) {
      String permissionCode = each.getPermissionCode();
      String permissionTitle = each.getPermissionTitle();

      if (TextUtils.isEmpty(permissionTitle)) {
        permissionTitle = context.getString(each.getPermissionTitleResourceId());
      }

      String permissionDesc = each.getPermissionDescription();

      if (TextUtils.isEmpty(permissionDesc)) {
        permissionDesc = context.getString(each.getPermissionDescriptionResourceId());
      }

      mPermissionSettings.put(permissionCode,
          new PermissionInfo(permissionCode, permissionTitle, permissionDesc,
              each.getPermissionIconResourceId(), each.isMandatoryPermission()));
    }

    mDialogUiConfig = builder.build();
    mItemDividerDrawableResourceId = itemDividerDrawableResourceId;
  }

  public static void init(Context context, UiConfig.Builder builder,
      PermissionInfo... permissionInfos) {
    if (sInstance == null) {
      sInstance = new SimplePermissionNotice(context, builder, permissionInfos);
    }
  }

  public static void init(Context context, UiConfig.Builder builder,
      int itemDividerDrawableResourceId, PermissionInfo... permissionInfos) {
    if (sInstance == null) {
      sInstance = new SimplePermissionNotice(context, builder, itemDividerDrawableResourceId,
          permissionInfos);
    }
  }

  public static SimplePermissionNotice getInstance() {
    if (sInstance == null) {
      throw new IllegalStateException("Not Initialized!");
    }

    return sInstance;
  }

  private ArrayList<ArrayList<PermissionInfo>> getPermissions() {
    ArrayList<PermissionInfo> mandatoryPermissions = new ArrayList<>();
    ArrayList<PermissionInfo> optionalPermissions = new ArrayList<>();

    for (PermissionInfo permissionInfo : mPermissionSettings.values()) {
      if (permissionInfo.isMandatoryPermission()) {
        mandatoryPermissions.add(permissionInfo);
      } else {
        optionalPermissions.add(permissionInfo);
      }
    }

    ArrayList<ArrayList<PermissionInfo>> results = new ArrayList<>();
    results.add(mandatoryPermissions);
    results.add(optionalPermissions);
    return results;
  }

  private ArrayList<ArrayList<PermissionInfo>> getPermissions(
      @DangerousPermissions String... permissionCodes) {
    ArrayList<PermissionInfo> mandatoryPermissions = new ArrayList<>();
    ArrayList<PermissionInfo> optionalPermissions = new ArrayList<>();

    for (String each : permissionCodes) {
      PermissionInfo permissionInfo = mPermissionSettings.get(each);

      if (permissionInfo == null) {
        continue;
      }

      if (permissionInfo.isMandatoryPermission()) {
        mandatoryPermissions.add(permissionInfo);
      } else {
        optionalPermissions.add(permissionInfo);
      }
    }

    ArrayList<ArrayList<PermissionInfo>> results = new ArrayList<>();
    results.add(mandatoryPermissions);
    results.add(optionalPermissions);
    return results;
  }

  public void showDialog(Activity activity, SimplePermissionNoticeCallback callback) {
    if (!checkSdkLevel(callback)) {
      return;
    }

    ArrayList<ArrayList<PermissionInfo>> permissions = getPermissions();

    if (checkPermissionGranted(permissions, activity)) {
      callback.onGranted();
      return;
    }

    FragmentManager fragmentManager = activity.getFragmentManager();

    SimplePermissionNoticeDialog dialog =
        createDialog(
            permissions.get(0),
            permissions.get(1),
            callback);

    dialog.show(fragmentManager, SimplePermissionNoticeDialog.class.getSimpleName());
  }

  public void showDialog(Activity activity, SimplePermissionNoticeCallback callback,
      @DangerousPermissions String... permissionCodes) {
    if (!checkSdkLevel(callback)) {
      return;
    }

    ArrayList<ArrayList<PermissionInfo>> permissions = getPermissions(permissionCodes);

    if (checkPermissionGranted(permissions, activity)) {
      callback.onGranted();
      return;
    }

    FragmentManager fragmentManager = activity.getFragmentManager();

    SimplePermissionNoticeDialog dialog = createDialog(
        permissions.get(0),
        permissions.get(1),
        callback);

    dialog.show(fragmentManager, SimplePermissionNoticeDialog.class.getSimpleName());
  }

  private SimplePermissionNoticeDialog createDialog(ArrayList<PermissionInfo> mandatoryPermissions,
      ArrayList<PermissionInfo> optionalPermissions,
      SimplePermissionNoticeCallback callback) {

    return SimplePermissionNoticeDialog.newInstance(mandatoryPermissions,
        optionalPermissions, mDialogUiConfig, mItemDividerDrawableResourceId, callback);
  }

  public void showActivity(Activity activity, SimplePermissionNoticeCallback callback) {
    if (!checkSdkLevel(callback)) {
      return;
    }

    ArrayList<ArrayList<PermissionInfo>> permissions = getPermissions();

    if (checkPermissionGranted(permissions, activity)) {
      callback.onGranted();
      return;
    }

    launchActivity(activity, permissions.get(0), permissions.get(1), -1, callback);
  }

  public void showActivity(Activity activity, SimplePermissionNoticeCallback callback,
      @DangerousPermissions String... permissionCodes) {
    if (!checkSdkLevel(callback)) {
      return;
    }

    ArrayList<ArrayList<PermissionInfo>> permissions = getPermissions(permissionCodes);

    if (checkPermissionGranted(permissions, activity)) {
      callback.onGranted();
      return;
    }
    launchActivity(activity, permissions.get(0), permissions.get(1), -1, callback);
  }

  public void showActivity(Activity activity, int activityLayoutResourceId,
      SimplePermissionNoticeCallback callback) {
    if (!checkSdkLevel(callback)) {
      return;
    }

    ArrayList<ArrayList<PermissionInfo>> permissions = getPermissions();

    if (checkPermissionGranted(permissions, activity)) {
      callback.onGranted();
      return;
    }

    launchActivity(activity, permissions.get(0), permissions.get(1), activityLayoutResourceId,
        callback);
  }

  public void showActivity(Activity activity, int activityLayoutResourceId,
      SimplePermissionNoticeCallback callback, @DangerousPermissions String... permissionCodes) {
    if (!checkSdkLevel(callback)) {
      return;
    }

    ArrayList<ArrayList<PermissionInfo>> permissions = getPermissions(permissionCodes);

    if (checkPermissionGranted(permissions, activity)) {
      callback.onGranted();
      return;
    }

    launchActivity(activity, permissions.get(0), permissions.get(1), activityLayoutResourceId,
        callback);
  }


  private void launchActivity(
      Activity activity,
      ArrayList<PermissionInfo> mandatoryPermissions,
      ArrayList<PermissionInfo> optionalPermissions,
      int activityLayoutResourceId,
      SimplePermissionNoticeCallback callback) {

    Intent intent = new Intent(activity, SimplePermissionNoticeActivity.class);

    intent.putExtra("mandatoryPermissions", mandatoryPermissions);
    intent.putExtra("optionalPermissions", optionalPermissions);
    intent.putExtra("uiConfig", mDialogUiConfig);
    intent.putExtra("dividerResourceId", mItemDividerDrawableResourceId);
    intent.putExtra("activityLayoutResourceId", activityLayoutResourceId);

    SimplePermissionNoticeActivity.startActivity(activity, intent, callback);
  }

  private boolean checkSdkLevel(SimplePermissionNoticeCallback callback) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
      callback.onUnderMarshmallow();
      return false;
    }

    return true;
  }

  private boolean checkPermissionGranted(ArrayList<ArrayList<PermissionInfo>> permissions,
      Activity activity) {

    for (int i = 0; i < permissions.size(); i++) {
      ArrayList<PermissionInfo> permissionInfoList = permissions.get(i);

      for (int j = 0; j < permissionInfoList.size(); j++) {
        PermissionInfo permissionInfo = permissionInfoList.get(j);

        if (PermissionChecker.checkSelfPermission(activity, permissionInfo.getPermissionCode())
            != PermissionChecker.PERMISSION_GRANTED) {
          return false;
        }

      }
    }

    return true;
  }
}
