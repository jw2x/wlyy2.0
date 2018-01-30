package com.yihu.ehr.iot.security.core;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * Sso integrated
 * Created by progr1mmer on 2018/1/27.
 */
public class EhrWebAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private final Object principal;
    private Object credentials;
    private boolean isSso;

    // ~ Constructors
    // ===================================================================================================

    /**
     * This constructor can be safely used by any code that wishes to create a
     * <code>UsernamePasswordAuthenticationToken</code>, as the {@link #isAuthenticated()}
     * will return <code>false</code>.
     *
     */
        public EhrWebAuthenticationToken(Object principal, Object credentials, boolean isSso) {
        super(principal, credentials);
        this.principal = principal;
        this.credentials = credentials;
        this.isSso = isSso;
        setAuthenticated(false);
    }

    public boolean isSso() {
        return this.isSso;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
