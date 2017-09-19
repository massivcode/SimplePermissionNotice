package com.massivcode.simplepermissionnotice.models;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.massivcode.simplepermissionnotice.R;

import java.io.Serializable;

/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 10:57
 */

public class UiConfig implements Serializable {
    private String mandatoryPermissionTitle;
    private String optionalPermissionTitle;
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int titleColor;
    private String okString;
    @ColorInt
    private int okColor;
    @LayoutRes
    private int itemLayoutResourceId;

    public String getMandatoryPermissionTitle() {
        return mandatoryPermissionTitle;
    }

    public void setMandatoryPermissionTitle(String mandatoryPermissionTitle) {
        this.mandatoryPermissionTitle = mandatoryPermissionTitle;
    }

    public String getOptionalPermissionTitle() {
        return optionalPermissionTitle;
    }

    public void setOptionalPermissionTitle(String optionalPermissionTitle) {
        this.optionalPermissionTitle = optionalPermissionTitle;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public String getOkString() {
        return okString;
    }

    public void setOkString(String okString) {
        this.okString = okString;
    }

    public int getOkColor() {
        return okColor;
    }

    public void setOkColor(int okColor) {
        this.okColor = okColor;
    }

    public int getItemLayoutResourceId() {
        return itemLayoutResourceId;
    }

    public void setItemLayoutResourceId(int itemLayoutResourceId) {
        this.itemLayoutResourceId = itemLayoutResourceId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DialogUiConfig{");
        sb.append("mandatoryPermissionTitle='").append(mandatoryPermissionTitle).append('\'');
        sb.append(", optionalPermissionTitle='").append(optionalPermissionTitle).append('\'');
        sb.append(", backgroundColor=").append(backgroundColor);
        sb.append(", titleColor=").append(titleColor);
        sb.append(", okString='").append(okString).append('\'');
        sb.append(", okColor=").append(okColor);
        sb.append(", itemLayoutResourceId=").append(itemLayoutResourceId);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder implements Serializable {
        private String mandatoryPermissionTitle;
        private String optionalPermissionTitle;
        @ColorInt
        private int backgroundColor;
        @ColorInt
        private int titleColor;
        private String okString;
        @ColorInt
        private int okColor;
        @LayoutRes
        private int itemLayoutResourceId;

        private Context context;

        public Builder(Context context) {
            this.context = context;
            this.backgroundColor = ContextCompat.getColor(context, R.color.simplePermissionNotice_backgroundColor);
            this.titleColor = ContextCompat.getColor(context, R.color.simplePermissionNotice_titleColor);
            this.okColor = ContextCompat.getColor(context, R.color.simplePermissionNotice_okStringColor);
            this.itemLayoutResourceId = R.layout.item_simple_permission_notice;
        }

        public Builder setMandatoryPermissionTitle(String mandatoryPermissionTitle) {
            this.mandatoryPermissionTitle = mandatoryPermissionTitle;
            return this;
        }

        public Builder setMandatoryPermissionTitle(@StringRes int mandatoryPermissionTitleResourceId) {
            this.mandatoryPermissionTitle = context.getString(mandatoryPermissionTitleResourceId);
            return this;
        }

        public Builder setOptionalPermissionTitle(String optionalPermissionTitle) {
            this.optionalPermissionTitle = optionalPermissionTitle;
            return this;
        }

        public Builder setOptionalPermissionTitle(@StringRes int optionalPermissionTitleResourceId) {
            this.optionalPermissionTitle = context.getString(optionalPermissionTitleResourceId);
            return this;
        }

        public Builder setBackgroundColor(@ColorRes int backgroundColorResourceId) {
            this.backgroundColor = ContextCompat.getColor(context, backgroundColorResourceId);
            return this;
        }

        public Builder setBackgroundColor(String backgroundColorString) {
            this.backgroundColor = Color.parseColor(backgroundColorString);
            return this;
        }

        public Builder setTitleColor(@ColorRes int titleColorResourceId) {
            this.titleColor = ContextCompat.getColor(context, titleColorResourceId);
            return this;
        }

        public Builder setTitleColor(String titleColorString) {
            this.titleColor = Color.parseColor(titleColorString);
            return this;
        }

        public Builder setOkString(String okString) {
            this.okString = okString;
            return this;
        }

        public Builder setOkString(@StringRes int okStringResourceId) {
            this.okString = context.getString(okStringResourceId);
            return this;
        }

        public Builder setOkColor(@ColorRes int okColorResourceId) {
            this.okColor = ContextCompat.getColor(context, okColorResourceId);
            return this;
        }

        public Builder setOkColor(String okColorString) {
            this.okColor = Color.parseColor(okColorString);
            return this;
        }

        public Builder setItemLayout(@LayoutRes int itemLayoutResourceId) {
            this.itemLayoutResourceId = itemLayoutResourceId;
            return this;
        }

        public UiConfig build() {
            UiConfig dialogUiConfig = new UiConfig();

            String mandatoryPermissionTitle = this.mandatoryPermissionTitle;

            if (TextUtils.isEmpty(mandatoryPermissionTitle)) {
                mandatoryPermissionTitle = context.getString(R.string.simplePermissionNotice_mandatoryPermissionTitle);
            }

            String optionalPermissionTitle = this.optionalPermissionTitle;

            if (TextUtils.isEmpty(optionalPermissionTitle)) {
                optionalPermissionTitle = context.getString(R.string.simplePermissionNotice_optionalPermissionTitle);
            }

            String okString = this.okString;

            if (TextUtils.isEmpty(okString)) {
                okString = context.getString(android.R.string.ok);
            }

            dialogUiConfig.setMandatoryPermissionTitle(mandatoryPermissionTitle);
            dialogUiConfig.setOptionalPermissionTitle(optionalPermissionTitle);
            dialogUiConfig.setOkString(okString);
            dialogUiConfig.setBackgroundColor(this.backgroundColor);
            dialogUiConfig.setTitleColor(this.titleColor);
            dialogUiConfig.setOkColor(this.okColor);
            dialogUiConfig.setItemLayoutResourceId(this.itemLayoutResourceId);

            return dialogUiConfig;
        }
    }


}
