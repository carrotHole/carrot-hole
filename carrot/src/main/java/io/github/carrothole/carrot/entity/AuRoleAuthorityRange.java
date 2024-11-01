package io.github.carrothole.carrot.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import io.github.carrothole.carrot.config.mf.MfConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author Administrator
 * @since 2024-10-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色授权数据范围")
@Table("au_role_authority_range")
public class AuRoleAuthorityRange extends BaseTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    private String id;

    /**
     * 角色授权主键
     */
    @Schema(description = "角色授权主键")
    private String roleAuthorityId;

    /**
     * 组织机构主键
     */
    @Schema(description = "组织机构主键")
    private String deptId;


}
