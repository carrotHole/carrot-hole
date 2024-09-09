package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRoleMenu;
import io.github.carrothole.carrot.entity.AuRoleMenuRange;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 服务层。
 *
 * @author Administrator
 * @since 0.0.1
 */
public interface AuRoleMenuService extends IService<AuRoleMenu> {
    /**
     * 绑定菜单
     *
     * @param roleMenu {@link AuRoleMenu}
     * @return {@link AuRoleMenu}
     */
    AuRoleMenu bindMenu(@Valid AuRoleMenu roleMenu);

    /**
     * 解绑菜单
     *
     * @param roleMenuId 角色菜单主键
     * @return boolean
     */
    boolean unbindMenu(@Valid String id);

}
