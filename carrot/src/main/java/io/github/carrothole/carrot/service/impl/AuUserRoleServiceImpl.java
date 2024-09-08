package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuUserRole;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.UserRoleVO;
import io.github.carrothole.carrot.mapper.AuUserRoleMapper;
import io.github.carrothole.carrot.service.AuRoleService;
import io.github.carrothole.carrot.service.AuUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.carrothole.carrot.entity.table.AuRoleTableDef.AU_ROLE;
import static io.github.carrothole.carrot.entity.table.AuUserRoleTableDef.AU_USER_ROLE;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuUserRoleServiceImpl extends ServiceImpl<AuUserRoleMapper, AuUserRole> implements AuUserRoleService {

    @Autowired
    private AuRoleService auRoleService;

    @Override
    public boolean remove(String userId) {
        return this.remove(
                QueryWrapper.create()
                        .and(AU_USER_ROLE.USER_ID.eq(userId))
        );
    }

    @Override
    public boolean bindRole(UserRoleVO vo) {
        final List<AuUserRole> auUserRoles = vo.getRoleIds()
                .stream()
                .distinct()
                .map(roleId -> auRoleService.getById(roleId))
                .map(role -> new AuUserRole(vo.getUserId(), role.getId(), role.getProjectId()))
                .collect(Collectors.toList());
        return this.saveBatch(auUserRoles);
    }

    @Override
    public boolean unbindRole(UserRoleVO vo) {
        return this.remove(
                QueryWrapper.create()
                        .and(AU_USER_ROLE.USER_ID.eq(vo.getUserId()))
                        .and(AU_USER_ROLE.ROLE_ID.in(vo.getRoleIds()))
        );
    }

    @Override
    public List<AuRoleResultVO> getRoleByUserId(String userId) {
        return this
                .listAs(
                        QueryWrapper.create()
                                .select(AU_ROLE.ALL_COLUMNS)
                                .from(AU_ROLE)
                                .join(AU_USER_ROLE).on(AU_USER_ROLE.ROLE_ID.eq(AU_ROLE.ID))
                                .and(AU_USER_ROLE.USER_ID.eq(userId))
                        , AuRoleResultVO.class);
    }
}
