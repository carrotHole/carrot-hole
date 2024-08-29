package com.carrothole.carrot.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.io.Serial;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author Administrator
 * @since 2024-08-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
@Table("au_user_role")
public class AuUserRole implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Schema(description = "主键")
    private String id;

    /**
     * 租户主键
     */
    @Schema(description = "租户主键")
    private String tenantId;

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
     * 部门主键
     */
    @Schema(description = "部门主键")
    private String deptId;

    /**
     * 项目主键
     */
    @Schema(description = "项目主键")
    private String projectId;

}