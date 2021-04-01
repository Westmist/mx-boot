package com.lcc.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lcc.config.shiro.JwtToken;
import com.lcc.system.entity.MxUsers;
import com.lcc.system.entity.vo.MxUsersVO;
import com.lcc.system.service.IMxUsersService;
import com.lcc.system.service.impl.MxUsersServiceImpl;
import com.lcc.util.JwtUtil;
import com.lcc.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.lcc.api.vo.Result;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 用户表 前端控制器
 *
 * @author mingxuan
 * @since 2021-03-31
 */
@Api(tags = "用户认证Controller")
@Slf4j
@RestController
@RequestMapping("/security")
public class MxUsersController {
    @Resource
    private RedisUtil redisUtil;

    @Resource
    private IMxUsersService usersService;

    /**
     * redis 中的 Token 过期时间
     */
    @Value("${accessTokenExpireTime}")
    private Long accessTokenExpireTime;

    /**
     * 用户登陆接口
     *
     * @param usersVO             用户VO类 执行校验
     * @param httpServletResponse 响应
     * @return
     */
    @ApiOperation("用户登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "usersVO", value = "{'userEmail':'','userPassword':''}", required = true),
    })
    @PostMapping("/login")
    public Result login(@RequestBody @Validated MxUsersVO usersVO, @ApiIgnore HttpServletResponse httpServletResponse) {
        MxUsers mxUsers = usersService.validUser(usersVO);
        if (mxUsers != null) {
            // 验证通过
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            String token = JwtUtil.sign(mxUsers.getUserId(), currentTimeMillis);
            // 将token保存到redis中
            redisUtil.set("user:" + mxUsers.getUserId(), token, accessTokenExpireTime);
            // 将Token添加到响应头中
            httpServletResponse.setHeader("Authorization", token);
            httpServletResponse.setHeader("Access-Control-Expose-Headers", "Authorization");
            log.info(mxUsers.getUserEmail() + ":登陆成功。");
            return Result.ok("登陆成功");
        }
        log.info("账号或密码不存在。");
        return Result.error(202, "账号或密码不存在！");
    }

    @PostMapping("/test")
    public Result test() {
        return Result.ok("请求成功！！");
    }

    @RequestMapping("/error")
    public Result<?> errorA(HttpServletRequest request) {
        String message = (String) request.getAttribute("message");
        return Result.error(203, message);
    }
}