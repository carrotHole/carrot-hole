package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuRoleMenuRange;
import io.github.carrothole.carrot.entity.vo.RoleMenuRangeVO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-08-29
 */
public interface AuRoleMenuRangeService extends IService<AuRoleMenuRange> {

    boolean rangeReduce(@Valid @NotBlank(message = "主键不能为空") String roleMenuRangeId);

    List<AuRoleMenuRange> rangeExpand(@Valid @NotNull(message = "权限范围扩展对象") RoleMenuRangeVO vo);
}
