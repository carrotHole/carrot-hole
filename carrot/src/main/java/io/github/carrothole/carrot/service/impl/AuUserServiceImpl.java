package io.github.carrothole.carrot.service.impl;

import io.github.carrothole.carrot.config.CarrotConstant;
import io.github.carrothole.carrot.entity.AuDeptUser;
import io.github.carrothole.carrot.entity.vo.AuUserOperationVO;
import io.github.carrothole.carrot.service.AuDeptUserService;
import io.github.carrothole.carrot.util.PassUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuUser;
import io.github.carrothole.carrot.mapper.AuUserMapper;
import io.github.carrothole.carrot.service.AuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
