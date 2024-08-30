package com.carrothole.carrot.entity;

import com.carrothole.carrot.config.mf.MfConstant;
import com.carrothole.carrot.config.mf.MfDefaultInsertListener;
import com.carrothole.carrot.config.mf.MfDefaultUpdateListener;
import com.carrothole.carrot.config.validate.ValidateGroup;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;

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
 * @since 2024-08-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "租户对象")
@Table(value = "au_tenant",onInsert = MfDefaultInsertListener.class, onUpdate = MfDefaultUpdateListener.class )
public class AuTenant extends BaseUserTime {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = ValidateGroup.UPDATE)
    private String id;

    /**
     * 租户标志
     */
    @Schema(description = "租户标志")
    @NotBlank(message = "租户标志不能为空", groups = {ValidateGroup.UPDATE, ValidateGroup.SAVE})
    private String tenantMark;

    /**
     * 租户名
     */
    @Schema(description = "租户名")
    @NotBlank(message = "租户标志不能为空", groups = {ValidateGroup.UPDATE, ValidateGroup.SAVE})
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
