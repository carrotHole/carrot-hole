package com.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.carrothole.carrot.entity.AuUser;
import com.carrothole.carrot.mapper.AuUserMapper;
import com.carrothole.carrot.service.AuUserService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuUserServiceImpl extends ServiceImpl<AuUserMapper, AuUser> implements AuUserService {

}
