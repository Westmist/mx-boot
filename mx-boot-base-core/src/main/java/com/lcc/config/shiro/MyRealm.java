package com.lcc.config.shiro;

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
    private RedisTemplate redisTemplate;

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
        /*
         * Token 校验
         * 根据请求传过来的Token获取用户名
         * 用户名为key 比对redis中的Token
         */
        if (redisTemplate.opsForValue().get(token) == null) {
           throw new AuthenticationException("登录过期请重新登录");
        }
        // TODO Redis Token 比对
        // Redis 存在走controller
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
