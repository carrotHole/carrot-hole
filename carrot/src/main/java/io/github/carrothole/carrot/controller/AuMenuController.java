package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.ObjectUtil;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;
import io.github.carrothole.carrot.exception.UnSupportOperationException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.carrothole.carrot.entity.AuMenu;
import io.github.carrothole.carrot.service.AuMenuService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuMenuTableDef.AU_MENU;


/**
 *  控制层。
 *
 * @author Administrator
 * @since 0.0.1
 */
@RestController
@Tag(name = "菜单")
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
    @PreAuthorize(menu = {"au:menu:save"}, user = {"carrot","superman"})
    public boolean save(@RequestBody @Parameter(description="保存") @Valid AuMenu auMenu) {
        // 校验统一应用下是否有同路径和权限的菜单
        if (ObjectUtil.equals(auMenu.getMenuType(),"1") && auMenuService.exists(QueryWrapper.create().and(AU_MENU.MENU_URL.eq(auMenu.getMenuUrl())))) {
            throw new UnSupportOperationException("已存在相同路的菜单");
        }
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
    @PreAuthorize(menu = {"au:menu:remove"}, user = {"carrot","superman"})
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
    @PreAuthorize(menu = {"au:menu:update"}, user = {"carrot","superman"})
    public boolean update(@RequestBody @Parameter(description="主键")AuMenu auMenu) {
        return auMenuService.updateById(auMenu);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list/{projectId}")
    @Operation(description="查询所有")
    @PreAuthorize(menu = {"au:menu:list"}, user = {"carrot","superman"})
    public List<AuMenuResultVO> list(@PathVariable String projectId) {
        return auMenuService.listByProjectId(projectId);
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
     * 获取菜单树(所有菜单)
     * @param projectId 项目主键
     * @return
     */
    @GetMapping("getTree/{projectId}")
    @Operation(description="根据项目主键获取树形菜单")
    @PreAuthorize(menu = {"au:menu:getTree"}, user = {"carrot","superman"})
    public List<AuMenuResultVO> getTree(@PathVariable String projectId){
       return auMenuService.getTree(projectId);
    }


}
