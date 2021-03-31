package com.lcc.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lcc.system.entity.MxUsers;
import com.lcc.system.mapper.MxUsersMapper;
import com.lcc.system.service.IMxUsersService;
import org.springframework.stereotype.Service;


/**
 * 用户表 服务实现类
 *
 * @author mingxuan
 * @since 2021-03-31
 */
@Service
public class MxUsersServiceImpl extends ServiceImpl<MxUsersMapper, MxUsers> implements IMxUsersService {

}
