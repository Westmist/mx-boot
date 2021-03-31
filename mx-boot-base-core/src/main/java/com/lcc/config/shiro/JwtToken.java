package com.lcc.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * JwtToken
 * @author Cong-Cong Liao
 * @since 2021-03-24
 */
public class JwtToken implements AuthenticationToken {
    /**
     * Token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
