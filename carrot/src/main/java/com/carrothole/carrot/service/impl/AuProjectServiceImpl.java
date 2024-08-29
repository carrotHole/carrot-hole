package com.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.carrothole.carrot.entity.AuProject;
import com.carrothole.carrot.mapper.AuProjectMapper;
import com.carrothole.carrot.service.AuProjectService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuProjectServiceImpl extends ServiceImpl<AuProjectMapper, AuProject> implements AuProjectService {

}
