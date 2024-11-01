package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthorityRange;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 0.0.1
 */
public interface AuRoleMenuAuthorityRangeService extends IService<AuRoleMenuAuthorityRange> {

    /**
     * 根据角色菜单主键删除
     * @param ids
     * @return boolean
     */
    boolean removeByRoleMenuAuthorityIds(List<String> ids);

    /**
     * 根据角色菜单主键删除
     * @param id 主键
     * @return boolean
     */
    boolean removeByRoleMenuId(String id);

    /**
     * 根据角色菜单主键获取部门主键列表
     * @param id 主键
     * @return 部门主键列表
     */
    List<String> listDeptByRoleMenuId(String id);
}
