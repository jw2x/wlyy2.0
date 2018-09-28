package com.yihu.jw.exception.code;

/**
 * @author yeshijie on 2018/9/26.
 */
public class BaseErrorCode {

    public static final String PREFIX = "error:code:";//定义redis的前缀

    /**
     * 公共模块
     * -1 ~ -9999
     * 每个模块的错误码至少保留分100个值
     */
    public static class Common{

        public static final String FAIL_UPLOAD = "-1";//上传失败
        public static final String FAIL_UPDATE = "-2";//修改失败
        public static final String FAIL_CREATE = "-3";//新增失败
        public static final String FAIL_DELETE = "-4";//删除失败
        public static final String ID_IS_NULL = "-5";//ID不能为空

    }

    /**
     * 错误码模块
     */
    public static class ErrorCode{
        public static final String IS_EXIST = "-10000";
    }

    /**
     * 租户模块
     */
    public static class Saas{
        public static final String SAAS = "-101000";
    }

    /**
     * 菜单模块
     */
    public static class Menu{
        public static final String NAME_IS_EXIST = "-102000";
    }

    /**
     * 业务模块
     */
    public static class Module{
        public static final String FINDDICTBYCODE = "-103000";
    }

    /**
     * 字典模块
     */
    public static class Dict{
        public static final String FINDDICTBYCODE = "-104000";
    }

    /**
     * 通知公告模块
     */
    public static class Notice{
        public static final String FINDDICTBYCODE = "-105000";
    }

    /**
     * 接口模块
     */
    public static class Interface{
        public static final String FINDDICTBYCODE = "-106000";
    }

    /**
     * 微信模块
     */
    public static class Wechat{
        public static final String WECHAT = "-1000000";
    }
}
