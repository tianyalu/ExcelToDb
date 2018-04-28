package com.sty.excel.to.db.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sty.excel.to.db.model.CoBrandCardBin;
import com.sty.excel.to.db.utils.LogUtils;

import java.sql.SQLException;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class BaseDbHelper extends OrmLiteSqliteOpenHelper {
    private static final String TAG = BaseDbHelper.class.getSimpleName();
    //DB Name
    private static final String DATABASE_NAME = "data.db";
    //DB Version
    private static final int DATABASE_VERSION = 2;

    private static Context context;

    private BaseDbHelper(){
        this(context);
    }

    private BaseDbHelper(Context context1) {
        super(context1, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, CoBrandCardBin.class);
        } catch (SQLException e) {
            LogUtils.e(TAG, e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {

    }

    /**
     * 静态内部类单例模式
     */
    private static class LazyHolder{
        private static final BaseDbHelper instance = new BaseDbHelper();
    }
    public static BaseDbHelper getInstance(Context context1){
        context = context1;
        return LazyHolder.instance;
    }

    //双重锁单例模式
  /*  private static volatile BaseDbHelper instance;
    public static BaseDbHelper getInstance(Context context){
        if(instance == null){
            synchronized (BaseDbHelper.class){
                if(instance == null){
                    instance = new BaseDbHelper(context);
                }
            }
        }
        return instance;
    }*/
}
