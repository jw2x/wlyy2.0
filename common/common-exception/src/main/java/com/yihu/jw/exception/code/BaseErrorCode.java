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

        public static final String fail_upload = "-1";//上传失败
        public static final String fail_update = "-2";//修改失败
        public static final String fail_create = "-3";//新增失败
        public static final String fail_delete = "-4";//删除失败
        public static final String id_is_null = "-5";//ID不能为空

    }

    /**
     * 错误码模块
     */
    public static class ErrorCode{
        public static final String is_exist = "-10000";
    }

    /**
     * 菜单模块
     */
    public static class Menu{
        public static final String findDictByCode = "findDictByCode";
    }
}
