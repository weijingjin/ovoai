package com.ovo.xfy;

import android.util.Log;

public class TCYLog {

    public static void log(Object log){
        Log.e("-------", "-----------=" + log);
    }

    public static void log(String msg, Object log){
        Log.e("-------" + msg, "-----------=" + log);
    }
}
