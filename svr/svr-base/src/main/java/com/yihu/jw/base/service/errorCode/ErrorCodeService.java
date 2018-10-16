package com.yihu.jw.base.service.errorCode;

import com.yihu.jw.base.dao.errorCode.ErrorCodeDao;
import com.yihu.jw.entity.base.dict.ErrorCodeDO;
import com.yihu.jw.exception.code.BaseErrorCode;
import com.yihu.mysql.query.BaseJpaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yeshijie on 2018/9/26.
 */
@Service
public class ErrorCodeService extends BaseJpaService<ErrorCodeDO, ErrorCodeDao> {


    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ErrorCodeDao errorCodeDao;

    /**
     * 初始化错误码，如果redis没有则查询数据库导入到redis中
     */
    public void initErrorCode(){
        String msg = redisTemplate.opsForValue().get(BaseErrorCode.PREFIX + BaseErrorCode.Common.FAIL_CREATE);
        if(StringUtils.isBlank(msg)){
            Iterable<ErrorCodeDO> list = errorCodeDao.findAll();
            list.forEach(code->{
                redisTemplate.opsForValue().set(BaseErrorCode.PREFIX + code.getErrorCode(),code.getErrorMsg());
            });
        }
    }

    /**
     * 初始化错误码（无条件）
     */
    public void initWithoutCondition(){
        Iterable<ErrorCodeDO> list = errorCodeDao.findAll();
        list.forEach(code->{
            redisTemplate.opsForValue().set(BaseErrorCode.PREFIX + code.getErrorCode(),code.getErrorMsg());
        });
    }

    /**
     * 新增
     * @param errorCodeDO
     */
    public ErrorCodeDO addErrorCode(ErrorCodeDO errorCodeDO){
        errorCodeDao.save(errorCodeDO);
        redisTemplate.opsForValue().set(BaseErrorCode.PREFIX + errorCodeDO.getErrorCode(),errorCodeDO.getErrorMsg());
        return errorCodeDO;
    }

    /**
     * 修改
     * @param id
     * @param msg
     */
    public ErrorCodeDO updateMsg(String id,String msg){
        ErrorCodeDO errorCodeDO = errorCodeDao.findOne(id);
        errorCodeDO.setErrorMsg(msg);
        errorCodeDao.save(errorCodeDO);
        redisTemplate.opsForValue().set(BaseErrorCode.PREFIX + errorCodeDO.getErrorCode(),msg);
        return errorCodeDO;
    }

    /**
     * 错误码是否存在
     * @param errorCode
     * @return
     */
    public int isExistsErrorCode(String errorCode){
        return errorCodeDao.isExistsErrorCode(errorCode);
    }

}
