package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限范围实体类。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "权限范围")
@Table("au_role_menu_permission")
public class AuRoleMenuRange extends BaseTenant {

    public AuRoleMenuRange(String roleMendId, String deptId) {
        this.roleMendId = roleMendId;
        this.deptId = deptId;
    }

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    private String id;

    /**
     * 角色菜单主键
     */
    @Schema(description = "角色菜单主键")
    private String roleMendId;

    /**
     * 部门主键
     */
    @Schema(description = "部门主键")
    private String deptId;

}
