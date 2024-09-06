package io.github.carrothole.carrot.service;


import com.mybatisflex.core.paginate.*;
import com.mybatisflex.core.service.*;
import io.github.carrothole.carrot.entity.*;
import io.github.carrothole.carrot.entity.vo.*;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuUserService extends IService<AuUser> {

    Page<AuUserResultVO> page(PageVO page, io.github.carrothole.carrot.entity.qo.AuUserQueryVO queryVO);

    /**
     * 修改密码
     * @param vo {@link ChangePasswordVO}
     * @return boolean
     */
    boolean updatePassword(ChangePasswordVO vo);
}
