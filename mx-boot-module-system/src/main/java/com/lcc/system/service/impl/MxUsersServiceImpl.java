package com.lcc.system.service.impl;

import com.lcc.test.entity.MxUsers;
import com.lcc.test.mapper.MxUsersMapper;
import com.lcc.test.service.IMxUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mingxuan
 * @since 2021-03-31
 */
@Service
public class MxUsersServiceImpl extends ServiceImpl<MxUsersMapper, MxUsers> implements IMxUsersService {

}
