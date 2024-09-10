package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRoleMenu;
import io.github.carrothole.carrot.entity.AuRoleMenuRange;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import io.github.carrothole.carrot.mapper.AuRoleMenuMapper;
import io.github.carrothole.carrot.service.AuRoleMenuService;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.Collection;
import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuMenuTableDef.AU_MENU;
import static io.github.carrothole.carrot.entity.table.AuRoleMenuTableDef.AU_ROLE_MENU;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuRoleMenuServiceImpl extends ServiceImpl<AuRoleMenuMapper, AuRoleMenu> implements AuRoleMenuService {
    @Override
    public AuRoleMenu bindMenu(AuRoleMenu roleMenu) {
        this.save(roleMenu);
        return roleMenu;
    }

    @Override
    public boolean unbindMenu(String id) {
        this.removeById(id);
        return false;
    }

    @Override
    public List<AuMenuResultVO> listMenuByRoleIds(Collection<String> roleIdSet) {
        return super.listAs(
                QueryWrapper.create()
                        .select(AU_MENU.ALL_COLUMNS)
                        .from(AU_MENU)
                        .rightJoin(AU_ROLE_MENU).on(AU_ROLE_MENU.MENU_ID.eq(AU_MENU.ID))
                        .and(AU_ROLE_MENU.ROLE_ID.in(roleIdSet)),
                AuMenuResultVO.class
        );
    }

}
