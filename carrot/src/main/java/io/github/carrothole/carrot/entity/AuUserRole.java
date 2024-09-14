package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 *  用户角色绑定 实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户角色绑定")
@Table("au_user_role")
public class AuUserRole extends BaseUserTimeTenant {

    public AuUserRole(String userId, String roleId,  String projectId) {
        this.roleId = roleId;
        this.userId = userId;
        this.projectId = projectId;
    }

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
    private String roleId;

    /**
     * 用户主键
     */
    @Schema(description = "用户主键")
    private String userId;


    /**
     * 项目主键
     */
    @Schema(description = "项目主键")
    private String projectId;

}
