package com.ovo.common.utils;

import android.util.Log;

public class LogUtils {

    public static void log(Object log){
        Log.e("-------", "-----------=" + log);
    }

    public static void log(String msg, Object log){
        Log.e("-------" + msg, "-----------=" + log);
    }

}
