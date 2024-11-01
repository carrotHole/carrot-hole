package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.*;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.AuRoleAuthorityResultVO;
import io.github.carrothole.carrot.entity.vo.AuRoleAuthorityVO;
import io.github.carrothole.carrot.entity.vo.AuRoleMenuAuthorityVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.enums.AuRangeEnum;
import io.github.carrothole.carrot.mapper.AuRoleMapper;
import io.github.carrothole.carrot.service.*;
import io.github.carrothole.carrot.util.BoolUtil;
import io.github.carrothole.carrot.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.carrothole.carrot.entity.table.AuRoleTableDef.AU_ROLE;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuRoleServiceImpl extends ServiceImpl<AuRoleMapper, AuRole> implements AuRoleService {

    @Autowired
    private AuRoleAuthorityService roleAuthorityService;


    @Autowired
    private AuRoleAuthorityRangeService roleAuthorityRangeService;

    @Autowired
    private AuRoleMenuAuthorityService roleMenuService;

    @Autowired
    private AuRoleMenuAuthorityRangeService roleMenuRangeService;

    @Override
    public Page<AuRoleResultVO> page(PageVO page, AuRoleQueryVO vo) {

        return this.pageAs(
                page.buildPage(),
                page.appendOrderBy(
                        QueryWrapper.create()
                                .select(AU_ROLE.ALL_COLUMNS)
                                .and(AU_ROLE.DEPT_ID.eq(vo.getDeptId(), StrUtil.isNotBlank(vo.getDeptId())))
                                .and(AU_ROLE.PROJECT_ID.eq(vo.getProjectId(), StrUtil.isNotBlank(vo.getProjectId())))
                                .and(AU_ROLE.STATUS.eq(vo.getStatus(), vo.getStatus() != null))

                                .and(AU_ROLE.CREATED_TIME.le(vo.getCreatedTimeEnd(), vo.getCreatedTimeEnd() != null))
                                .and(AU_ROLE.CREATED_TIME.ge(vo.getCreatedTimeBegin(), vo.getCreatedTimeBegin() != null))

                                .and(AU_ROLE.REMARK.like(vo.getRemark(), StrUtil.isNotBlank(vo.getRemark())))
                                .and(AU_ROLE.ROLE_NAME.like(vo.getRoleName(), StrUtil.isNotBlank(vo.getRoleName())))
                ),
                AuRoleResultVO.class
        );
    }

    @Override
    @Transactional
    public boolean saveAuthority(AuRoleAuthorityVO vo) {

        // 保存roleAuthority
        AuRoleAuthority roleAuthority = vo.getRoleAuthority();
        roleAuthorityService.save(roleAuthority);

        // 保存roleAuthorityRange
        if (AuRangeEnum.CUSTOM.hasMe(roleAuthority.getAuRange())){
            List<String> deptIdList = vo.getDeptIdList();
            Set<AuRoleAuthorityRange> authorityRangeSet = deptIdList.stream().map(deptId -> AuRoleAuthorityRange.builder().deptId(deptId).roleAuthorityId(roleAuthority.getId()).build()).collect(Collectors.toSet());
            roleAuthorityRangeService.saveBatch(authorityRangeSet);
        }
        return true;
    }

    @Override
    @Transactional
    public boolean saveMenuAuthority(AuRoleMenuAuthorityVO vo) {
        // 保存roleMenuAuthority
        AuRoleMenuAuthority roleMenuAuthority = vo.getRoleMenuAuthority();
        roleMenuService.save(roleMenuAuthority);

        // 保存roleMenuAuthorityRange
        if (BoolUtil.isTrue(roleMenuAuthority.getAuRange())){
            List<String> deptIdList = vo.getDeptIdList();
            Set<AuRoleMenuAuthorityRange> authorityRangeSet = deptIdList.stream().map(deptId -> AuRoleMenuAuthorityRange.builder().deptId(deptId).roleMendId(roleMenuAuthority.getId()).build()).collect(Collectors.toSet());
            roleMenuRangeService.saveBatch(authorityRangeSet);
        }

        return true;
    }

    @Override
    @Transactional
    public boolean updateAuthority(AuRoleAuthorityVO vo) {


        // 更新roleAuthority
        AuRoleAuthority roleAuthority = vo.getRoleAuthority();

        AuRoleAuthority beforeAuthority =  CheckUtil.checkNotNull(roleAuthorityService.getById(roleAuthority.getId()),"未找到当前角色授权信息");

        roleAuthorityService.updateById(roleAuthority);

        // 删除roleAuthorityRange
        if (AuRangeEnum.CUSTOM.hasMe(beforeAuthority.getAuRange())){
            roleAuthorityRangeService.removeByRoleAuthorityId(roleAuthority.getId());
        }
        // 保存roleAuthorityRange
        List<String> deptIdList = vo.getDeptIdList();
        if (AuRangeEnum.CUSTOM.hasMe(roleAuthority.getAuRange()) && deptIdList != null && !deptIdList.isEmpty()){
            Set<AuRoleAuthorityRange> authorityRangeSet = deptIdList.stream().map(deptId -> AuRoleAuthorityRange.builder().deptId(deptId).roleAuthorityId(roleAuthority.getId()).build()).collect(Collectors.toSet());
            roleAuthorityRangeService.saveBatch(authorityRangeSet);
        }

        // 删除roleMenuAuthority
        if (AuRoleAuthority.AuMenu.ALL.isMe(roleAuthority.getAuMenu()) && AuRoleAuthority.AuMenu.CUSTOM.isMe(beforeAuthority.getAuMenu())){
            roleMenuService.removeByRoleId(roleAuthority.getRoleId());
        }

        return true;
    }

    @Override
    public boolean updateMenuAuthority(AuRoleMenuAuthorityVO vo) {
        AuRoleMenuAuthority roleMenuAuthority = vo.getRoleMenuAuthority();
        AuRoleMenuAuthority beforeRoleMenuAuthority = CheckUtil.checkNotNull(roleMenuService.getById(roleMenuAuthority.getId()), "未找到当前授权信息");

        // 更新auRoleMenuAuthority
        roleMenuService.updateById(roleMenuAuthority);

        // 删除roleMenuAuthorityRange
        if (AuRangeEnum.CUSTOM.hasMe(beforeRoleMenuAuthority.getAuRange())){
            roleMenuRangeService.removeByRoleMenuId(roleMenuAuthority.getId());
        }

        // 新增roleMenuAuthorityRange
        List<String> deptIdList = vo.getDeptIdList();
        if(BoolUtil.isFalse(roleMenuAuthority.getUseDefault()) && AuRangeEnum.CUSTOM.hasMe(roleMenuAuthority.getAuRange()) && deptIdList != null && !deptIdList.isEmpty()){
            Set<AuRoleMenuAuthorityRange> authorityRangeSet = deptIdList.stream().map(deptId -> AuRoleMenuAuthorityRange.builder().deptId(deptId).roleMendId(roleMenuAuthority.getId()).build()).collect(Collectors.toSet());
            roleMenuRangeService.saveBatch(authorityRangeSet);
        }

        return true;
    }

    @Override
    public boolean removeMenuAuthority(String id) {
        AuRoleMenuAuthority roleMenuAuthority = CheckUtil.checkNotNull(roleMenuService.getById(id), "未找到当前授权信息");
        roleMenuService.removeById(id);
        if (BoolUtil.isFalse(roleMenuAuthority.getUseDefault()) && AuRangeEnum.CUSTOM.hasMe(roleMenuAuthority.getAuRange())){
            roleMenuRangeService.removeByRoleMenuId(id);
        }
        return true;
    }

    @Override
    public AuRoleAuthorityResultVO getAuthority(String id) {
        AuRoleAuthorityResultVO vo = new AuRoleAuthorityResultVO();
        AuRoleAuthority roleAuthority = roleAuthorityService.getById(id);
        vo.setRoleAuthority(roleAuthority);

        if (roleAuthority != null ){
            // 获取roleAuthorityRange
            if ( AuRangeEnum.CUSTOM.hasMe(roleAuthority.getAuRange())){
                List<String> deptIds = roleAuthorityRangeService.listDeptByRoleAuthorityId(roleAuthority.getId());
                vo.setDeptIdList(deptIds);
            }
            // 获取roleMenuAuthority
            List<AuRoleMenuAuthority> roleMenuAuthorityList = roleMenuService.listByRoleId(roleAuthority.getRoleId());
            roleMenuAuthorityList.stream().forEach(roleMenuAuthority -> {
                if (BoolUtil.isFalse(roleMenuAuthority.getUseDefault()) && AuRangeEnum.CUSTOM.hasMe(roleMenuAuthority.getAuRange())){
                    List<String> deptIds = roleMenuRangeService.listDeptByRoleMenuId(roleMenuAuthority.getId());
                    vo.setDeptIdList(deptIds);
                }
            });
        }
        return vo;
    }
}
