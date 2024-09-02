package com.carrothole.carrot.entity;

import cn.hutool.json.JSONUtil;
import com.carrothole.carrot.config.mf.MfConstant;
import com.carrothole.carrot.config.validate.ValidateGroup;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import java.io.Serializable;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "部门")
@Table("au_dept")
public class AuDept implements Serializable {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    private String id;

    /**
     * 租户主键
     */
    @Schema(description = "租户主键")
    private String tenantId;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createdTime;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String deptName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 部门类型
     */
    @Schema(description = "部门类型")
    @NotBlank(message = "部门类型不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer deptType;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotBlank(message = "状态不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private Integer status;

    /**
     * 父主键
     */
    @Schema(description = "父主键")
    @NotBlank(message = "父主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    private String parentId;

    /**
     * 上级部门主键集合
     */
    @Schema(description = "上级部门主键集合")
    private String parentIds;

    public void setParentIdList(List<String> parentIdList){
        this.parentIds = JSONUtil.toJsonStr(parentIdList);
    }

    public List<String>  getParentIdList(){
        return JSONUtil.toList(this.parentIds,String.class);
    }
}
