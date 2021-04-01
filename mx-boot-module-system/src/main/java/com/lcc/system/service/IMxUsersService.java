package com.lcc.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lcc.system.entity.MxUsers;
import com.lcc.system.entity.vo.MxUsersVO;

/**
 * 用户表 服务类
 *
 * @author mingxuan
 * @since 2021-03-31
 */
public interface IMxUsersService extends IService<MxUsers> {
    /**
     * 用户校验
     * @param usersVO 用户VO类
     * @return
     */
    MxUsers validUser(MxUsersVO usersVO);
}
