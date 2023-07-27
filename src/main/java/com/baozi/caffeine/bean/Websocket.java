package com.baozi.caffeine.bean;

/**
 * 作者：鲍庆洋
 * 时间：2023/6/14 9:37
 * 描述：
 */
public class Websocket {
    private int times = 0;

    public void refresh() {
        times++;
    }

    public int getTimes() {
        return times;
    }
}
