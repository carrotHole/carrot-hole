package com.carrothole.carrot.entity;

import com.carrothole.carrot.config.mf.MfConstant;
import com.carrothole.carrot.config.validate.ValidateGroup;
import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

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
@Schema(description = "")
@Table("au_user")
public class AuUser extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    private String id;


    /**
     * 创建方式
     */
    @Schema(description = "创建方式")
    @NotNull(message = "创建类型不能为空", groups = {ValidateGroup.Update.class})
    private Integer createdType;

    /**
     * 用户名
     */
    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String username;

    /**
     * 密码
     */
    @Column()
    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空", groups = { ValidateGroup.Save.class})
    private String password;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer status;

}
