package com.sty.excel.to.db.db;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.sty.excel.to.db.model.CoBrandCardBin;
import com.sty.excel.to.db.utils.LogUtils;

import java.util.List;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class CoBrandCardBinDao {
    private static final String TAG = CoBrandCardBinDao.class.getSimpleName();

    private static Context context;
    private final BaseDbHelper dbHelper;

    private RuntimeExceptionDao<CoBrandCardBin, Integer> coBrandCardBinDao;

    private CoBrandCardBinDao(){
        this(context);
    }

    private CoBrandCardBinDao(Context context){
        dbHelper = BaseDbHelper.getInstance(context);
    }

    private static class LazyHolder{
        private static final CoBrandCardBinDao instance = new CoBrandCardBinDao();
    }

    public static CoBrandCardBinDao getInstance(Context context1){
        context = context1;
        return LazyHolder.instance;
    }

    public BaseDbHelper getDbHelper() {
        return dbHelper;
    }

    private RuntimeExceptionDao<CoBrandCardBin, Integer> getCoBrandCardBinDao(){
        if(coBrandCardBinDao == null){
            coBrandCardBinDao = dbHelper.getRuntimeExceptionDao(CoBrandCardBin.class);
        }
        //LogUtils.i(TAG, "is null ?:" + coBrandCardBinDao == null ? "null" : "not null");
        return coBrandCardBinDao;
    }

    public boolean insertCoBrandCardBinData(CoBrandCardBin coBrandCardBin){
        try {
            RuntimeExceptionDao<CoBrandCardBin, Integer> dao = getCoBrandCardBinDao();
            dao.create(coBrandCardBin);
            return true;
        }catch (Exception e){
            LogUtils.e(TAG, e);
            return false;
        }
    }

    public boolean updateCoBrandCardBinData(CoBrandCardBin coBrandCardBin){
        try {
            RuntimeExceptionDao<CoBrandCardBin, Integer> dao = getCoBrandCardBinDao();
            dao.update(coBrandCardBin);
            return true;
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    public List<CoBrandCardBin> findSpecificCoBrandCardBin(String name){
        RuntimeExceptionDao<CoBrandCardBin, Integer> dao = getCoBrandCardBinDao();
        List<CoBrandCardBin> binList = dao.queryForEq(CoBrandCardBin.ISSUER_NAME, name);
        if(binList != null && !binList.isEmpty()){
            return binList;
        }
        return null;
    }
}
