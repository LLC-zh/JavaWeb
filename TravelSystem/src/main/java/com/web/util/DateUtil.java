package com.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/**
     * 取得当前的时间，格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurDateTime() {
    	Date dt = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String currentTime = sdf.format(dt);
        return currentTime;
    }
	
}
