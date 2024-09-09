package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRoleMenuRange;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import io.github.carrothole.carrot.mapper.AuRoleMenuRangeMapper;
import io.github.carrothole.carrot.service.AuRoleMenuRangeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.carrothole.carrot.entity.table.AuRoleMenuRangeTableDef.AU_ROLE_MENU_RANGE;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuRoleMenuRangeServiceImpl extends ServiceImpl<AuRoleMenuRangeMapper, AuRoleMenuRange> implements AuRoleMenuRangeService {

    @Override
    public boolean rangeReduce(@NotBlank(message = "主键不能为空") @Valid String id) {
        return this.remove(
                QueryWrapper.create()
                        .and(AU_ROLE_MENU_RANGE.ID.eq(id))
        );
    }

    @Override
    public List<AuRoleMenuRange> rangeExpand(RoleMenuRangeVO vo) {
        final List<AuRoleMenuRange> list = vo.getDeptIds()
                .stream()
                .distinct()
                .map(deptId -> AuRoleMenuRange.builder().roleMendId(vo.getRoleMenuId()).deptId(deptId).build())
                .collect(Collectors.toList());
        this.saveBatch(list);
        return list;
    }
}
