package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuTenant;
import io.github.carrothole.carrot.mapper.AuTenantMapper;
import io.github.carrothole.carrot.service.AuTenantService;
import org.springframework.stereotype.Service;

import static io.github.carrothole.carrot.entity.table.AuTenantTableDef.AU_TENANT;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuTenantServiceImpl extends ServiceImpl<AuTenantMapper, AuTenant> implements AuTenantService {

    @Override
    public AuTenant getByTenantMark(String tenantMark) {
        return super.getOne(
                QueryWrapper.create()
                        .and(AU_TENANT.TENANT_MARK.eq(tenantMark))
        );
    }
}
