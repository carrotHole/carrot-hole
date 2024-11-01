package io.github.carrothole.carrot.controller;

import com.mybatisflex.core.paginate.Page;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.config.ValidateGroup;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.AuRoleAuthorityResultVO;
import io.github.carrothole.carrot.entity.vo.AuRoleAuthorityVO;
import io.github.carrothole.carrot.entity.vo.AuRoleMenuAuthorityVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.service.AuRoleMenuAuthorityRangeService;
import io.github.carrothole.carrot.service.AuRoleMenuAuthorityService;
import io.github.carrothole.carrot.util.SecurityUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.carrothole.carrot.entity.AuRole;
import io.github.carrothole.carrot.service.AuRoleService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

/**
 *  控制层。
 *
 * @author Administrator
 * @since 0.0.1
 */
@RestController
@Tag(name = "角色")
@RequestMapping("/auRole")
public class AuRoleController {

    @Autowired
    private AuRoleService auRoleService;

    @Autowired
    private AuRoleMenuAuthorityService auRoleMenuService;

    @Autowired
    private AuRoleMenuAuthorityRangeService auRoleMenuRangeService;
    /**
     * 添加。
     *
     * @param auRole 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    @PreAuthorize(menu = {"au:role:save"}, user = {"carrot"})
    public boolean save(@RequestBody @Parameter(description="角色对象") @Valid @Validated(value = {ValidateGroup.Save.class}) AuRole auRole) {
        auRole.setDeptId(SecurityUtil.getUser().getDeptId());
        return auRoleService.save(auRole);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键")
    @PreAuthorize(menu = {"au:role:remove"}, user = {"carrot"})
    public boolean remove(@PathVariable @Parameter(description="主键") @Valid @NotBlank(message = "主键不能为空") String id) {
        return auRoleService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param auRole 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新")
    @PreAuthorize(menu = {"au:role:update"}, user = {"carrot"})
    public boolean update(@RequestBody @Parameter(description="主键") @Valid @Validated(value = {ValidateGroup.Update.class}) AuRole auRole) {
        return auRoleService.updateById(auRole);
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
    @PreAuthorize(menu = {"au:role:getInfo"}, user = {"carrot"})
    public AuRole getInfo(@PathVariable @Valid @NotBlank(message = "主键不能为空") String id) {
        return auRoleService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description="分页查询")
    @PreAuthorize(menu = {"au:role:page"}, user = {"carrot"})
    public Page<AuRoleResultVO> page(@Parameter(description="分页信息") PageVO page, AuRoleQueryVO vo) {
        return auRoleService.page(page,vo);
    }

    // ------------权限相关----------------

    /**
     * 获取授权信息
     */
    @GetMapping("getAuthority/{id}")
    @Operation(description="获取授权信息")
    @PreAuthorize(menu = {"au:role:getAuthority"}, user = {"carrot"})
    public AuRoleAuthorityResultVO getAuthority(@PathVariable @Valid @NotBlank(message = "主键不能为空") String id) {
        return auRoleService.getAuthority(id);
    }

    /**
     * 角色新增授权。
     *
     * @param vo {@link AuRoleAuthorityVO}
     * @return boolean
     */
    @PostMapping("saveAuthority")
    @Operation(description="角色新增授权")
    @PreAuthorize(menu = {"au:role:saveAuthority"}, user = {"carrot"})
    public boolean saveAuthority(@RequestBody AuRoleAuthorityVO vo) {
        return auRoleService.saveAuthority(vo);
    }

    /**
     * 角色更新授权。
     *
     * @param vo {@link AuRoleAuthorityVO}
     * @return boolean
     */
    @PutMapping("updateAuthority")
    @Operation(description="角色更新授权")
    @PreAuthorize(menu = {"au:role:updateAuthority"}, user = {"carrot"})
    public boolean updateAuthority(@RequestBody AuRoleAuthorityVO vo) {
        return auRoleService.updateAuthority(vo);
    }

    /**
     * 角色菜单新增授权。
     *
     * @param vo {@link AuRoleAuthorityVO}
     * @return boolean
     */
    @PostMapping("saveMenuAuthority")
    @Operation(description="角色菜单新增授权")
    @PreAuthorize(menu = {"au:role:saveMenuAuthority"}, user = {"carrot"})
    public boolean saveMenuAuthority(@RequestBody AuRoleMenuAuthorityVO vo) {
        return auRoleService.saveMenuAuthority(vo);
    }

    /**
     * 角色菜单更新授权。
     *
     * @param vo {@link AuRoleAuthorityVO}
     * @return boolean
     */
    @PutMapping("updateMenuAuthority")
    @Operation(description="角色菜单更新授权")
    @PreAuthorize(menu = {"au:role:updateMenuAuthority"}, user = {"carrot"})
    public boolean updateMenuAuthority(@RequestBody AuRoleMenuAuthorityVO vo) {
        return auRoleService.updateMenuAuthority(vo);
    }

    /**
     * 角色菜单删除授权
     * @param id 主键
     * @return boolean
     */
    @DeleteMapping("removeMenuAuthority/{id}")
    @Operation(description="")
    @PreAuthorize(menu = {"au:role:removeMenuAuthority"}, user = {"carrot"})
    public boolean removeMenuAuthority(@PathVariable @Valid @NotBlank(message = "主键不能为空") String id) {
        return auRoleService.removeMenuAuthority(id);
    }
}
