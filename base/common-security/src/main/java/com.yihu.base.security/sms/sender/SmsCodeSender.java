/**
 * 
 */
package com.yihu.base.security.sms.sender;

/**
 * @author chenweida
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
