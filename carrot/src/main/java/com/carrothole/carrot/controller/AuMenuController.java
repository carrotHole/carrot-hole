package com.carrothole.carrot.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.carrothole.carrot.entity.AuMenu;
import com.carrothole.carrot.service.AuMenuService;
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
@Tag(name = "接口")
@RequestMapping("/auMenu")
public class AuMenuController {

    @Autowired
    private AuMenuService auMenuService;

    /**
     * 添加。
     *
     * @param auMenu 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    public boolean save(@RequestBody @Parameter(description="")AuMenu auMenu) {
        return auMenuService.save(auMenu);
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
        return auMenuService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param auMenu 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新")
    public boolean update(@RequestBody @Parameter(description="主键")AuMenu auMenu) {
        return auMenuService.updateById(auMenu);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有")
    public List<AuMenu> list() {
        return auMenuService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
    public AuMenu getInfo(@PathVariable String id) {
        return auMenuService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description="分页查询")
    public Page<AuMenu> page(@Parameter(description="分页信息")Page<AuMenu> page) {
        return auMenuService.page(page);
    }

}