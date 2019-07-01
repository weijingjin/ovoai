package com.ovo.xfy.xfyun;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.ovo.xfy.TCYLog;
import com.ovo.xfy.impl.ALYTTSCompletedListener;

/**
 * 讯飞云tts
 */
public class XFYTTS {
    private final String TAG = this.getClass().getSimpleName();
    private static XFYTTS utils = null;

    public static XFYTTS getIntance() {
        if (utils == null) {
            synchronized (XFYTTS.class) {
                if (utils == null) {
                    utils = new XFYTTS();
                }
            }
        }
        return utils;
    }

    // 语音合成对象
    private SpeechSynthesizer mTts;

    // 默认发音人
//    private String voicer = "xiaoyan";
//    private String voicer = "x_xiaolin";
    private String voicer = "aisjinger";
    // 引擎类型
    private String mEngineType = SpeechConstant.TYPE_CLOUD;

    /***tts播报完成*/
    private ALYTTSCompletedListener alyttsCompletedListener;
    /***单字下标**/
    private int textId = -1;

    private Context context;

    public void setALYTTSCompletedListener(ALYTTSCompletedListener listener){
        alyttsCompletedListener = listener;
    }

    public void initXFY(Context context){
        // 初始化合成对象
        //此处调用与SpeechDemo中重复，两处只调用其一即可
        this.context = context;
        SpeechUtility.createUtility(context, "appid=5d0b5a");
        mTts = SpeechSynthesizer.createSynthesizer(context, mTtsInitListener);
    }

    public void startPlayXFY(String text, int textId){
        this.textId = textId;

        if (mTts != null){
            // 设置参数
            setParam();
            mTts.startSpeaking(text, mTtsListener);
        }
    }

    public void startPlayXFY(String text){
        if (mTts != null){
            // 设置参数
            setParam();
            mTts.startSpeaking(text, mTtsListener);
        }
    }

    /**
     * 停止播报tts
     */
    public void stopPlayXFY() {
        if (mTts != null){
            mTts.stopSpeaking();
        }
    }

    /**
     * 关闭播报tts
     */
    protected void closeXFYTTS() {
        if( null != mTts ){
            mTts.stopSpeaking();
            // 退出时释放连接
            mTts.destroy();
        }
    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "InitListener init() code = " + code);
            if (code != ErrorCode.SUCCESS) {
                TCYLog.log(TAG, "初始化失败,错误码："+code);
            } else {
                TCYLog.log(TAG, "初始化成功");
                if (alyttsCompletedListener != null){
                    alyttsCompletedListener.InitSuccess();
                }
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    /**
     * 合成回调监听。
     */
    private SynthesizerListener mTtsListener = new SynthesizerListener() {

        @Override
        public void onSpeakBegin() {
            TCYLog.log(TAG, "开始播放");
        }

        @Override
        public void onSpeakPaused() {
            TCYLog.log(TAG, "暂停播放");
        }

        @Override
        public void onSpeakResumed() {
            TCYLog.log(TAG, "继续播放");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos,
                                     String info) {
//            TCYLog.log(TAG, "合成进度=" + percent);
            // 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
//            TCYLog.log(TAG, "播放进度=" + percent);
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                TCYLog.log(TAG, "播放完成");
                if (alyttsCompletedListener != null){
                    if (textId != -1){
                        alyttsCompletedListener.TTSCompleted(textId);
//                        textId = -1;
                    }else {
                        alyttsCompletedListener.TTSCompleted();
                    }
                }
            } else if (error != null) {
                TCYLog.log(error.getPlainDescription(true));
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}

            //当设置SpeechConstant.TTS_DATA_NOTIFY为1时，抛出buf数据
			/*if (SpeechEvent.EVENT_TTS_BUFFER == eventType) {
						byte[] buf = obj.getByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER);
						Log.e("MscSpeechLog", "buf is =" + buf);
					}*/

        }
    };

    /**
     * 参数设置
     * @return
     */
    private void setParam(){
        // 清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        // 根据合成引擎设置相应参数
        if(mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            //支持实时音频返回，仅在synthesizeToUri条件下支持
            //mTts.setParameter(SpeechConstant.TTS_DATA_NOTIFY, "1");
            // 设置在线合成发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicer);
            //设置合成语速
            mTts.setParameter(SpeechConstant.SPEED,"30");
            //设置合成音调
            mTts.setParameter(SpeechConstant.PITCH,"50");
            //设置合成音量
            mTts.setParameter(SpeechConstant.VOLUME,"50");
        }else {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            mTts.setParameter(SpeechConstant.VOICE_NAME, "");

        }
        //设置播放器音频流类型
        mTts.setParameter(SpeechConstant.STREAM_TYPE, "3");
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
//        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "pcm");
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory()+"/msc/tts.pcm");
    }

}
