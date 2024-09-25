package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.carrothole.carrot.entity.Dict;
import io.github.carrothole.carrot.entity.DictContent;
import io.github.carrothole.carrot.service.DictContentService;
import io.github.carrothole.carrot.service.DictService;
import io.github.carrothole.carrot.util.CheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.DictTableDef.DICT;

/**
 *  字典接口。
 *
 * @author Administrator
 * @since 2024-09-21
 */
@RestController
@Tag(name = "字典接口")
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @Autowired
    private DictContentService dictContentService;

    /**
     * 保存字典类型。
     *
     * @param dict
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存字典类型")
    public boolean save(@RequestBody @Parameter(description="保存")Dict dict) {
        if (dictService.exists(QueryWrapper.create().and(DICT.TYPE.eq(dict.getType())))){
            throw new UnsupportedOperationException("字典类型已存在");
        }
        return dictService.save(dict);
    }

    /**
     * 根据主键删除字典类型。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    @Operation(description="根据主键删除字典类型")
    @Transactional
    public boolean remove(@PathVariable @Parameter(description="主键")String id) {
        final Dict dict = CheckUtil.checkNotNull(dictService.getById(id), "未找到当前字典");
        dictContentService.removeByType(dict.getType());
        return dictService.removeById(id);
    }

    /**
     * 根据主键更新字典类型。
     *
     * @param dict  {@link Dict}
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新字典类型")
    public boolean update(@RequestBody @Parameter(description="主键")Dict dict) {
        if (dictService.exists(QueryWrapper.create().and(DICT.TYPE.eq(dict.getType())).and(DICT.ID.ne(dict.getId())))){
            throw new UnsupportedOperationException("字典类型已存在");
        }
        return dictService.updateById(dict);
    }

    /**
     * 查询所有字典类型。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有字典类型")
    public List<Dict> list(String queryStr) {
        final QueryWrapper queryWrapper = QueryWrapper.create().orderBy(DICT.TYPE.desc()).orderBy(DICT.ID.desc());
        if (StrUtil.isNotBlank(queryStr)){
            queryWrapper
                    .or(DICT.TYPE.like(queryStr))
                    .or(DICT.NAME.like(queryStr))
                    .or(DICT.REMARK.like(queryStr));
        }
        return dictService.list(queryWrapper);
    }

    /**
     * 根据字典主键查询字典内容
     * @param id 字典主键
     * @return {@link List<DictContent>}
     */
    @GetMapping("listContentById/{id}")
    @Operation(description="查询所有字典值(主键)")
    public List<DictContent> pageContentByMark(@PathVariable @Schema(description = "字典类型主键") String id){
        final Dict dict = CheckUtil.checkNotNull(dictService.getById(id), "未找到当前字典");
        return dictContentService.listByType(dict.getType());
    }


    /**
     * 根据字典主键查询字典内容
     * @param type 字典类型
     * @return {@link List<DictContent>}
     */
    @GetMapping("listContentByType/{type}")
    @Operation(description="查询所有字典值")
    public List<DictContent> pageContentByType(@PathVariable @Schema(description = "字典类型类型") String type){
        CheckUtil.checkNotNull(dictService.getOne(QueryWrapper.create().and(DICT.TYPE.eq(type))),"未找到当前字典");
        return dictContentService.listByType(type);
    }

    /**
     * 新增字典内容
     * @param dictContent {@link DictContent}
     * @return boolean
     */
    @PostMapping("saveContent")
    @Operation(description="新增字典内容")
    public boolean saveContent(@RequestBody DictContent dictContent){
        return dictContentService.save(dictContent);
    }

    /**
     * 更新字典内容
     * @param dictContent {@link DictContent}
     * @return boolean
     */
    @PutMapping("updateContent")
    @Operation(description="更新字典内容")
    public boolean updateContent(@RequestBody DictContent dictContent){
        return dictContentService.updateById(dictContent);
    }

    /**
     * 删除字典内容
     * @param id 字典内容主键
     * @return boolean
     */
    @DeleteMapping("removeContent/{id}")
    @Operation(description="删除字典内容")
    public boolean removeContent(@PathVariable @Schema(description = "字典内容主键") String id){
        return dictContentService.removeById(id);
    }

}
