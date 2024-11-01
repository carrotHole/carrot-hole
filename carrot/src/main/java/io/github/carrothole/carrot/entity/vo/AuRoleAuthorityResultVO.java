package io.github.carrothole.carrot.entity.vo;

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
public class AuRoleAuthorityResultVO extends AuRoleAuthorityVO {

    List<AuRoleMenuAuthorityVO> roleMenuAuthorityList;

}
