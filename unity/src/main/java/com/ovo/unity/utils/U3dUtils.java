package com.ovo.unity.utils;


import com.ovo.common.impl.XFYAIUIListener;
import com.ovo.common.utils.LogUtils;
import com.ovo.common.utils.StringUtils;
import com.ovo.common.utils.pingyin.PinYinUtils;
import com.ovo.common.utils.pingyin.PinyinUtil;
import com.ovo.xfy.tts.impl.ALYTTSCompletedListener;
import com.ovo.xfy.tts.xfyun.XFYTTS;
import com.ovo.xfy_aiui.AIUIUtils;

import java.util.HashMap;

public class U3dUtils {
    private final String TAG = this.getClass().getSimpleName();
    private static U3dUtils utils = null;

    private HashMap<String, String> dataMap;

    public static U3dUtils getInstance(){
        if (utils == null){
            synchronized (U3dUtils.class){
                if (utils == null){
                    utils = new U3dUtils();
                }
            }
        }
        return utils;
    }

    private int i = 0;
    private String []content = null;
    /**汉字**/
    public void setData(String text){
        content = text.split("，|；|。|！|？|,|;|\\.|!|\\?");
    }

    private String []juzi = null;
    private String []ci = null;
    /**声母韵母**/
    public void setPYData(String text){
        juzi = text.split(",");
    }

    /***开始播放**/
    public void startData(){
        if (content == null)return;
        XFYTTS.getIntance().setALYTTSCompletedListener(listener);
        playTTS(content[i], i + 1);
        playU3d(content[i]);
    }

    /***开始播放**/
    public void startPYData(){
        if (juzi == null || content == null)return;

        AIUIUtils.getInstance().setXfyaiuiListener(xfyaiuiListener);

        XFYTTS.getIntance().setALYTTSCompletedListener(listener);
        playTTS(content[i], i + 1);
        playPYU3d(juzi[i]);
//        playPersonU3d(juzi[i]);
    }

    public void playTTS(String content, int i){
        content = content + "，";
        XFYTTS.getIntance().startPlayXFY(content, i);
    }

    /**控制u3d**/
    public void playU3d(String contents){
        final String content = contents + "，";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataMap = DataUtils.getInstance().getDataMap();
                    if (dataMap == null)return;
                    String text = "";
                    String json = "";
                    String type = "{\"commandData\":{\"control\":\"play\"}}";
                    TcpClient.getIntance().send(type);
                    Thread.sleep(400);
                    for (int i = 0; i < content.length(); i++) {
                        text = content.substring(i, i + 1);
                        json = dataMap.get(text);
                        TcpClient.getIntance().send(json);
                        LogUtils.log(text, json);
                        if (text.equals("，") || text.equals("。")){
                            Thread.sleep(400);
                        }else {
                            Thread.sleep(360);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**声母韵母控制u3d**/
    public void playPYU3d(final String juzi){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    dataMap = DataUtils.getInstance().getDataMap();
                    if (dataMap == null)return;

                    String type = "{\"commandData\":{\"control\":\"play\"}}";
                    TcpClient.getIntance().send(type);
                    Thread.sleep(400);

                    String PY[] = juzi.split("\\|");
                    for (int i = 0; i < PY.length; i++) {
                        if (!StringUtils.isEmpty(PY[i])){
                            String text[] = PY[i].split("&");
                            String json = "";
                            for (int j = 0; j < text.length; j++) {
                                if (!StringUtils.isEmpty(dataMap.get(text[j]))
                                                && !"null".equals(dataMap.get(text[j]))){
                                    json = json + dataMap.get(text[j]);
                                }
                            }
                            if (!StringUtils.isEmpty(json)){
                                TcpClient.getIntance().send(json);
                            }
                            LogUtils.log(TAG,PY[i] + "===" + json);
                            Thread.sleep(360);
                        }
                    }
                    /**符号**/
                    String duanju = dataMap.get(",");
                    TcpClient.getIntance().send(duanju);
                    LogUtils.log(TAG, "," + duanju);
                    Thread.sleep(400);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ALYTTSCompletedListener listener = new ALYTTSCompletedListener() {
        @Override
        public void InitSuccess() {

        }

        @Override
        public void TTSCompleted(int i) {
            if (i < content.length){
                playTTS(content[i], i + 1);
//                playU3d(content[i]);
                playPYU3d(juzi[i]);
            }else {
                try {
                    Thread.sleep(10);
                    AIUIUtils.getInstance().startVoiceNlp();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void TTSCompleted() {
            try {
                Thread.sleep(10);
                AIUIUtils.getInstance().startVoiceNlp();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    private StringBuffer sb = new StringBuffer();
    private XFYAIUIListener xfyaiuiListener = new XFYAIUIListener() {
        @Override
        public void AIUIResult(String result) {
            try {
                Thread.sleep(10);
                AIUIUtils.getInstance().stopAIUI();
            }catch (Exception e){
                e.printStackTrace();
            }
            //数字转汉字
            result = StringUtils.strNumtoChar(result);

            result = result.replaceAll("℃", "摄氏度");
            result = result.replaceAll("%", "百分之");
            sb.setLength(0);
            char[]texts = result.toCharArray();
            for (int i = 0; i < texts.length; i++) {
                String py = PinyinUtil.polishPinyin(PinYinUtils.getPinYin(texts[i]));
                sb.append(py);
            }
//                LogUtils.log("3");
            LogUtils.log(TAG,sb.toString());

            setData(result);
            setPYData(sb.toString());
            startPYData();
        }

        @Override
        public void AIUIRecode(String recode) {
            LogUtils.log(TAG,"********" + recode);
        }
    };
}
