package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuTenant;
import io.github.carrothole.carrot.mapper.AuTenantMapper;
import io.github.carrothole.carrot.service.AuTenantService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuTenantServiceImpl extends ServiceImpl<AuTenantMapper, AuTenant> implements AuTenantService {

}