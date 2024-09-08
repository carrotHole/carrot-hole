package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuUserRole;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.UserRoleVO;
import jakarta.validation.Valid;

import java.util.List;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuUserRoleService extends IService<AuUserRole> {

    /**
     * 删除用户下所有角色
     * @param userId 用户主键
     * @return boolean
     */
    boolean remove(String userId);


    /**
     * 绑定角色
     * @param vo {@link UserRoleVO}
     * @return boolean
     */
    boolean bindRole(@Valid UserRoleVO vo);

    /**
     * 解绑角色
     * @param vo {@link UserRoleVO}
     * @return boolean
     */
    boolean unbindRole(@Valid UserRoleVO vo);

    /**
     * 根据用户主键获取角色列表
     * @param userId 用户主键
     * @return 角色列表
     */
    List<AuRoleResultVO> getRoleByUserId(String userId);
}
