package io.github.carrothole.carrot.entity.vo;

import io.github.carrothole.carrot.entity.AuRoleAuthority;
import io.github.carrothole.carrot.entity.AuRoleAuthorityRange;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthority;
import io.github.carrothole.carrot.entity.AuRoleMenuAuthorityRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * @author moon
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuRoleAuthorityVO implements Serializable {

    private AuRoleAuthority roleAuthority;

    private List<String> deptIdList;

}
