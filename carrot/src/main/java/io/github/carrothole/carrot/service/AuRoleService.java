package io.github.carrothole.carrot.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRole;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.AuRoleAuthorityResultVO;
import io.github.carrothole.carrot.entity.vo.AuRoleAuthorityVO;
import io.github.carrothole.carrot.entity.vo.AuRoleMenuAuthorityVO;
import io.github.carrothole.carrot.entity.vo.PageVO;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 0.0.1
 */
public interface AuRoleService extends IService<AuRole> {

    /**
     * 分页查询
     * @param page 分页对象
     * @param vo {@link AuRoleQueryVO}
     * @return {@link Page}
     */
    Page<AuRoleResultVO> page(PageVO page, AuRoleQueryVO vo);

    /**
     * 新增授权
     * @param vo {@link AuRoleAuthorityVO}
     * @return boolean
     */
    boolean saveAuthority(AuRoleAuthorityVO vo);

    /**
     * 新增菜单授权
     * @param vo {@link AuRoleMenuAuthorityVO}
     * @return boolean
     */
    boolean saveMenuAuthority(AuRoleMenuAuthorityVO vo);

    /**
     * 更新授权
     * @param vo {@link AuRoleAuthorityVO}
     * @return boolean
     */
    boolean updateAuthority(AuRoleAuthorityVO vo);

    boolean updateMenuAuthority(AuRoleMenuAuthorityVO vo);

    /**
     * 删除菜单授权
     * @param id roleMenuId
     * @return boolean
     */
    boolean removeMenuAuthority(String id);

    /**
     * 查询授权
     * @param id roleId
     * @return
     */
    AuRoleAuthorityResultVO getAuthority(String id);
}
