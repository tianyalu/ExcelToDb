package com.sty.excel.to.db.manager;

import android.content.Context;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.DatabaseConnection;
import com.sty.excel.to.db.app.MyApplication;
import com.sty.excel.to.db.db.BaseDbHelper;
import com.sty.excel.to.db.db.CoBrandCardBinDao;
import com.sty.excel.to.db.model.CoBrandCardBin;
import com.sty.excel.to.db.utils.LogUtils;
import com.sty.excel.to.db.utils.SpUtils;
import com.sty.excel.to.db.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Savepoint;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class CoBrandCardBinDbManager {
    private static final String TAG = CoBrandCardBinDbManager.class.getSimpleName();
    private static final String HAS_EVER_RUN = "HAS_EVER_RUN";

    private static CoBrandCardBinDao coBrandCardBinDao;
    private Context context;

    private CoBrandCardBinDbManager(){
        init();
    }

    private static class LazyHolder{
        private static final CoBrandCardBinDbManager instance = new CoBrandCardBinDbManager();
    }

    public static CoBrandCardBinDbManager getInstance(){
        return LazyHolder.instance;
    }

    private void init(){
        context = MyApplication.getmApp();
        coBrandCardBinDao = CoBrandCardBinDao.getInstance(context);
        long startTimeMills = System.currentTimeMillis();
        LogUtils.i("sty", "--------" + SpUtils.getBoolean(HAS_EVER_RUN) + "--------");
        if(!SpUtils.getBoolean(HAS_EVER_RUN)){
            //readExcelToDb(context);  //57S 393mS
            readExcelToDbOptimized(context);  //1S 858mS

        }
        long endTimeMills = System.currentTimeMillis();
        long delta = endTimeMills - startTimeMills;
        LogUtils.i(TAG, "--->" + delta/1000 + "S " + delta%1000 + "mS finished");
    }

    public static CoBrandCardBinDao getCoBrandDao(){
        if(coBrandCardBinDao == null){
            coBrandCardBinDao = CoBrandCardBinDao.getInstance(MyApplication.getmApp());
        }
        return coBrandCardBinDao;
    }

    private void readExcelToDb(Context context){
        Workbook book = null;
        InputStream is = null;
        try{
            is = context.getAssets().open("UPI_BIN_Table_150621.xls");
            book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            //获取第一个工作表对象
            Sheet sheet = book.getSheet(0);
            CoBrandCardBin coBrandCardBin = null;
            int rows = sheet.getRows();
            for(int i = 1; i < rows; i++){  //跳过表头，从第一行开始
                String issuerIIN = (sheet.getCell(0, i)).getContents();
                String issuerName = (sheet.getCell(1, i)).getContents();

                String tmp = (sheet.getCell(2, i)).getContents();
                String absoluteTmp = Utils.getPaddedString(tmp, 5, '0');
                String cardLevel = absoluteTmp.substring(0, 1);
                String countryCode = absoluteTmp.substring(1);

                String binLength;
                String cardBin;
                tmp = (sheet.getCell(3, i)).getContents();
                if(tmp.startsWith("1")){
                    binLength = tmp.substring(0, 2);
                    cardBin = tmp.substring(2);
                }else{
                    binLength = tmp.substring(0, 1);
                    cardBin = tmp.substring(1);
                }

                tmp = (sheet.getCell(4, i)).getContents();
                String panLength = tmp.substring(0, 2);
                String cardType = tmp.substring(2);

                coBrandCardBin = new CoBrandCardBin.Builder()
                        .setIssuerIIN(issuerIIN)
                        .setIssuerName(issuerName)
                        .setCardLevel(cardLevel)
                        .setCountryCode(countryCode)
                        .setBinLength(binLength)
                        .setCardBin(cardBin)
                        .setPanLength(panLength)
                        .setCardType(cardType)
                        .build();
                saveExcelCardBinToDb(coBrandCardBin);
            }
            SpUtils.setBoolean(HAS_EVER_RUN, true);
        }catch (Exception e){
            LogUtils.e(TAG, e);
            SpUtils.setBoolean(HAS_EVER_RUN, false);
        } finally {
            book.close();
            try {
                is.close();
            }catch (IOException e){
                LogUtils.e(TAG, e);
            }
        }
    }

    private void saveExcelCardBinToDb(CoBrandCardBin coBrandCardBin){
        getCoBrandDao().insertCoBrandCardBinData(coBrandCardBin);
    }

    private void readExcelToDbOptimized(Context context){
        BaseDbHelper dbHelper = CoBrandCardBinDao.getInstance(context).getDbHelper();
        RuntimeExceptionDao<CoBrandCardBin, Integer> dao = dbHelper.getRuntimeExceptionDao(CoBrandCardBin.class);
        //ORMLite的数据连接封装类
        DatabaseConnection dc = dao.startThreadConnection();
        Savepoint sp = null;

        Workbook book = null;
        InputStream is = null;
        try{
            // 存储点名称为create_excel_db
            sp = dc.setSavePoint("create_excel_db");

            is = context.getAssets().open("UPI_BIN_Table_150621.xls");
            book = Workbook.getWorkbook(is);
            book.getNumberOfSheets();
            //获取第一个工作表对象
            Sheet sheet = book.getSheet(0);
            CoBrandCardBin coBrandCardBin = null;
            int rows = sheet.getRows();
            for(int i = 1; i < rows; i++){  //跳过表头，从第一行开始
                String issuerIIN = (sheet.getCell(0, i)).getContents();
                String issuerName = (sheet.getCell(1, i)).getContents();

                String tmp = (sheet.getCell(2, i)).getContents();
                String absoluteTmp = Utils.getPaddedString(tmp, 5, '0');
                String cardLevel = absoluteTmp.substring(0, 1);
                String countryCode = absoluteTmp.substring(1);

                String binLength;
                String cardBin;
                tmp = (sheet.getCell(3, i)).getContents();
                if(tmp.startsWith("1")){
                    binLength = tmp.substring(0, 2);
                    cardBin = tmp.substring(2);
                }else{
                    binLength = tmp.substring(0, 1);
                    cardBin = tmp.substring(1);
                }

                tmp = (sheet.getCell(4, i)).getContents();
                String panLength = tmp.substring(0, 2);
                String cardType = tmp.substring(2);

                coBrandCardBin = new CoBrandCardBin.Builder()
                        .setIssuerIIN(issuerIIN)
                        .setIssuerName(issuerName)
                        .setCardLevel(cardLevel)
                        .setCountryCode(countryCode)
                        .setBinLength(binLength)
                        .setCardBin(cardBin)
                        .setPanLength(panLength)
                        .setCardType(cardType)
                        .build();

                dao.create(coBrandCardBin);
            }
            //成功添加后统一提交数据
            dc.commit(sp);
            dao.endThreadConnection(dc);

            SpUtils.setBoolean(HAS_EVER_RUN, true);
            LogUtils.i("sty", "----+++----" + SpUtils.getBoolean(HAS_EVER_RUN) + "----+++----");
        } catch (SQLException e){
            LogUtils.e(TAG, e);
            try{
                //发生异常时进行回滚
                dc.rollback(sp);
            }catch (SQLException e1){
                LogUtils.e(TAG, e1);
            }
            SpUtils.setBoolean(HAS_EVER_RUN, false);
        } catch (Exception e){
            LogUtils.e(TAG, e);
            SpUtils.setBoolean(HAS_EVER_RUN, false);
        } finally {
            book.close();
            try {
                is.close();
            }catch (IOException e){
                LogUtils.e(TAG, e);
            }
        }
    }
}
