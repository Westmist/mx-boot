package com.lcc.config.shiro;

import com.lcc.util.JwtUtil;
import com.lcc.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author Congcong Liao
 * @since 2021-03-04
 **/
@Slf4j
public class MyRealm extends AuthorizingRealm {
    @Resource
    private RedisUtil redisUtil;

    /**
     * 授权管理
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole("admin");
        return simpleAuthorizationInfo;
    }

    /**
     * 认证管理
     *
     * @param auth 认证口令
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // 前端传过来的Token
        String token = (String) auth.getCredentials();
        // 校验是否存在该key
        String realToken = (String) redisUtil.get("user:" + JwtUtil.getClaim(token, "account"));
        if (realToken == null) {
            throw new AuthenticationException("登录过期请重新登录！");
        }
        // 校验key是否一致
        if (!token.equals(realToken)) {
            throw new AuthenticationException("Token错误！");
        }
        // 校验通过进入JwtFilter
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    /**
     * 更改shiro的认证方式
     *
     * @param authenticationToken 认证口令
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof JwtToken;
    }

}
