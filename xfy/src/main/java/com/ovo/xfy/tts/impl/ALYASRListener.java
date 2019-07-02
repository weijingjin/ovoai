package com.ovo.xfy.tts.impl;

/**
 * 语音识别返回结果
 */
public interface ALYASRListener {
    /**
     * 语音识别返回结果
     * @param results
     */
    void ALYASRResult(String results);
}
