package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

import java.io.Serial;

import io.github.carrothole.carrot.config.validate.ValidateGroup;
import io.github.carrothole.processor.generateo.anno.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色")
@Table("au_role")
@GenQueryVO(describe = "角色查询对象")
@GenResultVO(describe = "角色返回对象",
append = {
        @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值"),
        @AppendField(name = "roleTypeValue", typeName = "java.lang.String", describe = "角色类型值")
})
public class AuRole extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @GenResultVOField(describe = "主键")
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    private String id;


    /**
     * 角色名
     */
    @Schema(description = "角色名")
    @GenQueryVOField(describe = "角色名")
    @GenResultVOField(describe = "角色名")
    @NotBlank(message = "角色名不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String roleName;

    /**
     * 角色类型
     */
    @Schema(description = "角色类型")
    @GenQueryVOField(describe = "角色类型")
    @GenResultVOField(describe = "角色类型")
    @NotBlank(message = "角色类型不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String roleType;

    /**
     * 应用主键
     */
    @Schema(description = "应用主键")
    @GenQueryVOField(describe = "应用主键")
    @GenResultVOField(describe = "应用主键")
    @NotBlank(message = "应用主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String projectId;

    /**
     * 部门主键
     */
    @Schema(description = "部门主键")
    @GenQueryVOField(describe = "部门主键")
    @GenResultVOField(describe = "部门主键")
    @NotBlank(message = "部门主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String deptId;

    /**
     * 级别
     */
    @Schema(description = "级别")
    @GenQueryVOField(describe = "级别")
    @GenResultVOField(describe = "级别")
    @NotNull(message = "级别不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer level;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @GenQueryVOField(describe = "状态")
    @GenResultVOField(describe = "状态")
    @NotBlank(message = "状态不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @GenQueryVOField(describe = "备注")
    @GenResultVOField(describe = "备注")
    private String remark;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @GenResultVOField(describe = "排序")
    private Integer sort;

}
