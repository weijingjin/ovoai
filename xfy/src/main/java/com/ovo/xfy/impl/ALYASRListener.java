package com.ovo.xfy.impl;

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
