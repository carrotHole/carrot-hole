package io.github.carrothole.carrot.entity;

import io.github.carrothole.processor.generateo.anno.GenQueryVOField;
import io.github.carrothole.processor.generateo.anno.GenResultVOField;
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
public class BaseTenant implements Serializable {

    /**
     * 租户主键
     */
    @Schema(description = "租户主键")
    @GenResultVOField(describe = "租户主键")
    @GenQueryVOField(describe = "租户主键")
    private String tenantId;
}
