package com.ovo.xfy.tts.impl;

/**
 * tts播报完成
 */
public interface ALYTTSCompletedListener {
    /**
     * tts初始化完成
     */
    void InitSuccess();
    /**
     * tts播报完成
     *
     * @param i 一句单字下标
     */
    void TTSCompleted(int i);
    /**
     * tts播报完成
     */
    void TTSCompleted();
}
