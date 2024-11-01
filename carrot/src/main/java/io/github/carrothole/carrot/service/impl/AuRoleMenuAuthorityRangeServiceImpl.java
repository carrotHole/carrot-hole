package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthorityRange;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import io.github.carrothole.carrot.mapper.AuRoleMenuAuthorityRangeMapper;
import io.github.carrothole.carrot.service.AuRoleMenuAuthorityRangeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.carrothole.carrot.entity.table.AuRoleMenuAuthorityRangeTableDef.AU_ROLE_MENU_AUTHORITY_RANGE;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuRoleMenuAuthorityRangeServiceImpl extends ServiceImpl<AuRoleMenuAuthorityRangeMapper, AuRoleMenuAuthorityRange> implements AuRoleMenuAuthorityRangeService {

    @Override
    public boolean removeByRoleMenuAuthorityIds(List<String> ids) {
        return super.remove(
                QueryWrapper.create()
                        .and(AU_ROLE_MENU_AUTHORITY_RANGE.ROLE_MEND_ID.in(ids))
        );
    }

    @Override
    public boolean removeByRoleMenuId(String id) {
        return super.remove(
                QueryWrapper.create()
                        .and(AU_ROLE_MENU_AUTHORITY_RANGE.ROLE_MEND_ID.eq(id))
        );
    }

    @Override
    public List<String> listDeptByRoleMenuId(String id) {
        return super.listAs(
                QueryWrapper.create()
                        .and(AU_ROLE_MENU_AUTHORITY_RANGE.ROLE_MEND_ID.eq(id)),
                String.class
        );
    }
}
