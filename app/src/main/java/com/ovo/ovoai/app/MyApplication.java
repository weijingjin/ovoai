package com.ovo.ovoai.app;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ovo.common.app.BaseApplication;

public class MyApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
