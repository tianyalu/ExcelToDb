package com.sty.excel.to.db.manager;


import android.os.AsyncTask;

import com.sty.excel.to.db.utils.LogUtils;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class ReadExcelToDbTask extends AsyncTask<Void, Void, Void> {
    private static final String TAG = ReadExcelToDbTask.class.getSimpleName();

    private CoBrandCardBinDbManager manager;
    @Override
    protected Void doInBackground(Void... voids) {
        manager = CoBrandCardBinDbManager.getInstance();
        LogUtils.i(TAG, "EXCEL数据写入数据库成功");
        return null;
    }
}
