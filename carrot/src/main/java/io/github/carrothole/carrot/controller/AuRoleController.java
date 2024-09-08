package io.github.carrothole.carrot.controller;

import com.mybatisflex.core.paginate.Page;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.config.validate.ValidateGroup;
import io.github.carrothole.carrot.entity.AuRoleMenu;
import io.github.carrothole.carrot.entity.AuRoleMenuRange;
import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import io.github.carrothole.carrot.service.AuRoleMenuRangeService;
import io.github.carrothole.carrot.service.AuRoleMenuService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
import java.util.List;

/**
 *  控制层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@RestController
@Tag(name = "角色")
@RequestMapping("/auRole")
public class AuRoleController {

    @Autowired
    private AuRoleService auRoleService;

    @Autowired
    private AuRoleMenuService auRoleMenuService;

    @Autowired
    private AuRoleMenuRangeService auRoleMenuRangeService;
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


    /**
     * 绑定菜单。
     * @param roleMenu 角色菜单对象
     * @return boolean
     */
    @PostMapping("bindMenu")
    @Operation(description="绑定菜单")
    @PreAuthorize(menu = {"au:role:bindMenu"}, user = {"carrot"})
    public AuRoleMenu bindMenu(@RequestBody @Schema(description="角色菜单对象") @Valid AuRoleMenu roleMenu) {
        return auRoleMenuService.bindMenu(roleMenu);
    }

    /**
     * 解绑菜单。
     * @param roleMenuId 角色菜单主键
     * @return boolean
     */
    @DeleteMapping("unbindMenu/{roleMenuId}")
    @Operation(description="解绑菜单")
    @PreAuthorize(menu = {"au:role:unbindMenu"}, user = {"carrot"})
    public boolean unbindMenu(@PathVariable @Schema(description="角色菜单主键") @Valid String roleMenuId) {
        return auRoleMenuService.unbindMenu(roleMenuId);
    }

    /**
     * 缩小权限范围。
     * @param roleMenuRangeId 权限范围主键
     * @return boolean
     */
    @DeleteMapping("rangeReduce/{roleMenuRangeId}}")
    @Operation(description="缩小权限范围")
    @PreAuthorize(menu = {"au:role:rangeReduce"}, user = {"carrot"})
    public boolean rangeReduce(@PathVariable @Schema(description="权限范围主键") @Valid @NotBlank(message = "主键不能为空") String roleMenuRangeId) {
        return auRoleMenuRangeService.rangeReduce(roleMenuRangeId);
    }

    /**
     * 扩展权限部门。
     * @param vo 扩展对象
     * @return boolean
     */
    @PostMapping("rangeExpand")
    @Operation(description="扩展权限范围")
    @PreAuthorize(menu = {"au:role:rangeExpand"}, user = {"carrot"})
    public List<AuRoleMenuRange> rangeExpand(@RequestBody @Schema(description="权限范围扩展对象") @Valid @NotNull(message = "权限范围扩展对象") RoleMenuRangeVO vo) {
        return auRoleMenuRangeService.rangeExpand(vo);
    }
}
