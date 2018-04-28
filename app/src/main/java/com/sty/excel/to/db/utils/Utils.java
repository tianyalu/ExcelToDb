package com.sty.excel.to.db.utils;

import com.pax.gl.convert.IConvert;
import com.pax.gl.impl.GL;
import com.sty.excel.to.db.app.MyApplication;

/**
 * Created by Steven.S on 2018/4/27/0027.
 */
public class Utils {
    private Utils(){}

    public static String getPaddedString(String str, int maxLen, char ch){
        return GL.getInstance(MyApplication.getmApp()).getConvert().stringPadding(str, ch, maxLen, IConvert.EPaddingPosition.PADDING_LEFT);
    }
}
