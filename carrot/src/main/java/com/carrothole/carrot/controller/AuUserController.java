package com.carrothole.carrot.controller;

import com.carrothole.carrot.entity.vo.AuUserOperationVO;
import com.carrothole.carrot.exception.UnSupportOperationException;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.carrothole.carrot.entity.AuUser;
import com.carrothole.carrot.service.AuUserService;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

import static com.carrothole.carrot.entity.table.AuUserTableDef.AU_USER;

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
     * @param operationVO
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @PostMapping("save")
    @Operation(description="保存")
    public boolean save(@RequestBody @Parameter(description="新增对象")@Valid AuUserOperationVO operationVO) {
        // 校验用户是否存在
        if (auUserService.exists(QueryWrapper.create().and(AU_USER.USERNAME.eq(operationVO.getUsername())))) {
            throw new UnSupportOperationException("用户已存在");
        }
        return auUserService.save(operationVO);
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
        return auUserService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param operationVO
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    @Operation(description="根据主键更新")
    public boolean update(@RequestBody @Parameter(description="更新对象") AuUserOperationVO operationVO) {
        return auUserService.updateById(operationVO);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    @Operation(description="查询所有")
    public List<AuUser> list() {
        return auUserService.list();
    }

    /**
     * 根据主键获取详细信息。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    @Operation(description="根据主键获取")
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
    public Page<AuUser> page(@Parameter(description="分页信息")Page<AuUser> page) {
        return auUserService.page(page);
    }

}
