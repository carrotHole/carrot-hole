package io.github.carrothole.carrot.service;

import io.github.carrothole.carrot.entity.vo.LoginResultVO;
import io.github.carrothole.carrot.entity.vo.LoginVO;

/**
 * 登录服务
 *
 * @author moon
 * @since 0.0.1
 */
public interface AuLoginService {

    /**
     * 登录
     *
     * @param vo {@link LoginVO}
     * @return  {@link LoginResultVO}
     */
    LoginResultVO login(LoginVO vo);

    /**
     * 创建token
     *
     * @param identifyKey identifyKey
     * @param deptId 部门主键
     * @return token
     */
    String createToken(String identifyKey, String deptId);

    /**
     * 退出登录
     * @return boolean
     */
    boolean logout();

}
