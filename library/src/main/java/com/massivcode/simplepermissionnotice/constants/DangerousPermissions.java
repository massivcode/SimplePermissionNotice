package com.massivcode.simplepermissionnotice.constants;

import android.Manifest;
import android.support.annotation.StringDef;

import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.ACCESS_COARSE_LOCATION;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.ACCESS_FINE_LOCATION;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.ADD_VOICEMAIL;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.BODY_SENSORS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.CALL_PHONE;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.CAMERA;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.GET_ACCOUNTS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.PROCESS_OUTGOING_CALLS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.READ_CALENDAR;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.READ_CALL_LOG;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.READ_CONTACTS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.READ_EXTERNAL_STORAGE;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.READ_PHONE_STATE;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.READ_SMS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.RECEIVE_MMS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.RECEIVE_SMS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.RECEIVE_WAP_PUSH;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.RECORD_AUDIO;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.SEND_SMS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.USE_SIP;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.WRITE_CALENDAR;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.WRITE_CALL_LOG;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.WRITE_CONTACTS;
import static com.massivcode.simplepermissionnotice.constants.DangerousPermissions.WRITE_EXTERNAL_STORAGE;


/**
 * Created by massivcode@gmail.com on 2017. 9. 18. 10:01
 */
@StringDef({READ_CALENDAR, WRITE_CALENDAR, CAMERA, READ_CONTACTS, WRITE_CONTACTS, GET_ACCOUNTS, ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION,
        RECORD_AUDIO, READ_PHONE_STATE, CALL_PHONE, READ_CALL_LOG, WRITE_CALL_LOG, ADD_VOICEMAIL, USE_SIP, PROCESS_OUTGOING_CALLS, BODY_SENSORS, SEND_SMS,
        RECEIVE_SMS, READ_SMS, RECEIVE_WAP_PUSH, RECEIVE_MMS, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE})
public @interface DangerousPermissions {
    String READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    String WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;
    String CAMERA = Manifest.permission.CAMERA;
    String READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    String WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    String RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    String CALL_PHONE = Manifest.permission.CALL_PHONE;
    String READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    String WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    String ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    String USE_SIP = Manifest.permission.USE_SIP;
    String PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;
    String BODY_SENSORS = Manifest.permission.BODY_SENSORS;
    String SEND_SMS = Manifest.permission.SEND_SMS;
    String RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    String READ_SMS = Manifest.permission.READ_SMS;
    String RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    String RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;
    String READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    String WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
}
