package com.yihu.jw.restmodel.gateway;

/**
 * Created by chenweida on 2017/6/19.
 */

public class GatewayContanrts {
    /**
     * 日志切面用的静态类
     */
    public static final class ZipkinElasticKey {
        static public String gateway_input_params = "gateway_input_params";
        static public String gateway_out_params = "gateway_output_params";
        static public String gateway_error_params = "gateway_error_params";
    }

    /**
     * 登陆报错用的静态类
     */
    public static final class LoginErrorMessage {
        static public String account_no_exists = "用户不存在";
        static public String password_error = "密码错误";
    }
}
