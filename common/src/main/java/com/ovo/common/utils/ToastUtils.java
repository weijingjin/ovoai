package com.ovo.common.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {

    private static Toast toast;
    private static long oneTime = 0;
    private static long twoTime = 0;
    private static CharSequence info = "";

    /***显示*/
    public static void show(Context context, CharSequence text) {
        show(context, text, Toast.LENGTH_SHORT);
    }

    public static void show(Context context, CharSequence text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, text, duration);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (info.equals(text)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                info = text;
                toast.setText(text);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

}
