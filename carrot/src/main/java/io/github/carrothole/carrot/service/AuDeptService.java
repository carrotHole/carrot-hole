package io.github.carrothole.carrot.service;

import io.github.carrothole.carrot.entity.vo.AuDeptTreeResultVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuDept;
import io.github.carrothole.carrot.entity.qo.AuDeptQueryVO;
import io.github.carrothole.carrot.entity.ro.AuDeptResultVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 0.0.1
 */
public interface AuDeptService extends IService<AuDept> {

    /**
     * 分页查询。
     * @param vo 分页信息
     * @param auDept 分页查询参数
     * @return Page<AuDept>
     */
    Page<AuDeptResultVO> page(PageVO vo, AuDeptQueryVO auDept);

    List<AuDept> listByIds(Collection<? extends Serializable> ids, String tenantId);

    List<AuDeptTreeResultVO> tree();
}
