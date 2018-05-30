package com.yihu.jw.rm.base;

/**
 * Created by 刘文彬 on 2018/5/4.
 */
public class BaseSecurityRequestMapping {
    public static final String api_common=BaseRequestMapping.api_base_common+"/security";

    public static class BaseToken {
        public static final String api_update_token_expiration_time = "/update/tokenExpiration/time";
        public static final String api_update_token_expiration_second = "/update/tokenExpiration/second";
        public static final String api_update_token_expiration_second2 = "/update/tokenExpiration/second2";
        public static final String api_update_token_expiration = "/update/tokenExpiration";
    }
}
