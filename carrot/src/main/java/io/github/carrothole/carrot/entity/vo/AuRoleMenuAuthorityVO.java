package io.github.carrothole.carrot.entity.vo;

import io.github.carrothole.carrot.entity.AuRoleMenuAuthority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author moon
 * @since
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuRoleMenuAuthorityVO implements Serializable {

    private AuRoleMenuAuthority roleMenuAuthority;

    private List<String> deptIdList;
}

