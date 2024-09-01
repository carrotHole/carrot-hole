package com.carrothole.carrot.service.impl;

import com.carrothole.carrot.util.SecurityUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.carrothole.carrot.entity.AuDept;
import com.carrothole.carrot.mapper.AuDeptMapper;
import com.carrothole.carrot.service.AuDeptService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuDeptServiceImpl extends ServiceImpl<AuDeptMapper, AuDept> implements AuDeptService {

    @Override
    public boolean save(AuDept entity) {
        entity.setCreatedBy(SecurityUtil.getPayLoad().getUsername());
        entity.setCreatedTime(new Date());
        // 设置父主键部门集合
        return super.save(entity);
    }
}
