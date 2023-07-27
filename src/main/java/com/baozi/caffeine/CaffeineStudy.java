package com.baozi.caffeine;

import com.baozi.caffeine.bean.Websocket;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * 作者：鲍庆洋
 * 时间：2023/6/14 9:33
 * 描述：
 */
public class CaffeineStudy {
    public static void main(String[] args) {
        Cache<Long, Websocket> cache = Caffeine.newBuilder()
                .build(k -> createWebsocket());

        // 查找一个缓存元素， 没有查找到的时候返回null
        Websocket ws = cache.getIfPresent(1L);
        // 查找缓存，如果缓存不存在则生成缓存元素,  如果无法生成则返回null
        ws = cache.get(2L, k -> createWebsocket());
        // 查找一个缓存元素， 没有查找到的时候返回null
        ws = cache.getIfPresent(1L);
        // 添加或者更新一个缓存元素
        cache.put(1L, new Websocket());
        // 移除一个缓存元素
        cache.invalidate(1L);
        Websocket ifPresent = cache.getIfPresent(1L);

    }

    public static Websocket createWebsocket() {
        return new Websocket();
    }

    public static Websocket refreshWebsocket(Long key) {
        return new Websocket();
    }
}
