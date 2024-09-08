package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:权限范围扩展对象
 * date: 2024-9-8 19:32
 *
 * @author moon
 * @since 0.0.1
 */

@Data
@Schema(description = "权限范围扩展对象")
public class RoleMenuRangeVO implements Serializable {

    @Schema(description = "角色菜单主键")
    @NotBlank(message = "角色菜单主键不能为空")
    private String roleMenuId;

    @Schema(description = "部门主键")
    @NotNull(message = "部门主键不能为空")
    private List<String> deptIds;

}
