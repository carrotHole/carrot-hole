package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.authorization.PreAuthorize;
import io.github.carrothole.carrot.entity.vo.PageVO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.carrothole.carrot.entity.AuProject;
import io.github.carrothole.carrot.service.AuProjectService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.github.carrothole.carrot.entity.qo.AuProjectQueryVO;
import io.github.carrothole.carrot.entity.ro.AuProjectResultVO;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuProjectTableDef.AU_PROJECT;

/**
 * 控制层。
 *
 * @author Administrator
 * @since 0.0.1
 */
@RestController
@Tag(name = "应用")
@RequestMapping("/auProject")
public class AuProjectController {

    @Autowired
    private AuProjectService auProjectService;

    /**
     * 添加。
     *
     * @param auProject
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description = "保存")
    @PreAuthorize(menu = {"au:project:save"}, user = {"carrot","superman"})
    public boolean save(@RequestBody @Parameter(description = "") AuProject auProject) {
        return auProjectService.save(auProject);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description = "根据主键")
    @PreAuthorize(menu = {"au:project:remove"}, user = {"carrot","superman"})
    public boolean remove(@PathVariable @Parameter(description = "主键") String id) {
        return auProjectService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param auProject
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description = "根据主键更新")
    @PreAuthorize(menu = {"au:project:update"}, user = {"carrot","superman"})
    public boolean update(@RequestBody @Parameter(description = "主键") AuProject auProject) {
        return auProjectService.updateById(auProject);
    }


    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description = "根据主键获取")
    @PreAuthorize(menu = {"au:project:getInfo"}, user = {"carrot","superman"})
    public AuProject getInfo(@PathVariable String id) {
        return auProjectService.getById(id);
    }

    /**
     * 获取全部应用列表
     */
    @GetMapping("list")
    @Operation(description = "查询所有")
    @PreAuthorize(menu = {"au:project:list"}, user = {"carrot","superman"})
    public List<AuProjectResultVO> list() {
        return auProjectService.listAs(QueryWrapper.create(), AuProjectResultVO.class);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    @Operation(description = "分页查询")
    @PreAuthorize(menu = {"au:project:page"}, user = {"carrot","superman"})
    public Page<AuProjectResultVO> page(@Parameter(description = "分页信息") PageVO page, AuProjectQueryVO vo) {
        return auProjectService.pageAs(
                page.buildPage(),
                page.appendOrderBy(
                        QueryWrapper.create()
                                .and(AU_PROJECT.STATUS.eq(vo.getStatus(), vo.getStatus() != null))
                                .and(AU_PROJECT.CREATED_TIME.le(vo.getCreatedTimeEnd(), vo.getCreatedTimeEnd() != null))
                                .and(AU_PROJECT.CREATED_TIME.ge(vo.getCreatedTimeBegin(), vo.getCreatedTimeBegin() != null))
                                .and(AU_PROJECT.PROJECT_NAME.like(vo.getProjectName(), StrUtil.isNotBlank(vo.getProjectName())))
                        , AU_PROJECT.SORT.desc(), AU_PROJECT.CREATED_TIME.desc()
                )
                , AuProjectResultVO.class
        );
    }

}
