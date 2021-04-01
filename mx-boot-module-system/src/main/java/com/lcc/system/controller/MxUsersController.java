package com.lcc.system.controller;


import com.lcc.system.entity.vo.MxUsersVO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.lcc.api.vo.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

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

    /**
     * 用户登陆接口
     *
     * @param usersVO             用户VO类 执行校验
     * @param httpServletResponse 响应
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Validated MxUsersVO usersVO, HttpServletResponse httpServletResponse) {

        System.out.println(usersVO);
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