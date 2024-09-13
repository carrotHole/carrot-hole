package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import io.github.carrothole.carrot.config.ValidateGroup;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import io.github.carrothole.processor.generateo.anno.*;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
@Table("au_user")
@GenVO(describe = "用户返回对象",
        append = {
            @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值", type = VOTypeEnum.RESULT),
            @AppendField(name = "realUserValue", typeName = "java.lang.String", describe = "是否真实用户值", type = VOTypeEnum.RESULT),
        },
        type = {VOTypeEnum.QUERY, VOTypeEnum.RESULT}
)
public class AuUser extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    private String id;


    /**
     * 创建方式
     */
    @Schema(description = "创建方式")
    @NotNull(message = "创建类型不能为空", groups = {ValidateGroup.Update.class})
    @GenVOField(describe = "创建方式")
    private Integer createdType;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "用户名")
    private String username;


    /**
     * 密码
     */
    @Column()
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空", groups = {ValidateGroup.Save.class})
    private String password;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @GenVOField(describe = "昵称")
    private String nickname;

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
    @NotNull(message = "状态不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "状态")
    private Integer status;

    /**
     * 部门主键
     */
    @Schema(description = "部门主键")
    @NotNull(message = "部门主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "部门主键")
    private String deptId;

    /**
     * 是否为真实用户 同一username下,只能有一个是真实用户
     */
    @Schema(description = "是否为真实用户")
    @NotNull(message = "指定用户是否为真实用户", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "是否为真实用户")
    private Integer realUser;

}
