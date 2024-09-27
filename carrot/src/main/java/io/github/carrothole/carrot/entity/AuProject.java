package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import io.github.carrothole.carrot.config.mf.MfDefaultInsertListener;
import io.github.carrothole.carrot.config.mf.MfDefaultUpdateListener;
import io.github.carrothole.processor.generateo.anno.AppendField;
import io.github.carrothole.processor.generateo.anno.GenVO;
import io.github.carrothole.processor.generateo.anno.GenVOField;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 *  实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
@Table(value = "au_project",  onInsert = MfDefaultInsertListener.class, onUpdate = MfDefaultUpdateListener.class)
@GenVO(describe = "用户返回对象",
        append = {
                @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值", type = VOTypeEnum.RESULT),
        },
        type = {VOTypeEnum.QUERY, VOTypeEnum.RESULT}
)
public class AuProject extends BaseUserTimeTenant {


    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    private String id;


    /**
     * 应用名称
     */
    @Schema(description = "应用名称")
    @GenVOField(describe = "应用名称")
    private String projectName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @GenVOField(describe = "排序", type = VOTypeEnum.RESULT)
    private Integer sort;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @GenVOField(describe = "状态")
    private Integer status;

}
