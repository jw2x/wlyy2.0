/**
 * 
 */
package com.yihu.base.security.sms.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author chenweida
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
