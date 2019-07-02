package com.ovo.xfy.ise;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvaluator;
import com.ovo.common.utils.LogUtils;
import com.ovo.xfy.ise.result.Result;
import com.ovo.xfy.ise.result.entity.Phone;
import com.ovo.xfy.ise.result.entity.Sentence;
import com.ovo.xfy.ise.result.entity.Syll;
import com.ovo.xfy.ise.result.entity.Word;
import com.ovo.xfy.ise.result.xml.XmlResultParser;

import java.util.ArrayList;

/**
 * 语音评测
 */
public class ISEUtlis {
    private static ISEUtlis utils = null;
    private String TAG = ISEUtlis.class.getSimpleName();
    // 评测语种
    private String language;
    // 评测题型
    private String category;
    // 结果等级
    private String result_level;

    private SpeechEvaluator mIse;

    public static ISEUtlis getIntance(){
        if (utils == null){
            synchronized (ISEUtlis.class){
                if (utils == null){
                    utils = new ISEUtlis();
                }
            }
        }
        return utils;
    }

    /**初始化*/
    public void initISE(Context context){
        // 初始化合成对象
        mIse = SpeechEvaluator.createEvaluator(context, null);
        setParams();
    }

    /**开始*/
    public void startEvaluating(String text){
        if (mIse != null){
            if (mIse.isEvaluating()){
                stopEvaluating();
            }else {
                mIse.startEvaluating(text, null, mEvaluatorListener);
            }
        }
    }

    /***停止录音  返回结果*/
    public void stopEvaluating(){
        if (mIse != null && mIse.isEvaluating()){
            LogUtils.log("停止评测");
            mIse.stopEvaluating();
        }
    }

    /**取消录音 不返回结果**/
    public void cancel(){
        if (mIse != null && mIse.isEvaluating()){
            LogUtils.log("取消评测");
            mIse.cancel();
        }
    }

    /**销毁录音**/
    public void destroy(){
        if (mIse != null){
            LogUtils.log("销毁录音");
            mIse.destroy();
            mIse = null;
        }
    }

    // 评测监听接口
    private EvaluatorListener mEvaluatorListener = new EvaluatorListener() {

        @Override
        public void onResult(EvaluatorResult result1, boolean isLast) {

            if (isLast) {
                StringBuilder builder = new StringBuilder();
                builder.append(result1.getResultString());
                String mLastResult = builder.toString();
                if (!TextUtils.isEmpty(mLastResult)) {
                    XmlResultParser resultParser = new XmlResultParser();
                    Result result = resultParser.parse(mLastResult);

                    if (null != result) {
//                        TCYLog.log(TAG, result.is_rejected);
                        boolean is_rejected = result.is_rejected;
                        if (!is_rejected){
                            ArrayList<Sentence> sentences = result.sentences;
                            //字符颜色变化
                            String wordStr;

                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < sentences.size(); i++) {
                                ArrayList<Word> words = sentences.get(i).words;
                                for (int j = 0; j < words.size(); j++) {
                                    ArrayList<Syll> sylls = words.get(j).sylls;
                                    ArrayList<Phone> phones = sylls.get(sylls.size() - 1).phones;
                                    wordStr = words.get(j).content;
                                    if (sylls.get(sylls.size() - 1).dp_message == 0
                                            && phones.get(phones.size() - 1).dp_message == 0){
//                                        LogUtils.log(wordStr + "--正确", index);
                                        LogUtils.log(wordStr, "正确");
                                    }else {
//                                        LogUtils.log(wordStr + "--错误", index);
                                        LogUtils.log(wordStr, "错误");
                                    }
                                }
                            }
//                            LogUtils.log("解析结果完成1");
                        }else {
//                            LogUtils.log("解析结果完成2");
                        }
                    } else {
                        LogUtils.log("解析结果为空");
                    }
                }
            }
        }

        @Override
        public void onError(SpeechError error) {
//            LogUtils.log(TAG, "评测错误");
            if(error != null) {
                LogUtils.log("评测错误:"+ error.getErrorCode() + "," + error.getErrorDescription());
            } else {
//                LogUtils.log(TAG, "evaluator over");
            }
        }

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            LogUtils.log(TAG, "开始语音输入");
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            LogUtils.log(TAG, "评测完成");
        }

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
//            LogUtils.log("当前音量：" + volume);
//			LogUtils.log(TAG, "返回音频数据："+data.length);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		LogUtils.log(TAG, "session id =" + sid);
            //	}
        }
    };

    private void setParams() {
        if( null == mIse ){
            // 创建单例失败，与 21001 错误为同样原因，参考
            // http://bbs.xfyun.cn/forum.php?mod=viewthread&tid=9688
            LogUtils.log(TAG,"创建对象失败，" +
                    "请确认 libmsc.so 放置正确，且有调用 createUtility 进行初始化" );
            return;
        }
        // 设置评测语言
        language = "zh_cn";
        // 设置需要评测的类型
        // read_syllable（单字，汉语专有）、read_word（词语）、
        //read_sentence（句子）、read_chapter（篇章）
        category = "read_sentence";
        // 设置结果等级（中文仅支持complete）
        result_level = "complete";
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理 5000
        String vad_bos = String.valueOf(30*1000);
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音 1800
        String vad_eos = String.valueOf(5*1000);
        // 语音输入超时时间，即用户最多可以连续说多长时间；
        String speech_timeout = "-1";

        mIse.setParameter(SpeechConstant.LANGUAGE, language);
        mIse.setParameter(SpeechConstant.ISE_CATEGORY, category);
        mIse.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
        mIse.setParameter(SpeechConstant.VAD_BOS, vad_bos);
        mIse.setParameter(SpeechConstant.VAD_EOS, vad_eos);
        mIse.setParameter(SpeechConstant.KEY_SPEECH_TIMEOUT, speech_timeout);
        mIse.setParameter(SpeechConstant.RESULT_LEVEL, result_level);

        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
//        mIse.setParameter(SpeechConstant.AUDIO_FORMAT,"wav");
//        mIse.setParameter(SpeechConstant.ISE_AUDIO_PATH, Environment.getExternalStorageDirectory().getAbsolutePath() + "/msc/ise.wav");
        //通过writeaudio方式直接写入音频时才需要此设置
        //mIse.setParameter(SpeechConstant.AUDIO_SOURCE,"-1");
    }

}
