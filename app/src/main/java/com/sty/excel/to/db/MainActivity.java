package com.sty.excel.to.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sty.excel.to.db.app.MyApplication;
import com.sty.excel.to.db.db.CoBrandCardBinDao;
import com.sty.excel.to.db.manager.ReadExcelToDbTask;
import com.sty.excel.to.db.model.CoBrandCardBin;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadExcelToDbTask().execute();

//        CoBrandCardBin bin = new CoBrandCardBin();
//        bin.setIssuerName("sty");
//        bin.setIssuerIIN("12345");
//
//        boolean isSuccess = CoBrandCardBinDao.getInstance(MyApplication.getmApp()).insertCoBrandCardBinData(bin);
//        Log.i("sty", "isSuccess?:" + isSuccess);
//
//        List<CoBrandCardBin> result =  CoBrandCardBinDao.getInstance(MyApplication.getmApp()).findSpecificCoBrandCardBin("sty");
//        if(result != null){
//            Log.i("sty", "result:" + result.get(0).getIssuerName() + "---" + result.get(0).getIssuerIIN());
//        }else{
//            Log.i("sty", "result: nothing" );
//        }
    }
}
