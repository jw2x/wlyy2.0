package com.yihu.jw.security.oauth2.core.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by progr1mmer on 2018/4/18.
 */
public class WlyyRedisVerifyCodeService {

    private final String KEY_SUFFIX = ":code";
    private final RedisTemplate redisTemplate;

    public WlyyRedisVerifyCodeService(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void store (String client_id, String username, String code, long expire) {
        String key = client_id + ":" + username + KEY_SUFFIX;
        redisTemplate.opsForValue().set(key, code);
        redisTemplate.expire(key, expire, TimeUnit.MILLISECONDS);
    }

    public Integer getExpireTime (String client_id, String username) {
        return new Long(redisTemplate.getExpire(client_id + ":" + username + KEY_SUFFIX)).intValue();
    }

    public boolean verification (String client_id, String username, String code) {
        if (StringUtils.isEmpty(code)) {
            return false;
        }
        String _code = (String) redisTemplate.opsForValue().get(client_id + ":" + username + KEY_SUFFIX);
        if (null == _code) {
            return false;
        }
        if (code.equalsIgnoreCase(_code)) {
            return true;
        }
        return false;
    }

}
