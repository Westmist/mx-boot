package com.lcc.config.shiro;

import com.lcc.config.shiro.filters.JwtFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Congcong Liao
 * @since 2021-03-04
 **/
@Configuration
public class ShiroConfig {
    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    /**
     * SecurityManager管理认证、授权整个流程
     */
    @Bean
    DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        // 设置禁用session
        manager.setSessionManager(defaultSessionManager());
        SecurityUtils.setSecurityManager(manager);
        return manager;
    }

    /**
     * 会话管理类 禁用session
     * @return
     */
    @Bean
    public DefaultSessionManager defaultSessionManager(){
        DefaultSessionManager manager = new DefaultSessionManager();
        manager.setSessionValidationSchedulerEnabled(false);
        return manager;
    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 自定义过滤器jwt
        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("jwt", new JwtFilter());
        // 将过滤链类型添加到shiro
        factoryBean.setFilters(filterMap);

        // 自定义url规则使用LinkedHashMap有序Map
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>(16);
        // 登陆与Token生成
        filterChainDefinitionMap.put("/security/login","anon");
        // JWT 口令校验
        filterChainDefinitionMap.put("/**", "jwt");
        // 登陆后的请求方法
        filterChainDefinitionMap.put("/security/test", "authc");
        // 所有请求通过我们自己的JWTFilter
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return factoryBean;
    }
}
