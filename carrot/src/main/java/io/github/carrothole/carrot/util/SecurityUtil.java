package io.github.carrothole.carrot.util;

import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.entity.AuRole;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.enums.MenuRangeEnum;
import io.github.carrothole.carrot.service.AuMenuService;
import io.github.carrothole.carrot.service.AuRoleMenuService;
import io.github.carrothole.carrot.service.AuUserRoleService;
import io.github.carrothole.carrot.service.AuUserService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.github.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

/**
 * Description: 用户安全工具类 <br>
 * Date: 2024/8/30 15:51 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Configuration
public class SecurityUtil {

    public static ThreadLocal<String> tokenLocal = new ThreadLocal<>();

    public static void setToken(String token){
        SecurityUtil.tokenLocal.set(token);
    }

    public static boolean isAdmin(){
        return Arrays.asList("admin", "superman").contains(getUser().getUsername());
    }
    /**
     * 获取token<br>
     * 当前方法应该可以通过各种环境获取token
     * 1. servlet
     * 2. dubbo
     * 3. ws
     * @return token
     */
    public static String getToken(){
        String token = tokenLocal.get();
        if (token != null) return token;

        token = request.getHeader("Authorization");
        if (token != null) return token;

        // todo 其他环境获取token
        return token;
    }

    public static TokenUtil.TokenPayLoad getPayLoad(){
        return TokenUtil.getPayLoad(getToken());
    }



    public static AuUserResultVO getUser() {
        TokenUtil.TokenPayLoad payLoad = getPayLoad();
        // todo 添加缓存
        return auUserService.getOneAs(QueryWrapper.create().where(AU_USER.ID.eq(payLoad.getUserId())), io.github.carrothole.carrot.entity.ro.AuUserResultVO.class);
    }

    public static List<AuMenuResultVO> getMenuList(String project, TokenUtil.TokenPayLoad payLoad){
        // todo 添加缓存
        List<AuRoleResultVO> roleList = getRoleList(project, payLoad);

        final Set<String> roleIdSet = new HashSet<>();
        for (AuRoleResultVO auRoleResultVO : roleList) {
            if (auRoleResultVO.getMenuRange() == MenuRangeEnum.ALL.ordinal()){
                // 如果有一个角色指定了全部菜单，则直接返回全部菜单
                return auMenuService.listByProjectId(project);
            }

            roleIdSet.add(auRoleResultVO.getId());
        }
        return auRoleMenuService.listMenuByRoleIds(roleIdSet);
    }

    public static List<AuMenuResultVO> getMenuList(String project){
        return getMenuList(project, getPayLoad());
    }

    public static List<AuRoleResultVO> getRoleList(String project, TokenUtil.TokenPayLoad payLoad){
        AuRoleQueryVO auRoleQueryVO = new AuRoleQueryVO();
        auRoleQueryVO.setProjectId(project);
        return auUserRoleService.listRoleByUserId(payLoad.getUserId(), auRoleQueryVO);
    }

    public static List<AuRoleResultVO> getRoleList(String project){
        return getRoleList(project, getPayLoad());
    }

    @PostConstruct
    public void init(){
        SecurityUtil.request = request_;
        SecurityUtil.auUserService = auUserService_;
        SecurityUtil.auUserRoleService = auUserRoleService_;
        SecurityUtil.auRoleMenuService = auRoleMenuService_;
        SecurityUtil.auMenuService = auMenuService_;
    }


    private static HttpServletRequest request;
    private static AuUserService auUserService;
    private static AuUserRoleService auUserRoleService;
    private static AuRoleMenuService auRoleMenuService;
    private static AuMenuService auMenuService;

    @Autowired
    private AuUserService auUserService_;
    @Autowired
    private AuUserRoleService auUserRoleService_;
    @Autowired
    private AuRoleMenuService auRoleMenuService_;
    @Autowired
    private AuMenuService auMenuService_;
    @Autowired
    public HttpServletRequest request_;
}
