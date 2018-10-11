package com.yihu.jw.util.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @author Air
 * @version 1.0
 * @created 2015.07.02 15:48
 */
public class AllowAllHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
