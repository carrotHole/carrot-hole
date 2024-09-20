package io.github.carrothole.carrot.entity;

import io.github.carrothole.carrot.config.mf.MfConstant;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import io.github.carrothole.carrot.config.mf.MfDefaultInsertListener;
import io.github.carrothole.carrot.config.mf.MfDefaultUpdateListener;
import io.github.carrothole.processor.generateo.anno.*;
import io.github.carrothole.processor.generateo.enums.VOTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


/**
 *  实体类。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
@Table(value = "au_menu",  onInsert = MfDefaultInsertListener.class, onUpdate = MfDefaultUpdateListener.class)
@GenVO(describe = "菜单返回对象",
    append = {
            @AppendField(name = "statusValue", typeName = "java.lang.String", describe = "状态值", type = VOTypeEnum.RESULT),
            @AppendField(name = "menuTypeValue", typeName = "java.lang.String", describe = "菜单类型值", type = VOTypeEnum.RESULT),
            @AppendField(name = "children", typeName = "java.util.List", describe = "子菜单集合", type = VOTypeEnum.RESULT)
    },
        type = {VOTypeEnum.QUERY,VOTypeEnum.RESULT}
)
public class AuMenu extends BaseUserTimeTenant {

    /**
     * 主键
     */
    @Id(keyType = KeyType.Generator, value = MfConstant.ID_GENERATOR)
    @Schema(description = "主键")
    @GenVOField(describe = "主键", type = VOTypeEnum.RESULT)
    private String id;


    /**
     * 父主键
     */
    @Schema(description = "父主键")
    @GenVOField(describe = "父主键")
    private String parentId;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    @GenVOField(describe = "菜单名称")
    private String menuName;

    /**
     * 菜单类型
     */
    @Schema(description = "菜单类型")
    @GenVOField(describe = "菜单类型")
    private String menuType;

    /**
     * 菜单地址
     */
    @Schema(description = "菜单地址")
    @GenVOField(describe = "菜单地址")
    private String menuUrl;

    /**
     * 组件地址
     */
    @Schema(description = "组件地址")
    @GenVOField(describe = "组件地址")
    private String componentPath;

    /**
     * 权限编码
     */
    @Schema(description = "权限编码")
    @GenVOField(describe = "权限编码")
    private String permissionCode;

    /**
     * 应用主键
     */
    @Schema(description = "应用主键")
    @GenVOField(describe = "应用主键")
    private String projectId;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @GenVOField(describe = "状态")
    private Integer status;

    /**
     * 排序
     */
    @Schema(description = "排序")
    @GenVOField(describe = "排序", type = VOTypeEnum.RESULT)
    private Integer sort;

    /**
     * 图标
     */
    @Schema(description = "图标")
    @GenVOField(describe = "图标", type = VOTypeEnum.RESULT)
    private String icon;

    /**
     * 级别
     */
    @Schema(description = "级别")
    @GenVOField(describe = "级别")
    private Integer level;

}
