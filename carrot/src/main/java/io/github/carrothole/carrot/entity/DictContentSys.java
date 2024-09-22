package io.github.carrothole.carrot.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.sql.Timestamp;

import java.io.Serial;

import io.github.carrothole.carrot.config.mf.MfDefaultInsertListener;
import io.github.carrothole.carrot.config.mf.MfDefaultUpdateListener;
import io.github.carrothole.processor.generateo.anno.GenVO;
import io.github.carrothole.processor.generateo.anno.GenVOField;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  实体类。
 *
 * @author Administrator
 * @since 2024-09-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
@Table(value = "dict_content_sys", onInsert = MfDefaultInsertListener.class, onUpdate = MfDefaultUpdateListener.class)
@GenVO(ignore = true, describe = "字典值",type = {VOTypeEnum.QUERY, VOTypeEnum.RESULT})
public class DictContentSys extends BaseTenant {

    /**
     * 主键
     */
    @Id
    @Schema(description = "主键")
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    private String id;

    /**
     * 字典类型
     */
    @GenVOField(describe = "字典类型")
    @Schema(description = "字典类型")
    private String type;

    /**
     * 编制
     */
    @Schema(description = "编码")
    @GenVOField(describe = "编码")
    private String mark;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @GenVOField(describe = "名称")
    private String name;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @GenVOField(describe = "备注")
    private String remark;

    /**
     * 租户主键
     */
    @Schema(description = "租户主键")
    @GenVOField(describe = "租户主键")
    private String tenantId;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @GenVOField(describe = "排序", type = VOTypeEnum.RESULT)
    private Integer sort;

}
