package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.update.UpdateChain;
import com.mybatisflex.core.update.UpdateWrapper;
import com.mybatisflex.core.util.UpdateEntity;
import io.github.carrothole.carrot.config.CarrotConstant;
import io.github.carrothole.carrot.entity.qo.AuUserQueryVO;
import io.github.carrothole.carrot.entity.vo.ChangePasswordVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.service.AuUserRoleService;
import io.github.carrothole.carrot.util.PassUtil;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuUser;
import io.github.carrothole.carrot.mapper.AuUserMapper;
import io.github.carrothole.carrot.service.AuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;

import static io.github.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Service
public class AuUserServiceImpl extends ServiceImpl<AuUserMapper, AuUser> implements AuUserService {

    @Autowired
    private AuUserRoleService auUserRoleService;

    @Override
    @Transactional
    public boolean save(AuUser auUser){
        // 密码加密
        auUser.setPassword(PassUtil.encrypt(auUser.getPassword(), CarrotConstant.PASSWORD_SALT));
        // 保存用户信息
        return super.save(auUser);
    }

    @Override
    @Transactional
    public boolean removeById(AuUser entity) {
        // 删除用户角色绑定
        auUserRoleService.remove(entity.getId(),entity.getDeptId());
        return super.removeById(entity);
    }

    @Override
    public boolean updateById(AuUser entity) {
        // 以下内容不能修改
        entity.setUsername(null);
        entity.setPassword(null);
        entity.setRealUser(null);
        return super.updateById(entity);
    }

    @Override
    public Page<AuUserResultVO> page(PageVO page, AuUserQueryVO queryVO) {
        // todo 字段映射
        return this.pageAs(
                page.buildPage(),
                page.appendOrderBy(
                        QueryWrapper.create(),
                        AU_USER.SORT.asc(),AU_USER.CREATED_TIME.desc()
                ),
                AuUserResultVO.class
        );
    }

    @Override
    public boolean updatePassword(ChangePasswordVO vo) {
        // todo 未完
        UpdateChain.of(AuUser.class)
                .set(AU_USER);

        return false;
    }
}