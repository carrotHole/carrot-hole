package io.github.carrothole.carrot.entity;

import io.github.carrothole.processor.generateo.anno.GenVOField;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:  <br>
 * Date: 2024/8/30 15:30 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseUserTime implements Serializable {

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @GenVOField(describe = "创建人", type = VOTypeEnum.RESULT)
    private String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @GenVOField(describe = "创建时间", between = true)
    private Date createdTime;

}
