package com.massivcode.simplepermissionnotice.models;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.massivcode.simplepermissionnotice.constants.DangerousPermissions;

import java.io.Serializable;

/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 10:20
 */

public class PermissionInfo implements Serializable {
    @DangerousPermissions
    private String permissionCode;
    private String permissionTitle;
    @StringRes
    private int permissionTitleResourceId;
    private String permissionDescription;
    @StringRes
    private int permissionDescriptionResourceId;
    @DrawableRes
    private int permissionIconResourceId;
    private boolean isMandatoryPermission;

    public PermissionInfo(@NonNull String permissionCode, String permissionTitle, String permissionDescription, int permissionIconResourceId, boolean isMandatoryPermission) {
        this.permissionCode = permissionCode;
        this.permissionTitle = permissionTitle;
        this.permissionDescription = permissionDescription;
        this.permissionIconResourceId = permissionIconResourceId;
        this.isMandatoryPermission = isMandatoryPermission;
    }

    public PermissionInfo(@NonNull String permissionCode, int permissionTitleResourceId, int permissionDescriptionResourceId, int permissionIconResourceId, boolean isMandatorypermission) {
        this.permissionCode = permissionCode;
        this.permissionTitleResourceId = permissionTitleResourceId;
        this.permissionDescriptionResourceId = permissionDescriptionResourceId;
        this.permissionIconResourceId = permissionIconResourceId;
        this.isMandatoryPermission = isMandatorypermission;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionTitle() {
        return permissionTitle;
    }

    public void setPermissionTitle(String permissionTitle) {
        this.permissionTitle = permissionTitle;
    }

    public void setPermissionTitle(Context context) {
        this.permissionTitle = context.getString(this.permissionTitleResourceId);
    }

    public int getPermissionTitleResourceId() {
        return permissionTitleResourceId;
    }

    public void setPermissionTitleResourceId(int permissionTitleResourceId) {
        this.permissionTitleResourceId = permissionTitleResourceId;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public void setPermissionDescription(Context context) {
        this.permissionDescription = context.getString(this.permissionDescriptionResourceId);
    }

    public int getPermissionDescriptionResourceId() {
        return permissionDescriptionResourceId;
    }

    public void setPermissionDescriptionResourceId(int permissionDescriptionResourceId) {
        this.permissionDescriptionResourceId = permissionDescriptionResourceId;
    }

    public int getPermissionIconResourceId() {
        return permissionIconResourceId;
    }

    public void setPermissionIconResourceId(int permissionIconResourceId) {
        this.permissionIconResourceId = permissionIconResourceId;
    }

    public boolean isMandatoryPermission() {
        return isMandatoryPermission;
    }

    public void setMandatoryPermission(boolean mandatoryPermission) {
        isMandatoryPermission = mandatoryPermission;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PermissionInfo{");
        sb.append("permissionCode='").append(permissionCode).append('\'');
        sb.append(", permissionTitle='").append(permissionTitle).append('\'');
        sb.append(", permissionTitleResourceId=").append(permissionTitleResourceId);
        sb.append(", permissionDescription='").append(permissionDescription).append('\'');
        sb.append(", permissionDescriptionResourceId=").append(permissionDescriptionResourceId);
        sb.append(", permissionIconResourceId=").append(permissionIconResourceId);
        sb.append(", isMandatoryPermission=").append(isMandatoryPermission);
        sb.append('}');
        return sb.toString();
    }
}
