package com.lcc.system.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户类VO
 *
 * @author Cong-Cong Liao
 * @since 2021-04-01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class MxUsersVO implements Serializable {
    private static final long serialVersionUID=1L;

    /**
     * 用户邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String userEmail;

    /**
     * 用户密码
     */
    @NotEmpty(message = "密码不能为空")
    private String userPassword;
}
