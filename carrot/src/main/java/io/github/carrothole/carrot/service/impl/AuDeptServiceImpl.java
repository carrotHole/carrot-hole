package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.exception.ParamException;
import io.github.carrothole.carrot.exception.UnSupportOperationException;
import io.github.carrothole.carrot.util.SecurityUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuDept;
import io.github.carrothole.carrot.mapper.AuDeptMapper;
import io.github.carrothole.carrot.service.AuDeptService;
import org.springframework.stereotype.Service;
import io.github.carrothole.carrot.entity.qo.AuDeptQueryVO;
import io.github.carrothole.carrot.entity.ro.AuDeptResultVO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.mybatisflex.core.tenant.TenantManager.ignoreTenantCondition;
import static com.mybatisflex.core.tenant.TenantManager.restoreTenantCondition;
import static io.github.carrothole.carrot.config.CarrotConstant.DEPT_ANCESTRY_ID;
import static io.github.carrothole.carrot.entity.table.AuDeptTableDef.AU_DEPT;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuDeptServiceImpl extends ServiceImpl<AuDeptMapper, AuDept> implements AuDeptService {

    @Override
    public boolean save(AuDept entity) {
        // 设置父主键部门集合
        entity.setParentIdList(buildParentIds(entity));
        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id){
        if (this.exists(QueryWrapper.create().and(AU_DEPT.PARENT_ID.eq(id)))){
            throw new UnSupportOperationException("存在下级部门，不允许删除");
        }
        return super.removeById(id);
    }

    @Override
    public boolean updateById(AuDept entity) {
        entity.setCreatedBy(SecurityUtil.getPayLoad().getUsername());
        entity.setCreatedTime(new Date());
        // 设置父主键部门集合
        entity.setParentIdList(buildParentIds(entity));
        return super.updateById(entity);
    }

    /**
     * 构建父主键集合
     * @param entity 部门实体
     * @return 父主键集合
     */
    public List<String> buildParentIds(AuDept entity){
        if (StrUtil.isBlank(entity.getParentId())){
            throw new ParamException("上级部门主键不能为空");
        }
        if (DEPT_ANCESTRY_ID.equals(entity.getParentId())){
            return List.of(DEPT_ANCESTRY_ID);
        }
        List<String> parentIds = this.queryParentIds(entity.getParentId());
        parentIds.add(entity.getParentId());
        return parentIds;
    }

    /**
     * 通过部门主键获取当前部门的父主键集合
     * @param id 部门主键
     * @return 父部门主键集合
     */
    public List<String> queryParentIds(String id){
        String parentIdsStr = this.getOneAs(QueryWrapper.create().select(AU_DEPT.PARENT_IDS).and(AU_DEPT.PARENT_ID.eq(id)), String.class);
        if (StrUtil.isBlank(parentIdsStr)){
            throw new ParamException("部门不存在或上级部门主键不存在");
        }
        return JSONUtil.toList(parentIdsStr, String.class);
    }

    @Override
    public Page<AuDeptResultVO> page(PageVO vo, AuDeptQueryVO queryVO) {
        // todo 字段映射
        return super.pageAs(
                vo.buildPage(),
                vo.appendOrderBy(
                        QueryWrapper.create()
                                .select(AU_DEPT.ALL_COLUMNS)
                                .from(AU_DEPT)
                                .and(AU_DEPT.PARENT_ID.eq(queryVO.getParentId()))
                                .and(AU_DEPT.DEPT_TYPE.eq(queryVO.getDeptType()))
                                .and(AU_DEPT.STATUS.eq(queryVO.getStatus()))
                                .and(AU_DEPT.DEPT_NAME.like(queryVO.getDeptName(), StrUtil.isNotBlank(queryVO.getDeptName()))),
                        AU_DEPT.SORT.asc(),AU_DEPT.CREATED_TIME.desc()
                ),
                AuDeptResultVO.class
        );
    }

    @Override
    public List<AuDept> listByIds(Collection<? extends Serializable> ids, String tenantId){
        try {
            ignoreTenantCondition();
            return this.list(QueryWrapper.create().and(AU_DEPT.ID.in(ids)).and(AU_DEPT.TENANT_ID.eq(tenantId)));
        }finally {
            restoreTenantCondition();
        }
    }

}
