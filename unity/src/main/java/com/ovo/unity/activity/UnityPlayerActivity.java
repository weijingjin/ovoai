package com.ovo.unity.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ovo.common.app.BaseApplication;
import com.ovo.unity.utils.DataUtils;
import com.ovo.unity.utils.MyUnityPlayer;
import com.ovo.unity.utils.TcpClient;
import com.ovo.xfy_aiui.AIUIUtils;
import com.unity3d.player.UnityPlayer;

@Route(path = "/unity/activity")
public class UnityPlayerActivity extends Activity {
    protected MyUnityPlayer mUnityPlayer; // don't change the name of this variable; referenced from native code

    public static UnityPlayerActivity activity = null;

    // Setup activity video
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        BaseApplication.getInstance().setContext(this);
        BaseApplication.getInstance().setActivity(this);
        activity = this;
        mUnityPlayer = new MyUnityPlayer(this);
        setContentView(mUnityPlayer);
        mUnityPlayer.requestFocus();

        DataUtils.getInstance().initPYPorsonData();
//        XFYTTS.getIntance().initXFY(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // To support deep linking, we need to make sure that the client can get access to
        // the last sent intent. The clients access this through a JNI api that allows them
        // to get the intent set on launch. To update that after launch we have to manually
        // replace the intent with the one caught here.
        setIntent(intent);
    }

    // Quit Unity
    @Override
    protected void onDestroy() {
        mUnityPlayer.quit();
        super.onDestroy();
    }

    // Resume Unity
    @Override
    protected void onResume() {
        super.onResume();
        activity = this;
        mUnityPlayer.resume();
        if (!TcpClient.getIntance().isRun) {
            TcpClient.getIntance().startTcp();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUnityPlayer.start();
    }

    // Pause Unity
    @Override
    protected void onPause() {
        super.onPause();
        mUnityPlayer.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUnityPlayer.stop();
        TcpClient.getIntance().exit();
        AIUIUtils.getInstance().closeAIUI();
    }

    // Low Memory Unity
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mUnityPlayer.lowMemory();
    }

    // Trim Memory Unity
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level == TRIM_MEMORY_RUNNING_CRITICAL) {
            mUnityPlayer.lowMemory();
        }
    }

    // This ensures the video will be correct.
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mUnityPlayer.configurationChanged(newConfig);
    }

    // Notify Unity of the focus change.
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mUnityPlayer.windowFocusChanged(hasFocus);
    }

    // For some reason the multiple keyevent type is not supported by the ndk.
    // Force event injection by overriding dispatchKeyEvent().
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_MULTIPLE)
            return mUnityPlayer.injectEvent(event);
        return super.dispatchKeyEvent(event);
    }

    // Pass any events not handled by (unfocused) views straight to UnityPlayer
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    /*API12*/
    public boolean onGenericMotionEvent(MotionEvent event) {
        return mUnityPlayer.injectEvent(event);
    }

    public static void CloseUnity() {
//        LogUtils.log("u3d退出");
//        TcpClient.getIntance().exit();
        UnityPlayer.UnitySendMessage("Main Camera", "StopMusic", "");

        if (activity != null) {
            activity.finish();
        }
    }
}
