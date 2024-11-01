package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import io.github.carrothole.carrot.config.ValidateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 *  角色菜单实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色菜单")
@Table("au_role_menu_authority")
public class AuRoleMenuAuthority extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    private String id;

    /**
     * 角色主键
     */
    @Schema(description = "角色主键")
    @NotBlank(message = "角色主键不能为空", groups = {ValidateGroup.Save.class, ValidateGroup.Update.class})
    private String roleId;

    /**
     * 角色主键
     */
    @Schema(description = "菜单主键")
    @NotBlank(message = "菜单主键不能为空", groups = {ValidateGroup.Save.class, ValidateGroup.Update.class})
    private String menuId;

    /**
     * 权限范围
     */
    @Schema(description = "权限范围")
    @NotNull(message = "权限范围不能为空", groups = {ValidateGroup.Save.class, ValidateGroup.Update.class})
    private Integer auRange;

    /**
     * 使用默认配置
     */
    @Schema(description = "使用默认配置")
    @NotNull(message = "使用默认配置", groups = {ValidateGroup.Save.class, ValidateGroup.Update.class})
    private Integer useDefault;

}
