package com.wenge.data.entry.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 李亮
 * @date: 2019/12/31
 * @description: Redis的工具类
 */
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    // ===========================Common===============================

    /**
     * 指定缓存失效时间
     * @param key 键
     * @param timeOut 失效时间(秒)
     * @return
     */
    public boolean expire(String key, long timeOut){
        try {
            if (timeOut > 0) {
                redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取缓存失效时间
     * @param key   键 key不能为null
     * @return  缓存失效时间(秒) 返回 0 代表永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key   键
     * @return  true:存在 false:不存在
     */
    public boolean hashKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     * @param key 可以传一个，也可以传多个
     */
    @SuppressWarnings("uncheck")
    public void del(String... key){
        if (key != null && key.length > 0){
            if(key.length == 1){
                redisTemplate.delete(key);
            }else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    // ===========================String===============================

    /**
     * 普通缓存获取
     * @param key 键
     * @return
     */
    public Object get(String key){
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     * @param key   键
     * @param value 值
     * @return  true:成功 false:失败
     */
    public boolean set(String key, Object value){
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 普通缓存放入并设置过期时间
     * @param key   键
     * @param value 值
     * @param timeOut   过去时间
     * @return true:成功  false:失败
     */
    public boolean set(String key, Object value, long timeOut){
        try {
            if (timeOut > 0) {
                redisTemplate.opsForValue().set(key, value, timeOut);
            } else {
                redisTemplate.opsForValue().set(key, value);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     * @param key   键
     * @param delta 递增因子(大于0)
     * @return
     */
    public long incr(String key, long delta){
        if (delta < 0){
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     * @param key   键
     * @param delta 递增因子(小于0)
     * @return
     */
    public long decr(String key, long delta){
        if (delta > 0){
            throw new RuntimeException("递增因子必须小于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ===========================Map===============================

    /**
     * HashGet
     * @param key   键   不能为null
     * @param item  项   不能为null
     * @return
     */
    public Object hget(String key, String item){
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key   键
     * @return  对应的多个键值
     */
    public Map<Object, Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     * @param key   键
     * @param map   对应多个键值
     * @return  true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     * @param key   键
     * @param map   对应多个键值
     * @param time  时间(秒)
     * @return  true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
