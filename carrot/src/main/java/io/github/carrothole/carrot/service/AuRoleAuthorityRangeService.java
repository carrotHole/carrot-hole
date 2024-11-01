package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRoleAuthorityRange;

import java.util.List;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-10-24
 */
public interface AuRoleAuthorityRangeService extends IService<AuRoleAuthorityRange> {

    /**
     * 根据角色权限主键删除
     * @param authorityId 角色权限主键{@link io.github.carrothole.carrot.entity.AuRoleAuthority#id}
     * @return boolean
     */
    boolean removeByRoleAuthorityId(String authorityId);

    /**
     * 根据角色权限主键获取部门主键集合
     * @param authorityId 角色权限主键{@link io.github.carrothole.carrot.entity.AuRoleAuthority#id}
     * @return 部门主键集合
     */
    List<String> listDeptByRoleAuthorityId(String authorityId);
}
