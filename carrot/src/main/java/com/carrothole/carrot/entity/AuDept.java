package com.carrothole.carrot.entity;

import com.mybatisflex.annotation.Id;
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
@Table("au_dept")
public class AuDept implements Serializable {

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
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 部门类型
     */
    @Schema(description = "部门类型")
    private Integer deptType;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private Integer status;

    /**
     * 父主键
     */
    @Schema(description = "父主键")
    private String parentId;

    /**
     * 上级部门主键集合
     */
    @Schema(description = "上级部门主键集合")
    private String parentIds;

}
