package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.config.validate.ValidateGroup;
import io.github.carrothole.carrot.entity.vo.ChangePasswordVO;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.exception.UnSupportOperationException;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;
import io.github.carrothole.carrot.entity.qo.AuUserQueryVO;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.util.BoolUtil;
import io.github.carrothole.carrot.util.CheckUtil;
import jakarta.validation.Valid;
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


import static io.github.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

/**
 *  控制层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@RestController
@Tag(name = "用户")
@RequestMapping("/auUser")
public class AuUserController {

    @Autowired
    private AuUserService auUserService;

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
        // 如果删除的是真实用户，则校验是否有虚拟用户,如果存在虚拟用户则不能删除
        if (BoolUtil.isTrue(auUser.getRealUser())){
            if (auUserService.exists(QueryWrapper.create().and(AU_USER.USERNAME.eq(auUser.getRealUser())).and(AU_USER.REAL_USER.eq(BoolUtil.trueInt)))){
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
        // 校验当前部门下是否用户名重复
        checkDeptUser(auUser);
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
     * 修改密码。
     * @param auUser 修改密 码对象
     * @return 结果
     */
    @PostMapping("updatePassword")
    @Operation(description="修改密码")
    @PreAuthorize(menu = {"au:user:updatePassword"}, user = "carrot")
    public boolean updatePassword(@RequestBody ChangePasswordVO vo) {
        // todo 其他方式校验
        if (StrUtil.isBlank(vo.getPassword()) && StrUtil.isBlank(vo.getPasswordEnc())){
            throw new UnSupportOperationException("密码不能为空");
        }
        return auUserService.updatePassword(vo);
    }



    private void checkDeptUser(AuUser auUser) {
        if (auUserService.exists(QueryWrapper.create().and(AU_USER.USERNAME.eq(auUser.getUsername())).and(AU_USER.DEPT_ID.eq(auUser.getDeptId())))) {
            throw new UnSupportOperationException("当前部门下用户名重复");
        }
    }
}
