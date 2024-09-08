package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRole;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.mapper.AuRoleMapper;
import io.github.carrothole.carrot.service.AuRoleService;
import org.springframework.stereotype.Service;

import static io.github.carrothole.carrot.entity.table.AuRoleTableDef.AU_ROLE;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuRoleServiceImpl extends ServiceImpl<AuRoleMapper, AuRole> implements AuRoleService {

    @Override
    public Page<AuRoleResultVO> page(PageVO page, AuRoleQueryVO vo) {

        return this.pageAs(
                page.buildPage(),
                page.appendOrderBy(
                        QueryWrapper.create()
                                .select(AU_ROLE.ALL_COLUMNS)
                                .and(AU_ROLE.DEPT_ID.eq(vo.getDeptId(), StrUtil.isNotBlank(vo.getDeptId())))
                                .and(AU_ROLE.PROJECT_ID.eq(vo.getProjectId(), StrUtil.isNotBlank(vo.getProjectId())))
                                .and(AU_ROLE.STATUS.eq(vo.getStatus(), vo.getStatus() != null))
                                .and(AU_ROLE.LEVEL.eq(vo.getLevel(), vo.getLevel() != null))

                                .and(AU_ROLE.CREATED_TIME.le(vo.getCreatedTimeEnd(), vo.getCreatedTimeEnd() != null))
                                .and(AU_ROLE.CREATED_TIME.ge(vo.getCreatedTimeBegin(), vo.getCreatedTimeBegin() != null))

                                .and(AU_ROLE.REMARK.like(vo.getRemark(), StrUtil.isNotBlank(vo.getRemark())))
                                .and(AU_ROLE.ROLE_NAME.like(vo.getRoleName(), StrUtil.isNotBlank(vo.getRoleName())))
                                .and(AU_ROLE.ROLE_TYPE.like(vo.getRoleType(), vo.getRoleType() != null))
                ),
                AuRoleResultVO.class
        );
    }
}
