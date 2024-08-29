package com.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.carrothole.carrot.entity.SysVersion;
import com.carrothole.carrot.mapper.SysVersionMapper;
import com.carrothole.carrot.service.SysVersionService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class SysVersionServiceImpl extends ServiceImpl<SysVersionMapper, SysVersion> implements SysVersionService {

}