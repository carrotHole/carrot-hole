package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import io.github.carrothole.carrot.config.mf.MfDefaultInsertListener;
import io.github.carrothole.carrot.config.mf.MfDefaultUpdateListener;
import io.github.carrothole.carrot.config.ValidateGroup;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.io.Serial;

import io.github.carrothole.processor.generateo.anno.AppendField;
import io.github.carrothole.processor.generateo.anno.GenVO;
import io.github.carrothole.processor.generateo.anno.GenVOField;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "租户对象")
@Table(value = "au_tenant",onInsert = MfDefaultInsertListener.class, onUpdate = MfDefaultUpdateListener.class )
@GenVO(describe = "角色",
        append = {
                @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值", type = VOTypeEnum.RESULT),
        },
        type = {VOTypeEnum.QUERY, VOTypeEnum.RESULT})
public class AuTenant extends BaseUserTime {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = ValidateGroup.Update.class)
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    private String id;

    /**
     * 租户标志
     */
    @Schema(description = "租户标志")
    @NotBlank(message = "租户标志不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "租户标志")
    private String tenantMark;

    /**
     * 租户名
     */
    @Schema(description = "租户名")
    @NotBlank(message = "租户标志不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "租户名")
    private String tenantName;

    /**
     * 联系人
     */
    @Schema(description = "联系人")
    @GenVOField(describe = "联系人")
    private String linkUser;

    /**
     * 联系电话
     */
    @Schema(description = "联系电话")
    @GenVOField(describe = "联系电话")
    private String linkCellphone;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @GenVOField(describe = "状态")
    private Integer status;
}
