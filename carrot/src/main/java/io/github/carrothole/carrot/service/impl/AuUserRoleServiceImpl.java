package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuUserRole;
import io.github.carrothole.carrot.mapper.AuUserRoleMapper;
import io.github.carrothole.carrot.service.AuUserRoleService;
import org.springframework.stereotype.Service;

import static io.github.carrothole.carrot.entity.table.AuUserRoleTableDef.AU_USER_ROLE;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuUserRoleServiceImpl extends ServiceImpl<AuUserRoleMapper, AuUserRole> implements AuUserRoleService {

    @Override
    public boolean remove(String userId, String deptId) {
        return this.remove(
                QueryWrapper.create()
                        .and(AU_USER_ROLE.USER_ID.eq(userId))
                        .and(AU_USER_ROLE.DEPT_ID.eq(deptId))
        );
    }

    @Override
    public boolean remove(String userId, String deptId, String projectId) {
        return this.remove(
                QueryWrapper.create()
                        .and(AU_USER_ROLE.USER_ID.eq(userId))
                        .and(AU_USER_ROLE.DEPT_ID.eq(deptId))
                        .and(AU_USER_ROLE.PROJECT_ID.eq(projectId))
        );
    }
}
