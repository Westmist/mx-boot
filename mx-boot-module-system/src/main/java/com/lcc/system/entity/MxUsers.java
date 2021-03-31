package com.lcc.system.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author mingxuan
 * @since 2021-03-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MxUsers implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id主键
     */
      private String userId;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 激活状态
     */
    private Boolean activeState;

    /**
     * 注册时间
     */
    private LocalDateTime registerTime;

    /**
     * 登陆时间
     */
    private LocalDateTime loginTime;


}
