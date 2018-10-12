package com.yihu.jw.base.util;

import com.yihu.jw.exception.code.BaseErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 错误码工具类
 * @author yeshijie on 2018/9/26.
 */
@Component
public class ErrorCodeUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 根据错误码获取错误提示信息
     * @param errorCode
     * @return
     */
    public String getErrorMsg(String errorCode){
        return redisTemplate.opsForValue().get(BaseErrorCode.PREFIX + errorCode);
    }
}
