package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import io.github.carrothole.carrot.config.ValidateGroup;
import io.github.carrothole.processor.generateo.anno.*;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "角色")
@Table("au_role")
@GenVO(describe = "角色返回对象",
        append = {
                @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值", type = VOTypeEnum.RESULT),
                @AppendField(name = "roleTypeValue", typeName = "java.lang.String", describe = "角色类型值", type = VOTypeEnum.RESULT)
        },
        type = {VOTypeEnum.QUERY, VOTypeEnum.RESULT})
public class AuRole extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    private String id;


    /**
     * 角色名
     */
    @Schema(description = "角色名")
    @GenVOField(describe = "角色名")
    @NotBlank(message = "角色名不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String roleName;

    /**
     * 角色类型
     */
    @Schema(description = "角色类型")
    @GenVOField(describe = "角色类型")
    @NotBlank(message = "角色类型不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String roleType;

    /**
     * 应用主键
     */
    @Schema(description = "应用主键")
    @GenVOField(describe = "应用主键")
    @NotBlank(message = "应用主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String projectId;

    /**
     * 部门主键
     */
    @Schema(description = "部门主键")
    @GenVOField(describe = "部门主键")
    @NotBlank(message = "部门主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String deptId;

    /**
     * 级别
     */
    @Schema(description = "级别")
    @GenVOField(describe = "级别")
    @NotNull(message = "级别不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer level;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @GenVOField(describe = "状态")
    @NotBlank(message = "状态不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    @GenVOField(describe = "备注")
    private String remark;

    /**
     * 菜单范围
     */
    @Schema(description = "菜单范围")
    @GenVOField(describe = "菜单范围")
    @NotNull(message = "菜单范围不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer menuRange;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @GenVOField(describe = "排序", type = VOTypeEnum.RESULT)
    private Integer sort;

}
