package com.carrothole.carrot.entity.vo;

import com.carrothole.carrot.entity.AuUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

/**
 * Description: 用户创建/修改用对象 <br>
 * Date: 2024/9/2 15:53 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class AuUserOperationVO extends AuUser {

    @Schema(description = "主部门主键")
    private String deptId;

}
