package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRoleAuthorityRange;
import io.github.carrothole.carrot.mapper.AuRoleAuthorityRangeMapper;
import io.github.carrothole.carrot.service.AuRoleAuthorityRangeService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuRoleAuthorityRangeTableDef.AU_ROLE_AUTHORITY_RANGE;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-10-24
 */
@Service
public class AuRoleAuthorityRangeServiceImpl extends ServiceImpl<AuRoleAuthorityRangeMapper, AuRoleAuthorityRange> implements AuRoleAuthorityRangeService {

    @Override
    public boolean removeByRoleAuthorityId(String authorityId) {
        return this.remove(
                QueryWrapper.create()
                .and(AU_ROLE_AUTHORITY_RANGE.ROLE_AUTHORITY_ID.eq(authorityId))
        );
    }

    @Override
    public List<String> listDeptByRoleAuthorityId(String authorityId) {
        return this.listAs(
                QueryWrapper.create()
                        .and(AU_ROLE_AUTHORITY_RANGE.ROLE_AUTHORITY_ID.eq(authorityId))
                ,String.class);
    }
}
