package com.carrothole.carrot.entity;

import com.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@Table("au_dept_user")
public class AuDeptUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    private String id;

    /**
     * 租户主键
     */
    @Schema(description = "租户主键")
    private String tenantId;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createdTime;

    /**
     * 部门主键
     */
    @Schema(description = "部门主键")
    private String deptId;

    /**
     * 用户主键
     */
    @Schema(description = "用户主键")
    private String userId;

}
