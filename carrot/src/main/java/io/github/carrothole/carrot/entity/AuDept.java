package io.github.carrothole.carrot.entity;

import cn.hutool.json.JSONUtil;
import io.github.carrothole.carrot.config.mf.MfConstant;
import io.github.carrothole.carrot.config.ValidateGroup;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import java.util.List;

import io.github.carrothole.carrot.config.mf.MfDefaultInsertListener;
import io.github.carrothole.carrot.config.mf.MfDefaultUpdateListener;
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
 * 部门实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "部门")
@Table(value = "au_dept",  onInsert = MfDefaultInsertListener.class, onUpdate = MfDefaultUpdateListener.class)
@GenVO(describe = "部门返回对象",
        append = {
                @AppendField(name = "deptTypeValue", typeName = "java.lang.String", describe = "部门类型值", type = VOTypeEnum.RESULT)
                , @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值", type = VOTypeEnum.RESULT)
        },
        type = {VOTypeEnum.QUERY,VOTypeEnum.RESULT}
)
public class AuDept extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @NotBlank(message = "主键不能为空", groups = {ValidateGroup.Update.class})
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    private String id;


    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @NotBlank(message = "部门名称不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "部门名称")
    private String deptName;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @GenVOField(describe = "排序", type = VOTypeEnum.RESULT)
    private Integer sort;

    /**
     * 部门类型
     */
    @Schema(description = "部门类型")
    @NotBlank(message = "部门类型不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "部门类型")
    private String deptType;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @NotNull(message = "状态不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "状态")
    private Integer status;

    /**
     * 父主键
     */
    @Schema(description = "父主键")
    @NotBlank(message = "父主键不能为空", groups = {ValidateGroup.Update.class, ValidateGroup.Save.class})
    @GenVOField(describe = "父主键")
    private String parentId;

    /**
     * 上级部门主键集合
     */
    @Schema(description = "上级部门主键集合")
    private String parentIds;

    public void setParentIdList(List<String> parentIdList) {
        this.parentIds = JSONUtil.toJsonStr(parentIdList);
    }

    public List<String> getParentIdList() {
        return JSONUtil.toList(this.parentIds, String.class);
    }
}
