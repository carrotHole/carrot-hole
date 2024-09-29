package io.github.carrothole.carrot.controller;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.config.ValidateGroup;
import io.github.carrothole.carrot.exception.ParamException;
import io.github.carrothole.carrot.util.SecurityUtil;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.carrothole.carrot.entity.AuTenant;
import io.github.carrothole.carrot.service.AuTenantService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.Date;
import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuTenantTableDef.AU_TENANT;

/**
 *  控制层。
 *
 * @author Administrator
 * @since 0.0.1
 */
@RestController
@Tag(name = "租户")
@RequestMapping("/auTenant")
public class AuTenantController {

    @Autowired
    private AuTenantService auTenantService;

    /**
     * 添加。
     *
     * @param auTenant {@link AuTenant}
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    @PreAuthorize(menu = {"au:tenant:save"}, user = "carrot")
    public boolean save(@RequestBody @Parameter(description="租户对象") @Validated(value = {ValidateGroup.Save.class}) AuTenant auTenant) {
        auTenant.setCreatedBy(SecurityUtil.getPayLoad().getUsername());
        auTenant.setCreatedTime(new Date());
        final boolean exists = auTenantService.exists(QueryWrapper.create().and(AU_TENANT.TENANT_MARK.eq(auTenant.getTenantMark())));
        if (exists){
            throw new ParamException("租户标识重复");
        }
        return auTenantService.save(auTenant);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键")
    @PreAuthorize(menu = {"au:tenant:remove"}, user = "carrot")
    public boolean remove(@PathVariable @Parameter(description="主键")String id) {
        // todo 删除租户其他信息
        return auTenantService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param auTenant {@link AuTenant}
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新")
    @PreAuthorize(menu = {"au:tenant:update"}, user = "carrot")
    public boolean update(@RequestBody @Parameter(description="主键") @Validated(value = {ValidateGroup.Update.class}) AuTenant auTenant) {
        final AuTenant  tenant = auTenantService.getById(auTenant.getId());
        if (!tenant.getTenantMark().equals(auTenant.getTenantMark())){
            throw new ParamException("租户标识不能改变");
        }
        return auTenantService.updateById(auTenant);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有")
    @PreAuthorize(menu = {"au:tenant:list"}, user = "carrot")
    public List<AuTenant> list(AuTenant auTenant) {
        return auTenantService.list(
                QueryWrapper.create()
                        .and(AU_TENANT.TENANT_MARK.like(auTenant.getTenantMark(), StrUtil.isNotBlank(auTenant.getTenantMark())))
                        .and(AU_TENANT.TENANT_NAME.like(auTenant.getTenantName(), StrUtil.isNotBlank(auTenant.getTenantName())))
        );
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
    @PreAuthorize(menu = {"au:tenant:getInfo"}, user = "carrot")
    public AuTenant getInfo(@PathVariable String id) {
        return auTenantService.getById(id);
    }


}
