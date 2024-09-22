package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.entity.DictContentSys;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.service.DictContentSysService;
import io.github.carrothole.carrot.util.CheckUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.carrothole.carrot.entity.DictSys;
import io.github.carrothole.carrot.service.DictSysService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

import static io.github.carrothole.carrot.entity.table.DictSysTableDef.DICT_SYS;

/**
 *  字典接口。
 *
 * @author Administrator
 * @since 2024-09-21
 */
@RestController
@Tag(name = "字典接口")
@RequestMapping("/dictSys")
public class DictSysController {

    @Autowired
    private DictSysService dictSysService;

    @Autowired
    private DictContentSysService dictContentSysService;

    /**
     * 保存字典类型。
     *
     * @param dictSys 
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存字典类型")
    public boolean save(@RequestBody @Parameter(description="保存")DictSys dictSys) {
        return dictSysService.save(dictSys);
    }

    /**
     * 根据主键删除字典类型。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键删除字典类型")
    public boolean remove(@PathVariable @Parameter(description="主键")String id) {
        return dictSysService.removeById(id);
    }

    /**
     * 根据主键更新字典类型。
     *
     * @param dictSys 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新字典类型")
    public boolean update(@RequestBody @Parameter(description="主键")DictSys dictSys) {
        return dictSysService.updateById(dictSys);
    }

    /**
     * 查询所有字典类型。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有字典类型")
    public List<DictSys> list(String queryStr) {
        final QueryWrapper queryWrapper = QueryWrapper.create().orderBy(DICT_SYS.TYPE.desc());
        if (StrUtil.isNotBlank(queryStr)){
            queryWrapper
                    .or(DICT_SYS.TYPE.like(queryStr))
                    .or(DICT_SYS.NAME.like(queryStr))
                    .or(DICT_SYS.REMARK.like(queryStr));
        }
        return dictSysService.list(queryWrapper);
    }

    /**
     * 根据字典主键分页查询字典内容
     * @param id 字典主键
     * @return {@link Page<DictContentSys>}
     */
    @GetMapping("pageContentById/{id}")
    @Operation(description="查询所有字典值(主键)")
    public Page<DictContentSys> pageContentByMark(PageVO vo, @PathVariable @Schema(description = "字典类型主键") String id){
        final DictSys dictSys = CheckUtil.checkNotNull(dictSysService.getById(id), "未找到当前字典");
        return dictContentSysService.pageByType(vo,dictSys.getType());
    }


    /**
     * 根据字典主键分页查询字典内容
     * @param type 字典类型
     * @return {@link Page<DictContentSys>}
     */
    @GetMapping("pageContentByType/{type}")
    @Operation(description="查询所有字典值")
    public Page<DictContentSys> pageContentByType(PageVO vo, @PathVariable @Schema(description = "字典类型类型") String type){
        CheckUtil.checkNotNull(dictSysService.getOne(QueryWrapper.create().and(DICT_SYS.TYPE.eq(type))),"未找到当前字典");
        return dictContentSysService.pageByType(vo, type);
    }

    /**
     * 新增字典内容
     * @param dictContentSys DictContentSys
     * @return boolean
     */
    @PostMapping("saveContent")
    @Operation(description="新增字典内容")
    public boolean saveContent(@RequestBody DictContentSys dictContentSys){
        return dictContentSysService.save(dictContentSys);
    }

    /**
     * 删除字典内容
     * @param id 字典内容主键
     * @return boolean
     */
    @DeleteMapping("removeContent/{id}")
    @Operation(description="删除字典内容")
    public boolean removeContent(@PathVariable @Schema(description = "字典内容主键") String id){
        return dictContentSysService.removeById(id);
    }

}
