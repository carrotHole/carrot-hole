package io.github.carrothole.carrot.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRole;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
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
}
