package io.github.carrothole.carrot.entity.vo;

import io.github.carrothole.carrot.entity.AuUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

/**
 * Description: AuUser扩展对象 <br>
 * Date: 2024/9/2 15:53 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class AuUserVO extends AuUser {

    @Schema(description = "部门主键集合")
    private List<String> deptIdList;

    @Schema(description = "主部门主键")
    private String mainDeptId;

    @Schema(description = "角色主键集合")
    private Map<String,List<Role>> role;

}
