package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description: 操作用户角色vo
 * date: 2024-9-8 9:45
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@Schema(description = "操作用户角色对象")
public class UserRoleVO implements Serializable {
    /**
     * 用户id
     */
    @Schema(description = "用户id")
    @NotBlank(message = "用户主键不能为空")
    private String userId;

    /**
     * 角色id列表
     */
    @NotNull(message = "角色主键列表不能为空")
    @Schema(description = "角色id列表")
    private List<String> roleIds;

}
