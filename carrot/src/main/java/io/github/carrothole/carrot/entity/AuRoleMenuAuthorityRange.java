package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 权限范围实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色菜单数据范围")
@Table("au_role_menu_authority_range")
public class AuRoleMenuAuthorityRange extends BaseTenant {

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
