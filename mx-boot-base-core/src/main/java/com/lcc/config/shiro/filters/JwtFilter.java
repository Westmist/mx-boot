package com.lcc.config.shiro.filters;

import com.lcc.config.shiro.JwtToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;


/**
 * 自定义shiro拦截器
 * 继承shiro的认证
 *
 * @author dolyw.com
 * @date 2018/8/30 15:47
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 有携带Token
        if (this.isLoginAttempt(request, response)) {
            try {
                // 登陆
                this.executeLogin(request, response);
            } catch (Exception e) {
                // 出现登陆异常接受传递过来的异常信息
                String msg = e.getMessage();
                System.out.println(msg);
                try {
                    request.getRequestDispatcher("/security/errorA").forward(request,response);
                } catch (ServletException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
            return true;
        }
        System.out.println("未携带Token");
        try {
            request.getRequestDispatcher("/security/errorB").forward(request,response);
        } catch (ServletException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        String token = this.getAuthzHeader(request);
        return token != null;
    }

    /**
     * 进行AccessToken登录认证授权
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        JwtToken token = new JwtToken(this.getAuthzHeader(request));
        // 出现异常则抛出到isAccessAllowed中处理
        Subject subject = this.getSubject(request, response);
        subject.login(token);
        if (subject.isAuthenticated()) {
            System.out.println("认证通过");
        } else{
            System.out.println("认证失败");
        }
        return true;
    }

    /**
     * 这里我们详细说明下为什么重写
     * 可以对比父类方法，只是将executeLogin方法调用去除了
     * 如果没有去除将会循环调用doGetAuthenticationInfo方法
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.sendChallenge(request, response);
        return false;
    }
}

