package com.ovo.common.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ovo.xfy.xfyun.XFYTTS;

public class BaseApplication extends Application {

    private static BaseApplication instance = null;
    private Context context;
    private Activity activity;

    public static BaseApplication getInstance() {
        if (instance == null) {
            synchronized (BaseApplication.class){
                if (instance == null){
                    instance = new BaseApplication();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setContext(this);

        XFYTTS.getIntance().initXFY(this);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
