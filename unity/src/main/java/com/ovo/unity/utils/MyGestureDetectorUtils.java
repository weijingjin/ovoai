package com.ovo.unity.utils;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ovo.xfy.xfyun.XFYTTS;
import com.unity3d.player.UnityPlayer;

/***
 * 手势控制
 */
public class MyGestureDetectorUtils extends GestureDetector.SimpleOnGestureListener {
    private final String TAG = this.getClass().getSimpleName();
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
//        LogUtils.log(TAG, "onSingleTapUp");
        return super.onSingleTapUp(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
//        LogUtils.log(TAG, "onLongPress");
        super.onLongPress(e);
    }

    /**
     * 无论是用手拖动view，或者是以抛的动作滚动，
     * 都会多次触发 ,这个方法在ACTION_MOVE动作发生时就会触发
     * @param e1
     * @param e2
     * @param distanceX
     * @param distanceY
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return super.onScroll(e1, e2, distanceX, distanceY);
    }

    /**
     *
     * @param e1 第1个ACTION_DOWN MotionEvent 并且只有一个
     * @param e2 最后一个ACTION_MOVE MotionEvent
     * @param velocityX X轴上的移动速度，像素/秒
     * @param velocityY Y轴上的移动速度，像素/秒
     * 这个方法发生在ACTION_UP时才会触发
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (Math.abs(e2.getX() - e1.getX()) > 400 && Math.abs(e2.getY() - e1.getY()) < 300){
//            LogUtils.log("左滑");
            XFYTTS.getIntance().stopPlayXFY();
            ARouter.getInstance().build("/main/activity").navigation();
//            Intent intent = new Intent(BaseApplication.getInstance().getContext(), MainActivity.class);
//            BaseApplication.getInstance().getActivity().startActivity(intent);
        }else if (Math.abs(e2.getX() - e1.getX()) < 200 && (e2.getY() - e1.getY()) > 300){
//            LogUtils.log("下滑");
            //0是猫 1 是猪
            UnityPlayer.UnitySendMessage("Canvas", "ShowFace","0");
        }else if (Math.abs(e2.getX() - e1.getX()) < 200 & (e2.getY() - e1.getY()) < -300){
//            LogUtils.log("上滑");
            UnityPlayer.UnitySendMessage("Canvas", "ShowFace","1");
        }
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    @Override
    public void onShowPress(MotionEvent e) {
        super.onShowPress(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return super.onDown(e);
    }

    /***
     * 双击
     * @param e
     * @return
     */
    @Override
    public boolean onDoubleTap(MotionEvent e) {
//        LogUtils.log(TAG, "onDoubleTap");
//        TTSUtils.getInstance().speak("各位领导， 上午好！ 我是小童， " +
//                "很高兴见到您！我先来个自我介绍吧”\n" +
//                "小童真正是靠脸吃饭的， 通过深度学习算法，" +
//                "我能够学习人的各种表情，再通过机器人实体再现出来；" +
//                " 先给大家表演一下变脸的绝活");
        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return super.onDoubleTapEvent(e);
    }

    /**
     * 这个方法不同于onSingleTapUp，
     * 他是在GestureDetector确信用户在第一次触摸屏幕后，
     * 没有紧跟着第二次触摸屏幕，也就是不是“双击”的时候触发
     * @param e
     * @return
     */
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
//        LogUtils.log(TAG, "onSingleTapConfirmed");
        return super.onSingleTapConfirmed(e);
    }
}
