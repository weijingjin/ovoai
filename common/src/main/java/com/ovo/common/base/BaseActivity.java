package com.ovo.common.base;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ovo.common.app.AppManager;
import com.ovo.common.app.BaseApplication;
import com.ovo.common.utils.PermissionsUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends Activity implements PermissionsUtils.IPermissionsResult {

    private String []permisions = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO
    };

    private Unbinder mUnbinder;

    public abstract int layout();
    public abstract void initView();
    public abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout());

        PermissionsUtils.getInstance().
                chekPermissions(this, permisions, this);
        mUnbinder = ButterKnife.bind(this);
        BaseApplication.getInstance().setContext(this);
        BaseApplication.getInstance().setActivity(this);
        AppManager.getInstance().addActivity(this);

        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getInstance().setContext(this);
        BaseApplication.getInstance().setActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public void passPermissons() {}

    @Override
    public void forbitPermissons() {}

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtils.getInstance().onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
}
