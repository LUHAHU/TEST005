package com.megatronus.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;


public class SettingConfig {

    private static SettingConfig settingConfig;

    private SharedPreferences sharedPreferences;

    private Context context;

    //配置文件名
    private final String SETTING_NAME = "setting";

    public void init(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SETTING_NAME, Context.MODE_PRIVATE);
    }

    public static SettingConfig getInstance() {
        if (settingConfig == null) {
            synchronized (SettingConfig.class) {
                if (settingConfig == null) {
                    settingConfig = new SettingConfig();
                }
            }
        }
        return settingConfig;
    }

}
