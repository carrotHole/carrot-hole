package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.entity.AuDept;
import io.github.carrothole.carrot.entity.AuUser;
import io.github.carrothole.carrot.entity.vo.LoginResultUserVO;
import io.github.carrothole.carrot.entity.vo.LoginResultVO;
import io.github.carrothole.carrot.entity.vo.LoginVO;
import io.github.carrothole.carrot.exception.AuthorizationException;
import io.github.carrothole.carrot.service.AuDeptService;
import io.github.carrothole.carrot.service.AuLoginService;
import io.github.carrothole.carrot.service.AuUserService;
import io.github.carrothole.carrot.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

/**
 * 登录服务实现
 *
 * @author moon
 * @since 0.0.1
 */
@Service
public class AuLoginServiceImpl implements AuLoginService {

    @Autowired
    private AuUserService userService;

    @Autowired
    private AuDeptService deptService;

    @Override
    public LoginResultVO login(LoginVO vo) {
        List<AuUser> users = CheckUtil.checkNotNull(userService.ListUserByUserName(vo.getUsername()), new AuthorizationException("用户名或密码错误"));
        AuUser user = users.get(0);
        if (!BoolUtil.isTrue(user.getStatus())) {
            throw new AuthorizationException("用户已禁用");
        }
        if (!PassUtil.check(vo.getPassword(), user.getPassword())) {
            throw new AuthorizationException("用户名或密码错误");
        }

        // 获取所有用户
        List<String> deptIds = users.stream().map(AuUser::getDeptId).collect(Collectors.toList());
        List<AuDept> depts = deptService.listByIds(deptIds);
        // todo 获取当前用户最后登录的信息,并赋值进登录结果
        List<LoginResultUserVO> resultUsers = users.stream()
                .map(
                        user_ -> LoginResultUserVO.builder()
                                .username(user_.getUsername())
                                .nickname(user_.getNickname())
                                .realUser(user_.getRealUser())
                                .deptId(user_.getDeptId())
                                .deptName(depts.stream().filter(dept -> dept.getId().equals(user_.getDeptId())).findFirst().get().getDeptName())
                                .lastLogin(0)
                                .build()
                ).collect(Collectors.toList());
        String identityKey = IdUtil.simpleUUID();
        CacheUtil.addCache(CacheKeyUtil.getIdentifierKey(identityKey), vo, 30 * 60 * 1000L);
        return LoginResultVO.builder().identityKey(identityKey).users(resultUsers).build();
    }

    @Override
    public String createToken(String identifyKey, String deptId) {
        CacheUtil.CacheValue cacheValue = CacheUtil.removeCache(identifyKey);
        if (cacheValue == null || cacheValue.getExpireTime() < System.currentTimeMillis()) {
            throw new AuthorizationException("登录已过期");
        }
        LoginVO vo = (LoginVO) cacheValue.getValue();
        AuUserResultVO auUser = CheckUtil.checkNotNull(userService.getOneAs(QueryWrapper.create().and(AU_USER.USERNAME.eq(vo.getUsername())).and(AU_USER.DEPT_ID.eq(deptId)), io.github.carrothole.carrot.entity.ro.AuUserResultVO.class), "登录token异常");

        // 创建token
        return TokenUtil.create(auUser.getTenantId(), auUser.getDeptId(), auUser.getUsername(), BoolUtil.isTrue(vo.getRememberMe()) ? 30 * 24 * 60 * 60 * 1000L : 10 * 60 * 60 * 1000L);
    }

    @Override
    public boolean logout() {
        TokenUtil.TokenPayLoad payLoad = SecurityUtil.getPayLoad();
        TokenUtil.remove(payLoad.getTenantId(), payLoad.getUsername(), payLoad.getRandom());
        CacheUtil.removeCache(CacheKeyUtil.getUserKey(payLoad.getTenantId(),payLoad.getUsername(), payLoad.getRandom()));
        // todo 清空当前用户相关的缓存信息
        return true;
    }
}
