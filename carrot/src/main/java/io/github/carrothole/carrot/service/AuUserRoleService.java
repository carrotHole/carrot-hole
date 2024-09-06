package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuUserRole;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuUserRoleService extends IService<AuUserRole> {

    /**
     * 删除部门下用户角色关联记录
     * @param userId 用户主键
     * @param deptId 部门主键
     * @return 删除结果
     */
    boolean remove(String userId, String deptId);

    /**
     * 删除部门下某项目的用户角色关联记录
     * @param userId 用户主键
     * @param deptId 部门主键
     * @param projectId 项目主键
     * @return 删除结果
     */
    boolean remove(String userId, String deptId, String projectId);
}
