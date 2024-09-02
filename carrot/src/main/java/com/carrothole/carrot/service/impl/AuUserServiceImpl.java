package com.carrothole.carrot.service.impl;

import com.carrothole.carrot.config.CarrotConstant;
import com.carrothole.carrot.entity.AuDeptUser;
import com.carrothole.carrot.entity.vo.AuUserOperationVO;
import com.carrothole.carrot.service.AuDeptUserService;
import com.carrothole.carrot.util.PassUtil;
import com.carrothole.carrot.util.SecurityUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.carrothole.carrot.entity.AuUser;
import com.carrothole.carrot.mapper.AuUserMapper;
import com.carrothole.carrot.service.AuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuUserServiceImpl extends ServiceImpl<AuUserMapper, AuUser> implements AuUserService {

    @Autowired
    private AuDeptUserService auDeptUserService;


    @Override
    public boolean save(AuUserOperationVO vo){
        // 密码加密
        vo.setPassword(PassUtil.encrypt(vo.getPassword(), CarrotConstant.PASSWORD_SALT));
        // 保存用户信息
        super.save(vo);

        // 保存部门用户信息
        final AuDeptUser auDeptUser = new AuDeptUser();
        auDeptUser.setUserId(vo.getId());
        auDeptUser.setDeptId(vo.getDeptId());
        auDeptUser.setRealUser(1);
        return auDeptUserService.save(auDeptUser);
    }

}
