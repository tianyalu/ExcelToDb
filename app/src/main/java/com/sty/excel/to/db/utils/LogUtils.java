package com.sty.excel.to.db.utils;

import android.text.TextUtils;
import android.util.Log;

import com.sty.excel.to.db.BuildConfig;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class LogUtils {

    private static final boolean enabled = BuildConfig.LOG_ENABLE;
    private static final String defTag = "EXCEL_TO_DB";//默认TAG

    public static void v(String tag, String msg) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.v(newTag, msg);
    }

    public static void v(String msg) {
        v(defTag, msg);
    }

    public static void d(String tag, String msg) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.d(newTag, msg);
    }

    public static void d(String msg) {
        Log.d(defTag, msg);
    }

    public static void i(String tag, String msg) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.i(newTag, msg);
    }

    public static void i(String msg) {
        Log.i(defTag, msg);
    }

    public static void w(String tag, String msg) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.w(newTag, msg);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.w(newTag, msg, tr);
    }

    public static void w(String tag, Throwable tr) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.w(newTag, tr);
    }

    public static void w(String msg) {
        Log.w(defTag, msg);
    }

    public static void e(String tag, String msg) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.e(newTag, msg);
    }
    public static void e(String tag, String msg, Throwable tr) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.e(newTag, msg, tr);
    }

    public static void e(String tag, Throwable tr) {
        if (!enabled) {
            return;
        }
        String newTag = tag;
        if (TextUtils.isEmpty(tag)) {
            newTag = defTag;
        }
        Log.e(newTag,  "", tr);
    }

    public static void e(String msg) {
        Log.e(defTag, msg);
    }
}