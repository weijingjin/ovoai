package com.ovo.xfy_aiui;

import android.content.res.AssetManager;
import android.text.TextUtils;

import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;
import com.ovo.common.app.BaseApplication;
import com.ovo.common.impl.XFYAIUIListener;
import com.ovo.common.utils.LogUtils;
import com.ovo.common.utils.StringUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class AIUIUtils {
    private final String TAG = this.getClass().getSimpleName();
    private static AIUIUtils instance = null;

    private XFYAIUIListener xfyaiuiListener;

    //交互状态
    private int mAIUIState = AIUIConstant.STATE_IDLE;
    private AIUIAgent mAIUIAgent = null;

    public static AIUIUtils getInstance() {
        if (instance == null) {
            synchronized (AIUIUtils.class) {
                if (instance == null) {
                    instance = new AIUIUtils();
                }
            }
        }
        return instance;
    }

    public void setXfyaiuiListener(XFYAIUIListener xfyaiuiListener){
        this.xfyaiuiListener = xfyaiuiListener;
    }

    /**
     * 读取配置
     */
    private String getAIUIParams() {
        String params = "";

        AssetManager assetManager = BaseApplication.getInstance().getContext()
                .getResources().getAssets();
        try {
            InputStream ins = assetManager.open("cfg/aiui_phone.cfg");
            byte[] buffer = new byte[ins.available()];

            ins.read(buffer);
            ins.close();

            params = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return params;
    }

    private boolean checkAIUIAgent() {
        if (null == mAIUIAgent) {
            LogUtils.log(TAG, "创建 AIUI Agent");
            //创建AIUIAgent
            mAIUIAgent = AIUIAgent.createAgent(
                    BaseApplication.getInstance().getContext(), getAIUIParams(), mAIUIListener);
        }

        if (null == mAIUIAgent) {
            final String strErrorTip = "创建 AIUI Agent 失败！";
            LogUtils.log(strErrorTip);
        }

        return null != mAIUIAgent;
    }

    //开始录音
    public void startVoiceNlp() {
        if (!checkAIUIAgent()){
            return;
        }
        LogUtils.log(TAG, "开始录音");

        // 先发送唤醒消息，改变AIUI内部状态，只有唤醒状态才能接收语音输入
        // 默认为oneshot 模式，即一次唤醒后就进入休眠，如果语音唤醒后
        // ，需要进行文本语义，请将改段逻辑copy至startTextNlp()开头处
        if (AIUIConstant.STATE_WORKING != this.mAIUIState) {
            AIUIMessage wakeupMsg = new AIUIMessage(AIUIConstant.CMD_WAKEUP,
                    0, 0, "", null);
            mAIUIAgent.sendMessage(wakeupMsg);
        }

        // 打开AIUI内部录音机，开始录音
        String params = "sample_rate=16000,data_type=audio";
        AIUIMessage writeMsg = new AIUIMessage(AIUIConstant.CMD_START_RECORD,
                0, 0, params, null);
        mAIUIAgent.sendMessage(writeMsg);
    }

    //AIUI事件监听器
    private AIUIListener mAIUIListener = new AIUIListener() {

        @Override
        public void onEvent(AIUIEvent event) {
            switch (event.eventType) {
                case AIUIConstant.EVENT_WAKEUP:
                    //唤醒事件
//                    Log.i(TAG, "on event: " + event.eventType);
                    LogUtils.log(TAG, "进入识别状态");
                    break;

                case AIUIConstant.EVENT_RESULT: {
                    //结果事件
                    LogUtils.log(TAG, "结果事件");
                    try {
                        JSONObject bizParamJson = new JSONObject(event.info);
                        JSONObject data = bizParamJson.getJSONArray("data").getJSONObject(0);
                        JSONObject params = data.getJSONObject("params");
                        JSONObject content = data.getJSONArray("content").getJSONObject(0);

                        if (content.has("cnt_id")) {
                            String cnt_id = content.getString("cnt_id");
                            JSONObject cntJson = new JSONObject(new String(event.data.getByteArray(cnt_id), "utf-8"));

                            String sub = params.optString("sub");
                            JSONObject result = cntJson.optJSONObject("intent");
                            if ("nlp".equals(sub) && result.length() > 2) {
                                // 解析得到语义结果
                                String str = "";
                                //在线语义结果
                                if (result.optInt("rc") == 0) {
                                    JSONObject answer = result.optJSONObject("answer");
                                    if (answer != null) {
                                        str = answer.optString("text");
                                    }
                                } else {
                                    str = "rc4，无法识别";
                                }
                                if (!TextUtils.isEmpty(str)) {
                                    LogUtils.log(TAG, str);
                                    if (xfyaiuiListener != null){
                                        xfyaiuiListener.AIUIResult(str);
                                    }
                                }

                            } else if ("iat".equals(sub)) {
                                processIATResult(cntJson);
                            }
                        }
                    } catch (Throwable e) {
                        e.printStackTrace();
                        LogUtils.log(TAG, e.getLocalizedMessage());
                    }
                }
                break;

                case AIUIConstant.EVENT_ERROR: {
                    //错误事件
                    LogUtils.log(TAG, "on event: " + event.eventType);
                    LogUtils.log(TAG, "错误: " + event.eventType
                            + "===" + event.arg1 + "====" + event.info);
                }
                break;

                case AIUIConstant.EVENT_VAD: {
                    //vad事件
                    if (AIUIConstant.VAD_BOS == event.arg1) {
                        //找到语音前端点
//                        LogUtils.log(TAG, "找到vad_bos");
                    } else if (AIUIConstant.VAD_EOS == event.arg1) {
                        //找到语音后端点
//                        LogUtils.log(TAG, "找到vad_eos");
                    } else {
//                        LogUtils.log(TAG, "" + event.arg2);
                    }
                }
                break;

                case AIUIConstant.EVENT_START_RECORD: {
                    //开始录音事件
//                    Log.i(TAG, "on event: " + event.eventType);
                    LogUtils.log(TAG, "开始录音");
                }
                break;

                case AIUIConstant.EVENT_STOP_RECORD: {
                    //停止录音事件
//                    Log.i(TAG, "on event: " + event.eventType);
                    LogUtils.log(TAG, "停止录音");
                }
                break;

                case AIUIConstant.EVENT_STATE: {    // 状态事件
                    mAIUIState = event.arg1;

                    if (AIUIConstant.STATE_IDLE == mAIUIState) {
                        // 闲置状态，AIUI未开启
                        LogUtils.log(TAG, "闲置状态，AIUI未开启");
                    } else if (AIUIConstant.STATE_READY == mAIUIState) {
                        // AIUI已就绪，等待唤醒
                        LogUtils.log(TAG, "AIUI已就绪，等待唤醒");
                    } else if (AIUIConstant.STATE_WORKING == mAIUIState) {
                        // AIUI工作中，可进行交互
                        LogUtils.log(TAG, "AIUI工作中，可进行交互");
                    }
                }
                break;

                default:
                    break;
            }
        }

    };

    /**
     * 解析听写结果更新当前语音消息的听写内容
     */
    private void processIATResult(JSONObject cntJson) throws JSONException {
        JSONObject text = cntJson.optJSONObject("text");
        // 解析拼接此次听写结果
        StringBuilder iatText = new StringBuilder();
        JSONArray words = text.optJSONArray("ws");
        for (int index = 0; index < words.length(); index++) {
            JSONArray charWord = words.optJSONObject(index).optJSONArray("cw");
            for (int cIndex = 0; cIndex < charWord.length(); cIndex++) {
                iatText.append(charWord.optJSONObject(cIndex).opt("w"));
            }
        }
        if (!StringUtils.isEmpty(iatText.toString()) && xfyaiuiListener != null){
            xfyaiuiListener.AIUIRecode(iatText.toString());
        }
    }

    /**
     * 停止录音
     */
    public void stopAIUI() {
        if (null != this.mAIUIAgent) {
            AIUIMessage stopMsg = new AIUIMessage(AIUIConstant.CMD_STOP_RECORD,
                    0, 0, "data_type=audio,sample_rate=16000", null);
            mAIUIAgent.sendMessage(stopMsg);
        }
    }

    public void closeAIUI() {
        if (null != this.mAIUIAgent) {
            AIUIMessage closeMsg = new AIUIMessage(AIUIConstant.CMD_STOP,
                    0, 0, null, null);
            mAIUIAgent.sendMessage(closeMsg);

            this.mAIUIAgent.destroy();
            this.mAIUIAgent = null;
        }
    }
}
