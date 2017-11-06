package com.massivcode.example.simplepermissionnotice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.massivcode.simplepermissionnotice.SimplePermissionNotice;
import com.massivcode.simplepermissionnotice.SimplePermissionNoticeCallback;

public class MainActivity extends AppCompatActivity {

  private SimplePermissionNotice mNotice = SimplePermissionNotice.getInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.btn1).setOnClickListener(mOnActivityButtonClickListener);
    findViewById(R.id.btn2).setOnClickListener(mOnDialogShowButtonClickListener);
  }

  private View.OnClickListener mOnActivityButtonClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      mNotice.showActivity(MainActivity.this, new SimplePermissionNoticeCallback() {
        @Override
        public void onGranted() {

        }

        @Override
        public void onDismiss(String[] strings, String[] strings1, String[] strings2) {

        }

        @Override
        public void onUnderMarshmallow() {

        }
      });
    }
  };

  private View.OnClickListener mOnDialogShowButtonClickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      mNotice.showDialog(MainActivity.this, new SimplePermissionNoticeCallback() {

        @Override
        public void onGranted() {

        }

        @Override
        public void onDismiss(String[] strings, String[] strings1, String[] strings2) {

        }

        @Override
        public void onUnderMarshmallow() {

        }
      });
    }
  };
}
