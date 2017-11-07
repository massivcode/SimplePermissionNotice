package com.massivcode.simplepermissionnotice;

/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 11:22
 */

public interface SimplePermissionNoticeCallback {

  void onGranted();

  void onDismiss(String[] permissionCodes, String[] mandatoryPermissionCodes,
      String[] optionalPermissionCodes);

  void onUnderMarshmallow();
}
