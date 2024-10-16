package io.github.carrothole.carrot.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import io.github.carrothole.carrot.entity.ro.AuDeptResultVO;

/**
 * @author moon
 * @since 0.0.1
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class AuDeptTreeResultVO extends AuDeptResultVO {

    public List<AuDeptTreeResultVO> children;

}
