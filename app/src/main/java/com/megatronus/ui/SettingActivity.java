package com.megatronus.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.megatronus.ui.utils.*;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {


    
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }


    private void initView() {
       
    }

    @Override
    public void onClick(View view) {
        
    }

//    private void updateReplyDialog() {
//        String reReplyMessage = SettingConfig.getInstance().getReReplyMessage();
//        final EditText inputServer = new EditText(SettingActivity.this);
//        if (!TextUtils.isEmpty(reReplyMessage)) {
//            inputServer.setText(reReplyMessage);
//            inputServer.setSelection(reReplyMessage.length());
//        }
//        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
//        builder.setTitle("修改回复信息").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
//                .setNegativeButton("取消", null);
//        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface dialog, int which) {
//                if (inputServer.getText() != null) {
//                    String input = inputServer.getText().toString();
//                    SettingConfig.getInstance().setReReplyMessage(input);
//                    dialog.dismiss();
//                }
//            }
//
//        });
//        builder.show();
//    }
}
