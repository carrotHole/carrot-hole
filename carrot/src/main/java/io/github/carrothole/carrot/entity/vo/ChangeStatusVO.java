package io.github.carrothole.carrot.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: 修改状态VO
 * date: 2024-9-7 22:45
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@Schema(description = "修改状态对象")
public class ChangeStatusVO implements Serializable {

    /**
     * 主键
     */
    @NotBlank(message = "主键不能为空")
    @Schema(description = "主键")
    private String id;

    @NotNull(message = "状态不能为空")
    @Schema(description = "状态")
    private Integer status;

}
