package io.github.carrothole.carrot.service;


import com.mybatisflex.core.paginate.*;
import com.mybatisflex.core.service.*;
import io.github.carrothole.carrot.entity.*;
import io.github.carrothole.carrot.entity.vo.*;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;
import jakarta.validation.Valid;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuUserService extends IService<AuUser> {

    Page<AuUserResultVO> page(PageVO page, io.github.carrothole.carrot.entity.qo.AuUserQueryVO queryVO);

    /**
     * 修改密码<br/>
     * 注意修改密码是根据username进行的,会将真实用户和虚拟用户密码同时修改
     * @param vo {@link ChangePasswordVO}
     * @return boolean
     */
    boolean updatePassword(ChangePasswordVO vo);

    /**
     * 修改状态
     * @param vo {@link ChangeStatusVO}
     * @return boolean
     */
    boolean updateStatus(@Valid ChangeStatusVO vo);
}
