package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthority;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthorityRange;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 服务层。
 *
 * @author Administrator
 * @since 0.0.1
 */
public interface AuRoleMenuAuthorityService extends IService<AuRoleMenuAuthority> {

    boolean removeByRoleId(String roleId);

    /**
     * 根据角色主键获取角色菜单授权信息
     * @param roleId
     * @return
     */
    List<AuRoleMenuAuthority> listByRoleId(String roleId);
}
