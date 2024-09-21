package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.config.ValidateGroup;
import io.github.carrothole.carrot.constant.CarrotConst;
import io.github.carrothole.carrot.entity.ro.AuRoleResultVO;
import io.github.carrothole.carrot.entity.vo.ChangePasswordVO;
import io.github.carrothole.carrot.entity.vo.ChangeStatusVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.entity.vo.UserRoleVO;
import io.github.carrothole.carrot.exception.UnSupportOperationException;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;
import io.github.carrothole.carrot.entity.qo.AuUserQueryVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.service.AuUserRoleService;
import io.github.carrothole.carrot.util.BoolUtil;
import io.github.carrothole.carrot.util.CheckUtil;
import io.swagger.v3.oas.annotations.media.Schema;
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
import io.github.carrothole.carrot.entity.AuUser;
import io.github.carrothole.carrot.service.AuUserService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


import io.github.carrothole.carrot.entity.qo.AuRoleQueryVO;
import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

/**
 *  控制层。
 *
 * @author Administrator
 * @since 0.0.1
 */
@RestController
@Tag(name = "用户")
@RequestMapping("/auUser")
public class AuUserController {

    @Autowired
    private AuUserService auUserService;

    @Autowired
    private AuUserRoleService auUserRoleService;
    /**
     * 添加。
     *
     * @param auUser {@link AuUser}
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    @PreAuthorize(menu = {"au:user:save"}, user = "carrot")
    public boolean save(@RequestBody @Parameter(description="新增对象")@Valid @Validated(value = {ValidateGroup.Save.class}) AuUser auUser) {

        if (BoolUtil.isTrue(auUser.getRealUser())){
            // 校验用户是否存在
            if (auUserService.exists(QueryWrapper.create().and(AU_USER.USERNAME.eq(auUser.getUsername())))) {
                throw new UnSupportOperationException("用户已存在");
            }
        }else{
            // 校验当前部门下是否用户名重复
            checkDeptUser(auUser);
        }
        return auUserService.save(auUser);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键")
    @PreAuthorize(menu = {"au:user:remove"}, user = "carrot")
    public boolean remove(@PathVariable @Parameter(description="主键")String id) {
        AuUser auUser = CheckUtil.checkNotNull(auUserService.getById(id), "用户不存在");
        if (CarrotConst.ADMIN_USERNAME.contains(auUser.getUsername())){
            throw new UnSupportOperationException("管理员用户不能删除");
        }
        // 如果删除的是真实用户，则校验是否有虚拟用户,如果存在虚拟用户则不能删除
        if (BoolUtil.isTrue(auUser.getRealUser())){
            if (auUserService.exists(QueryWrapper.create().and(AU_USER.USERNAME.eq(auUser.getRealUser())).and(AU_USER.REAL_USER.eq(BoolUtil.falseInt)))){
                throw new UnSupportOperationException("存在虚拟用户，不能删除真实用户");
            }
        }
        return auUserService.removeById(auUser);
    }

    /**
     * 根据主键更新。
     *
     * @param auUser {@link AuUser}
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新")
    @PreAuthorize(menu = {"au:user:update"}, user = "carrot")
    public boolean update(@RequestBody @Parameter(description="更新对象") @Valid @Validated(value = {ValidateGroup.Update.class}) AuUser auUser) {
        AuUser auUserDb = CheckUtil.checkNotNull(auUserService.getById(auUser.getId()), "用户不存在");
        if (CarrotConst.ADMIN_USERNAME.contains(auUserDb.getUsername())){
            throw new UnSupportOperationException("管理员用户不能删除");
        }
        // 校验当前部门下是否用户名重复
        return auUserService.updateById(auUser);
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
    @PreAuthorize(menu = {"au:user:getInfo"}, user = "carrot")
    public AuUser getInfo(@PathVariable String id) {
        return auUserService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description="分页查询")
    @PreAuthorize(menu = {"au:user:page"}, user = "carrot")
    public Page<AuUserResultVO> page(@Parameter(description="分页信息") PageVO page, AuUserQueryVO queryVO) {
        return auUserService.page(page,queryVO);
    }

    /**
     * 绑定角色
     * @param vo {@link UserRoleVO}
     * @return boolean
     */
    @PutMapping("bindRole")
    @Operation(description="绑定角色")
    @PreAuthorize(menu = {"au:user:bindRole"}, user = "carrot")
    public boolean bindRole(@RequestBody @Valid UserRoleVO vo) {
        return auUserRoleService.bindRole(vo);
    }

    /**
     * 解绑角色
     * @param vo {@link UserRoleVO}
     * @return boolean
     */
    @PutMapping("unbindRole")
    @Operation(description="解绑角色")
    @PreAuthorize(menu = {"au:user:unbindRole"}, user = "carrot")
    public boolean unbindRole(@RequestBody @Valid UserRoleVO vo) {
        return auUserRoleService.unbindRole(vo);
    }

    // 获取用户下角色
    @GetMapping("getRole/{id}")
    @Operation(description="获取用户下角色")
    @PreAuthorize(menu = {"au:user:getRole"}, user = "carrot")
    public List<AuRoleResultVO> getRole(@PathVariable @Schema(description = "用户主键") @Valid @NotBlank(message = "用户主键不能为空")  String id, @Schema(description = "角色查询条件") AuRoleQueryVO vo) {
        return auUserRoleService.listRoleByUserId(id, vo);
    }


    /**
     * 修改密码。<br/>
     * 注意修改密码是根据username进行的,会将真实用户和虚拟用户密码同时修改
     * @param vo {@link ChangePasswordVO}
     * @return 结果
     */
    @PutMapping("updatePassword")
    @Operation(description="修改密码")
    @PreAuthorize(menu = {"au:user:updatePassword"}, user = "carrot")
    public boolean updatePassword(@RequestBody @Valid ChangePasswordVO vo) {
        // todo 校验码 验证旧密码
        if (StrUtil.isBlank(vo.getNewPassword()) && StrUtil.isBlank(vo.getOldPasswordEnc())){
            throw new UnSupportOperationException("密码不能为空");
        }
        return auUserService.updatePassword(vo);
    }

    /**
     * 修改状态。
     * @param vo {@link ChangeStatusVO}
     * @return boolean
     */
    @PutMapping("updateStatus")
    @Operation(description="修改状态")
    @PreAuthorize(menu = {"au:user:updateStatus"}, user = "carrot")
    public boolean updateStatus(@RequestBody @Valid  ChangeStatusVO vo) {
        return auUserService.updateStatus(vo);
    }


    private void checkDeptUser(AuUser auUser) {
        if (auUserService.exists(QueryWrapper.create().and(AU_USER.USERNAME.eq(auUser.getUsername())).and(AU_USER.DEPT_ID.eq(auUser.getDeptId())))) {
            throw new UnSupportOperationException("当前部门下用户名重复");
        }
    }
}
