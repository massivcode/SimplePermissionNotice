package com.massivcode.example.simplepermissionnotice;

import android.app.Application;

import com.massivcode.simplepermissionnotice.SimplePermissionNotice;
import com.massivcode.simplepermissionnotice.constants.DangerousPermissions;
import com.massivcode.simplepermissionnotice.models.PermissionInfo;
import com.massivcode.simplepermissionnotice.models.UiConfig;

/**
 * Created by massivcode@gmail.com on 2017. 9. 19. 15:23
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initSimplePermissionNotice();
    }

    private void initSimplePermissionNotice() {
        // SimplePermissionNotice 관련 다이얼로그와 액티비티의 UI 설정
        UiConfig.Builder builder = new UiConfig.Builder(getApplicationContext());

        /*
        * SimplePermissionNotice 를 이용하여 고지할 Permission 정의
        *
        * 1. new PermissionInfo(DangerousPermissions.READ_EXTERNAL_STORAGE, "저장소 읽기", "저장소 읽기 설명", R.mipmap.ic_launcher, true)
        * 2. new PermissionInfo(DangerousPermissions.READ_EXTERNAL_STORAGE, R.string.xxx, R.string.yyy, R.mipmap.ic_launcher, false)
        *
        * 1) DangerousPermissions.xxx : 고지할 Permission 의 Code
        * 2) Title : 고지할 Permission 의 Title (String 또는 StringRes)
        * 3) Description : 고지할 Permission 에 대한 설명 (String 또는 StringRes)
        * 4) icon : 고지할 Permission 에 대한 아이콘 (DrawableRes)
        * 5) isMandatory : 고지할 Permission 의 필수 권한 유무
        * */
        PermissionInfo mandatory = new PermissionInfo(DangerousPermissions.READ_EXTERNAL_STORAGE, "저장소 읽기", "저장소 읽기 설명", R.mipmap.ic_launcher, true);
        PermissionInfo mandatory2 = new PermissionInfo(DangerousPermissions.WRITE_EXTERNAL_STORAGE, "저장소 쓰기", "저장소 쓰기 설명", R.mipmap.ic_launcher, true);
        PermissionInfo mandatory3 = new PermissionInfo(DangerousPermissions.CALL_PHONE, "전화 걸기", "전화 걸기 설명", R.mipmap.ic_launcher, true);
        PermissionInfo mandatory4 = new PermissionInfo(DangerousPermissions.RECEIVE_MMS, "MMS 수신", "MMS 수신 설명", R.mipmap.ic_launcher, true);
        PermissionInfo optional = new PermissionInfo(DangerousPermissions.ACCESS_COARSE_LOCATION, "정확한 위치 정보 획득", "정확한 위치 정보 획득 설명", R.mipmap.ic_launcher, false);
        PermissionInfo optional2 = new PermissionInfo(DangerousPermissions.READ_CALENDAR, "캘린더 읽기", "캘린더 읽기 설명", R.mipmap.ic_launcher, false);
        PermissionInfo optional3 = new PermissionInfo(DangerousPermissions.GET_ACCOUNTS, "계정 읽기", "계정 읽기 설명", R.mipmap.ic_launcher, false);
        PermissionInfo optional4 = new PermissionInfo(DangerousPermissions.SEND_SMS, "SMS 전송", "SMS 전송 설명", R.mipmap.ic_launcher, false);

        /*
        * SimplePermissionNotice 초기화
        *
        * 1. SimplePermissionNotice.init(getApplicationContext(), builder, permissionInfo...);
        * 2. SimplePermissionNotice.init(getApplicationContext(), builder, itemDividerDrawableResourceId, permissionInfo...);
        * */
        SimplePermissionNotice.init(getApplicationContext(), builder, mandatory, optional, mandatory2, optional2, mandatory3, optional3, mandatory4, optional4);
    }
}
