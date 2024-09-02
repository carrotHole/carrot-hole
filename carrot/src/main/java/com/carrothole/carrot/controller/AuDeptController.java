package com.carrothole.carrot.controller;

import com.carrothole.carrot.authorization.PreAuthorize;
import com.carrothole.carrot.entity.vo.PageVO;
import com.carrothole.carrot.exception.ParamException;
import com.carrothole.carrot.exception.UnSupportOperationException;
import com.carrothole.carrot.property.CarrotProperty;
import com.carrothole.carrot.service.AuDeptUserService;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.carrothole.carrot.entity.AuDept;
import com.carrothole.carrot.service.AuDeptService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

import static com.carrothole.carrot.entity.table.AuDeptTableDef.AU_DEPT;
import static com.carrothole.carrot.entity.table.AuDeptUserTableDef.AU_DEPT_USER;
import static com.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

/**
 *  控制层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@RestController
@Tag(name = "部门")
@RequestMapping("/auDept")
public class AuDeptController {

    @Autowired
    private AuDeptService auDeptService;

    @Autowired
    private AuDeptUserService auDeptUserService;

    @Autowired
    private CarrotProperty carrotProperty;
    /**
     * 添加。
     *
     * @param auDept 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    @PreAuthorize(menu = {"au:dept:save"}, user = "carrot")
    public boolean save(@RequestBody @Parameter(description="部门")AuDept auDept) {
        // 校验同级下是否有同名部门
        if (auDeptService.exists(QueryWrapper.create().and(AU_DEPT.DEPT_NAME.eq(auDept.getDeptName())))) {
            throw new ParamException("同级下已存在同名部门");
        }
        return auDeptService.save(auDept);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键")
    @PreAuthorize(menu = {"au:dept:remove"}, user = "carrot")
    public boolean remove(@PathVariable @Parameter(description="主键")String id) {
        // 校验部门下是否有用户
        if (auDeptUserService.exists(QueryWrapper.create().and(AU_DEPT_USER.USER_ID.eq(id)))){
            throw new UnSupportOperationException("删除失败：部门下存在用户");
        }
        return auDeptService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param auDept 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新")
    @PreAuthorize(menu = {"au:dept:update"}, user = "carrot")
    public boolean update(@RequestBody @Parameter(description="主键")AuDept auDept) {
        // 校验同级下是否有同名部门
        if (auDeptService.exists(QueryWrapper.create().and(AU_DEPT.DEPT_NAME.eq(auDept.getDeptName())).and(AU_DEPT.ID.ne(auDept.getId())))) {
            throw new UnSupportOperationException("创建失败：同级下已存在同名部门");
        }
        return auDeptService.updateById(auDept);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有")
    @PreAuthorize(menu = {"au:dept:list"}, user = "carrot")
    public List<AuDept> list() {
        return auDeptService.list(QueryWrapper.create().limit(carrotProperty.web.listSize));
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
    @PreAuthorize(menu = {"au:dept:getInfo"}, user = "carrot")
    public AuDept getInfo(@PathVariable String id) {
        return auDeptService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param vo 分页信息
     * @param auDept 条件查询对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description="分页查询")
    @PreAuthorize(menu = {"au:dept:page"}, user = "carrot")
    public Page<AuDept> page(@Parameter(description="分页信息") PageVO vo,@Parameter(description="条件查询对象") AuDept auDept) {
        return auDeptService.page(vo,auDept);
    }

}
