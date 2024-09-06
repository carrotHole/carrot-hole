package io.github.carrothole.carrot.service;

import io.github.carrothole.carrot.entity.vo.PageVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuDept;
import io.github.carrothole.carrot.entity.qo.AuDeptQueryVO;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuDeptService extends IService<AuDept> {

    /**
     * 分页查询。
     * @param vo 分页信息
     * @param auDept 分页查询参数
     * @return Page<AuDept>
     */
    Page<io.github.carrothole.carrot.entity.ro.AuDeptResultVO> page(PageVO vo, AuDeptQueryVO auDept);
}
