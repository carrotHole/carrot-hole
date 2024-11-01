package io.github.carrothole.carrot.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;

import io.github.carrothole.carrot.config.ValidateGroup;
import io.github.carrothole.carrot.config.mf.MfConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author Administrator
 * @since 2024-10-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色授权")
@Table("au_role_authority")
public class AuRoleAuthority extends BaseTenant {
    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    private String id;

    /**
     * 角色主键
     */
    @Schema(description = "角色主键")
    @NotBlank(message = "角色主键不能为空", groups = {ValidateGroup.Save.class, ValidateGroup.Update.class})
    private String roleId;
    /**
     * 菜单授权类型;1-自定义,2-全部
     */
    @Schema(description = "菜单授权类型;1-自定义,2-全部")
    private Integer auMenu;

    /**
     * 权限范围授权类型;<br>
     * {@link io.github.carrothole.carrot.enums.AuRangeEnum}
     */
    @Schema(description = "权限范围授权类型;")
    private Integer auRange;


    public static enum AuMenu{
        CUSTOM(1),
        ALL(2),
        ;

        private final Integer code;

        AuMenu(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public boolean isMe(Integer code){
            return this.code.equals(code);
        }
    }
}
