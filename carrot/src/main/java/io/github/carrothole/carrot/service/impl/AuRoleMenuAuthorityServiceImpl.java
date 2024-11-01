package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthority;
import io.github.carrothole.carrot.mapper.AuRoleMenuAuthorityMapper;
import io.github.carrothole.carrot.service.AuRoleMenuAuthorityRangeService;
import io.github.carrothole.carrot.service.AuRoleMenuAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuRoleMenuAuthorityTableDef.AU_ROLE_MENU_AUTHORITY;


/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuRoleMenuAuthorityServiceImpl extends ServiceImpl<AuRoleMenuAuthorityMapper, AuRoleMenuAuthority> implements AuRoleMenuAuthorityService {

    @Autowired
    private AuRoleMenuAuthorityRangeService authorityRangeService;

    @Override
    @Transactional
    public boolean removeByRoleId(String roleId) {
        List<String> ids = super.listAs(QueryWrapper.create()
                        .select(AU_ROLE_MENU_AUTHORITY.ID)
                        .and(AU_ROLE_MENU_AUTHORITY.ROLE_ID.eq(roleId)),
                String.class);

        authorityRangeService.removeByRoleMenuAuthorityIds(ids);
        return super.removeByIds(ids);
    }

    @Override
    public List<AuRoleMenuAuthority> listByRoleId(String roleId) {
        return super.listAs(
                QueryWrapper.create()
                        .and(AU_ROLE_MENU_AUTHORITY.ROLE_ID.eq(roleId))
                , AuRoleMenuAuthority.class
        );
    }
}
