package io.github.carrothole.carrot.controller;

import com.mybatisflex.core.paginate.Page;
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

    /**
     * 添加。
     *
     * @param auRole 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    public boolean save(@RequestBody @Parameter(description="")AuRole auRole) {
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
    public boolean remove(@PathVariable @Parameter(description="主键")String id) {
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
    public boolean update(@RequestBody @Parameter(description="主键")AuRole auRole) {
        return auRoleService.updateById(auRole);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有")
    public List<AuRole> list() {
        return auRoleService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
    public AuRole getInfo(@PathVariable String id) {
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
    public Page<AuRole> page(@Parameter(description="分页信息")Page<AuRole> page) {
        return auRoleService.page(page);
    }

}
