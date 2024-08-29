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
@Table("au_tenant")
public class AuTenant implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id
    @Schema(description = "主键")
    private String id;

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
     * 租户标志
     */
    @Schema(description = "租户标志")
    private String tenantMark;

    /**
     * 租户名
     */
    @Schema(description = "租户名")
    private String tenantName;

    /**
     * 联系人
     */
    @Schema(description = "联系人")
    private String linkUser;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    private String linkCellphone;

}
