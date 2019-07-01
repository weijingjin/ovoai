package com.ovo.unity.utils;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.unity3d.player.UnityPlayer;

public class MyUnityPlayer extends UnityPlayer {
    private GestureDetector gestureDetector;//手势
    public MyUnityPlayer(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, new MyGestureDetectorUtils());
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }
}
