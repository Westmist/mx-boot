package com.lcc.system.controller;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.lcc.api.vo.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户表 前端控制器
 *
 * @author mingxuan
 * @since 2021-03-31
 */
@RestController
@RequestMapping("/security")
public class MxUsersController {
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/login")
    public Result login(HttpServletResponse httpServletResponse) {
        // 用户校验
        if (true) {
            /**
             * 校验通过
             * 按用户名生成一定规则的Token
             * 将Token保存到redis中
             * 将Token添加到响应头中
             */
            String token = "token";
            redisTemplate.opsForValue().set("token", token);
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            return Result.ok("登陆成功");
        }
        return Result.error(204, "用户错误");
    }

    @PostMapping("/test")
    public Result test() {
        return Result.ok("请求成功！！");
    }

    @PostMapping("/errorA")
    public Result<?> errorA() {
        return Result.error(203, "登录过期");
    }

    @PostMapping("/errorB")
    public Result<?> errorB() {
        return Result.error(202, "未携带Token");
    }
}