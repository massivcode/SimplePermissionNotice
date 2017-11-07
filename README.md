[![](https://jitpack.io/v/prChoe/SimplePermissionNotice.svg)](https://jitpack.io/#prChoe/SimplePermissionNotice)

# What is SimplePermissionNotice?

⌈정보통신망법⌋ 개정('16.3.22 공포) 주요 내용 중 '스마트폰 앱 접근권한에 대한이용자 동의권 강화' 에 대응하기 위한 라이브러리 입니다. 

[정보통신망 이용촉진 및 정보보호 등에 관한 법률](https://www.i-privacy.kr/jsp/user4/intro/law2.jsp)

'스마트폰에 대한 접근권한 필요시 필수적 권한과 선택적 권한을 구분하여 이용자의 동의를 받도록 함' 

이러한 내용이 존재하는데, 이에 대응하기 위한 접근법은 다음 슬라이드를 참고해주세요. [링크](https://www.slideshare.net/NAVER_D2SF/d2sf-20170713)

앱 사용에 필요한 [Dangerous Permission - 표1 참고](https://developer.android.com/guide/topics/security/permissions.html?hl=ko#normal-dangerous)
는 다른 일반적인 권한과 다르게 Android M 이상부터는 런타임 권한 체크를 통하여 수락/거부 여부를 확인해야 합니다.

귀찮고 짜증나는 런타임 권한 체크는 박상권님의 [TedPermission](https://github.com/ParkSangGwon/TedPermission) 라이브러리를 이용하여 손쉽게 해결하실 수 있으며,
이 라이브러리는 위에서 설명한 Dangerous Permission 을 ⌈필수 권한⌋ 및 ⌈선택 권한⌋ 으로 나눠서 편리하게 고지하기 위한 라이브러리 입니다.

## Preview

![Screenshot](https://github.com/prChoe/SimplePermissionNotice/blob/master/screenshot.png)    

## Setup

### 1. Gradle

```gradle
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

```gradle
compile 'com.github.prChoe:SimplePermissionNotice:1.2.0'
```

### 2. Maven

```maven
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```maven
<dependency>
	    <groupId>com.github.prChoe</groupId>
	    <artifactId>SimplePermissionNotice</artifactId>
	    <version>1.2.0</version>
	</dependency>
```

## How to Use

### 1. Initialize
```java
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initSimplePermissionNotice();
    }

    private void initSimplePermissionNotice() {
        // SimplePermissionNotice 관련 다이얼로그와 액티비티의 UI 설정
        UiConfig.Builder builder = new UiConfig.Builder(getApplicationContext());

        builder
                // 고지될 Permission 의 ItemLayout 정의
                .setItemLayout(R.layout.item_simple_permission_notice2)
                // Dialog 또는 Activity 의 백그라운드 색상 지정 (ColorRes)
                .setBackgroundColor(R.color.colorAccent)
                // Dialog 또는 Activity 의 백그라운드 색상 지정 (ColorString)
                .setBackgroundColor("#2196f3")
                // 필수 권한 텍스트 지정 (StringRes)
                .setMandatoryPermissionTitle(R.string.app_name)
                // 필수 권한 텍스트 지정 (String)
                .setMandatoryPermissionTitle("[필수 권한]")
                // 선택 권한 텍스트 지정 (StringRes)
                .setOptionalPermissionTitle(R.string.app_name)
                // 선택 권한 텍스트 지정 (String)
                .setOptionalPermissionTitle("[선택 권한]")
                // 팝업 또는 액티비티 종료를 수행하는 TextView 의 텍스트 지정 (String)
                .setOkString("OK")
                // 팝업 또는 액티비티 종료를 수행하는 TextView 의 텍스트 지정 (StringRes)
                .setOkString(R.string.app_name)
                // OK 텍스트 색상 지정 (ColorRes)
                .setOkColor(R.color.colorPrimary)
                // OK 텍스트 색상 지정 (ColorString)
                .setOkColor("#2196f3");

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
```

### 2. Show Dialog OR Activity

```java
public class MainActivity extends AppCompatActivity {
    private SimplePermissionNotice mNotice = SimplePermissionNotice.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sdt_1_btn).setOnClickListener(mOnShowAllPermissionsDialogListener);
        findViewById(R.id.sdt_2_btn).setOnClickListener(mOnShowSelectedPermissionsDialogListener);
        findViewById(R.id.sa_1_btn).setOnClickListener(mOnShowAllPermissionsActivityListener);
    }
    
    private View.OnClickListener mOnShowAllPermissionsDialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 사전 정의된 모든 Permission 을 고지하는 팝업을 띄운다.
            mNotice.showDialog(MainActivity.this, new SimplePermissionNoticeCallback() {
	    	// 모든 Permission 이 허용되었을 때
		@Override
       		public void onGranted() {

       		}
	
                @Override
                public void onDismiss(String[] permissionCodes, String[] mandatoryPermissionCodes, String[] optionalPermissionCodes) {
                    // permissionCodes : 사전 정의된 모든 PermissionCodes
                    // mandatoryPermissionCodes : 필수 권한 PermissionCodes
                    // optionalPermissionCodes : 선택 권한 PermissionCodes
                    
                    for (String each : permissionCodes) {
                        System.out.println("PermissionCodes : " + each);
                    }

                    System.out.println("================================================================");

                    for (String each : mandatoryPermissionCodes) {
                        System.out.println("MandatoryPermissionCodes : " + each);
                    }

                    System.out.println("================================================================");

                    for (String each : optionalPermissionCodes) {
                        System.out.println("OptionalPermissionCodes : " + each);
                    }

//                    출력 
                    
//                    PermissionCodes : android.permission.READ_EXTERNAL_STORAGE
//                    PermissionCodes : android.permission.WRITE_EXTERNAL_STORAGE
//                    PermissionCodes : android.permission.CALL_PHONE
//                    PermissionCodes : android.permission.RECEIVE_MMS
//                    PermissionCodes : android.permission.ACCESS_COARSE_LOCATION
//                    PermissionCodes : android.permission.READ_CALENDAR
//                    PermissionCodes : android.permission.GET_ACCOUNTS
//                    PermissionCodes : android.permission.SEND_SMS
//                    ================================================================
//                    MandatoryPermissionCodes : android.permission.READ_EXTERNAL_STORAGE
//                    MandatoryPermissionCodes : android.permission.WRITE_EXTERNAL_STORAGE
//                    MandatoryPermissionCodes : android.permission.CALL_PHONE
//                    MandatoryPermissionCodes : android.permission.RECEIVE_MMS
//                    ================================================================
//                    OptionalPermissionCodes : android.permission.ACCESS_COARSE_LOCATION
//                    OptionalPermissionCodes : android.permission.READ_CALENDAR
//                    OptionalPermissionCodes : android.permission.GET_ACCOUNTS
//                    OptionalPermissionCodes : android.permission.SEND_SMS
                }

                @Override
                public void onUnderMarshmallow() {
                    // 디바이스 APK Level 이 M 이하
                    System.out.println("under m");
                }
            });
        }
    };
    
    private View.OnClickListener mOnShowSelectedPermissionsDialogListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 사전 정의된 Permission 중 지정된 Permission 만 팝업으로 고지한다.
            mNotice.showDialog(MainActivity.this, new SimplePermissionNoticeCallback() {
	    	// 모든 Permission 이 허용되었을 때
		@Override
       		public void onGranted() {

       		}
		
                @Override
                public void onDismiss(String[] permissionCodes, String[] mandatoryPermissionCodes, String[] optionalPermissionCodes) {
                    // permissionCodes : 사전 정의된 모든 PermissionCodes
                    // mandatoryPermissionCodes : 필수 권한 PermissionCodes
                    // optionalPermissionCodes : 선택 권한 PermissionCodes

                    for (String each : permissionCodes) {
                        System.out.println("PermissionCodes : " + each);
                    }

                    System.out.println("================================================================");

                    for (String each : mandatoryPermissionCodes) {
                        System.out.println("MandatoryPermissionCodes : " + each);
                    }

                    System.out.println("================================================================");

                    for (String each : optionalPermissionCodes) {
                        System.out.println("OptionalPermissionCodes : " + each);
                    }

//                    출력 

//                    PermissionCodes : android.permission.WRITE_EXTERNAL_STORAGE
//                    PermissionCodes : android.permission.ACCESS_COARSE_LOCATION
//                    ================================================================
//                    MandatoryPermissionCodes : android.permission.WRITE_EXTERNAL_STORAGE
//                    ================================================================
//                    OptionalPermissionCodes : android.permission.ACCESS_COARSE_LOCATION
                }

                @Override
                public void onUnderMarshmallow() {
                    // 디바이스 APK Level 이 M 이하
                    System.out.println("under m");
                }
                
                // 고지할 사전 정의된 Permission Codes
            }, DangerousPermissions.ACCESS_COARSE_LOCATION, DangerousPermissions.WRITE_EXTERNAL_STORAGE);
        }
    };
    
    private View.OnClickListener mOnShowAllPermissionsActivityListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 사전 정의된 모든 Permission 을 고지하는 Activity 를 실행한다.
            mNotice.showActivity(MainActivity.this, new SimplePermissionNoticeCallback() {
	    	// 모든 Permission 이 허용되었을 때
		@Override
       		public void onGranted() {

       		}
		
                @Override
                public void onDismiss(String[] permissionCodes, String[] mandatoryPermissionCodes, String[] optionalPermissionCodes) {
                    // permissionCodes : 사전 정의된 모든 PermissionCodes
                    // mandatoryPermissionCodes : 필수 권한 PermissionCodes
                    // optionalPermissionCodes : 선택 권한 PermissionCodes

                    for (String each : permissionCodes) {
                        System.out.println("PermissionCodes : " + each);
                    }

                    System.out.println("================================================================");

                    for (String each : mandatoryPermissionCodes) {
                        System.out.println("MandatoryPermissionCodes : " + each);
                    }

                    System.out.println("================================================================");

                    for (String each : optionalPermissionCodes) {
                        System.out.println("OptionalPermissionCodes : " + each);
                    }

//                    출력 

//                    PermissionCodes : android.permission.READ_EXTERNAL_STORAGE
//                    PermissionCodes : android.permission.WRITE_EXTERNAL_STORAGE
//                    PermissionCodes : android.permission.CALL_PHONE
//                    PermissionCodes : android.permission.RECEIVE_MMS
//                    PermissionCodes : android.permission.ACCESS_COARSE_LOCATION
//                    PermissionCodes : android.permission.READ_CALENDAR
//                    PermissionCodes : android.permission.GET_ACCOUNTS
//                    PermissionCodes : android.permission.SEND_SMS
//                    ================================================================
//                    MandatoryPermissionCodes : android.permission.READ_EXTERNAL_STORAGE
//                    MandatoryPermissionCodes : android.permission.WRITE_EXTERNAL_STORAGE
//                    MandatoryPermissionCodes : android.permission.CALL_PHONE
//                    MandatoryPermissionCodes : android.permission.RECEIVE_MMS
//                    ================================================================
//                    OptionalPermissionCodes : android.permission.ACCESS_COARSE_LOCATION
//                    OptionalPermissionCodes : android.permission.READ_CALENDAR
//                    OptionalPermissionCodes : android.permission.GET_ACCOUNTS
//                    OptionalPermissionCodes : android.permission.SEND_SMS
                }

                @Override
                public void onUnderMarshmallow() {
                    // 디바이스 APK Level 이 M 이하
                    System.out.println("under m");
                }
            });
        }
    };
}

```

### 3. UI 설정 - Dialog & Activity

![Screenshot](https://github.com/prChoe/SimplePermissionNotice/blob/master/SimplePermissionNotice-commented-side.png)    

```java
        // SimplePermissionNotice 관련 다이얼로그와 액티비티의 UI 설정
        UiConfig.Builder builder = new UiConfig.Builder(getApplicationContext());

        builder
                // 고지될 Permission 의 ItemLayout 정의
                .setItemLayout(R.layout.item_simple_permission_notice2)
                // Dialog 또는 Activity 의 백그라운드 색상 지정 (ColorRes)
                .setBackgroundColor(R.color.colorAccent)
                // Dialog 또는 Activity 의 백그라운드 색상 지정 (ColorString)
                .setBackgroundColor("#2196f3")
                // 필수 권한 텍스트 지정 (StringRes)
                .setMandatoryPermissionTitle(R.string.app_name)
                // 필수 권한 텍스트 지정 (String)
                .setMandatoryPermissionTitle("[필수 권한]")
                // 선택 권한 텍스트 지정 (StringRes)
                .setOptionalPermissionTitle(R.string.app_name)
                // 선택 권한 텍스트 지정 (String)
                .setOptionalPermissionTitle("[선택 권한]")
                // 팝업 또는 액티비티 종료를 수행하는 TextView 의 텍스트 지정 (String)
                .setOkString("OK")
                // 팝업 또는 액티비티 종료를 수행하는 TextView 의 텍스트 지정 (StringRes)
                .setOkString(R.string.app_name)
                // OK 텍스트 색상 지정 (ColorRes)
                .setOkColor(R.color.colorPrimary)
                // OK 텍스트 색상 지정 (ColorString)
                .setOkColor("#2196f3");
```

#### 1. Activity Layout 재정의

SimplePermissionNotice 에 정의된 다음의 메소드를 호출합니다.

```java
// activityLayoutResourceId : 재정의한 activity resource id (R.layout.xxx)
public void showActivity(Activity activity, int activityLayoutResourceId, SimplePermissionNoticeCallback callback)
public void showActivity(Activity activity, int activityLayoutResourceId, SimplePermissionNoticeCallback callback, @DangerousPermissions String... permissionCodes)
```

레이아웃 재정의시 제약사항

* 다음 표의 요소들은 반드시 재정의된 xml 파일에 포함되어 있어야 합니다.

| View         | id                     | Description                              |
|--------------|------------------------|------------------------------------------|
| LinearLayout | root_container         | 루트 컨테이너                            |
| LinearLayout | mandatory_container_ll | 필수 권한 컨테이너                       |
| TextView     | mandatory_title_tv     | 필수 권한 TextView                       |
| RecyclerView | mandatory_rv           | 필수 권한이 출력될 RecyclerView          |
| LinearLayout | optional_container_ll  | 선택 권한 컨테이너                       |
| TextView     | optional_title_tv      | 선택 권한 TextView                       |
| RecyclerView | optional_rv            | 선택 권한이 출력될 RecyclerView          |
| TextView     | ok_tv                  | Dismiss 또는 Finish 를 수행하는 TextView |

#### 2. ItemLayout 재정의

```java
        // SimplePermissionNotice 관련 다이얼로그와 액티비티의 UI 설정
        UiConfig.Builder builder = new UiConfig.Builder(getApplicationContext());

        builder
                // 고지될 Permission 의 ItemLayout 정의
                .setItemLayout(R.layout.item_simple_permission_notice2);
                ...
```

레이아웃 재정의시 제약사항

* 다음 표의 요소들은 반드시 재정의된 xml 파일에 포함되어 있어야 합니다.

| View      | id             | Description               |
|-----------|----------------|---------------------------|
| ImageView | icon_iv        | Permission 의 아이콘 출력 |
| TextView  | title_tv       | Permission 의 타이틀 출력 |
| TextView  | description_tv | Permission 의 설명 출력   |

### 4. TedPermission 과의 연계

```java
 mNotice.showDialog(MainActivity.this, new SimplePermissionNoticeCallback() {
 	    	// 모든 Permission 이 허용되었을 때
		@Override
       		public void onGranted() {

       		}
		
                @Override
                public void onDismiss(String[] permissionCodes, String[] mandatoryPermissionCodes, String[] optionalPermissionCodes) {
                    new TedPermission(MainActivity.this)
                            .setPermissionListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted() {
                                    xxx();
                                }

                                @Override
                                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                    yyy();
                                }
                            })
                            .setPermissions(permissionCodes)
                            .check();
                }

                @Override
                public void onUnderMarshmallow() {
                    // 디바이스 APK Level 이 M 이하
                    System.out.println("under m");
                }
            });
```

## License 
 ```code
MIT License

Copyright (c) 2017 massivcode

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

```

