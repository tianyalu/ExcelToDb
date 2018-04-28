package com.sty.excel.to.db.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sty.excel.to.db.app.MyApplication;

/**
 * Created by Steven.S on 2018/4/28/0028.
 */
public class SpUtils {

    private SpUtils(){
    }

    public static void setBoolean(String name, boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getmApp());
        sp.edit().putBoolean(name, value).commit();
    }

    public static boolean getBoolean(String name){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getmApp());
        return sp.getBoolean(name, false);
    }
}
