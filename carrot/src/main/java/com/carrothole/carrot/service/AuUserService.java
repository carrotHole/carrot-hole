package com.carrothole.carrot.service;

import com.carrothole.carrot.entity.vo.AuUserOperationVO;
import com.mybatisflex.core.service.IService;
import com.carrothole.carrot.entity.AuUser;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuUserService extends IService<AuUser> {

    @Transactional
    boolean save(AuUserOperationVO vo);
}
