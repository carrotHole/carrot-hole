package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuRoleMenu;
import io.github.carrothole.carrot.entity.AuRoleMenuRange;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import io.github.carrothole.carrot.mapper.AuRoleMenuMapper;
import io.github.carrothole.carrot.service.AuRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
