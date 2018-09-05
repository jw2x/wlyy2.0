package com.yihu.jw.security.oauth2.core.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Service - 验证码缓存
 * Created by progr1mmer on 2018/4/18.
 */
public class WlyyRedisVerifyCodeService {

    private final String KEY_SUFFIX = ":code";
    private final RedisTemplate redisTemplate;

    public WlyyRedisVerifyCodeService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void store (String client_id, String username, String code, int expire) {
        String key = client_id + ":" + username + KEY_SUFFIX;
        redisTemplate.opsForValue().set(key, code);
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        String intervalKey = key + ":" + code + "_interval";
        redisTemplate.opsForValue().set(intervalKey, 60);
        redisTemplate.expire(intervalKey, 60, TimeUnit.SECONDS);
    }

    public boolean isIntervalTimeout(String client_id, String username) {
        String key = client_id + ":" + username + KEY_SUFFIX;
        String code = (String) redisTemplate.opsForValue().get(key);
        if (null == code) {
            return true;
        }
        String intervalKey = key + ":" + code + "_interval";
        if (redisTemplate.opsForValue().get(intervalKey) != null) {
            return false;
        }
        return true;
    }

    public boolean verification (String client_id, String username, String code) {
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        String key = client_id + ":" + username + KEY_SUFFIX;
        String _code = (String) redisTemplate.opsForValue().get(key);
        if (null == _code) {
            return false;
        }
        if (code.equalsIgnoreCase(_code)) {
            return true;
        }
        return false;
    }

}
