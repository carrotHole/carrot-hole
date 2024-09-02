package com.carrothole.carrot.service;

import com.carrothole.carrot.entity.vo.PageVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import com.carrothole.carrot.entity.AuDept;

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
    Page<AuDept> page(PageVO vo, AuDept auDept);
}
