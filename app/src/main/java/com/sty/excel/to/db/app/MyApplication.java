package com.sty.excel.to.db.app;

import android.app.Application;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class MyApplication extends Application {
    private static MyApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.mApp = this;
    }

    public static MyApplication getmApp(){
        return mApp;
    }
}
