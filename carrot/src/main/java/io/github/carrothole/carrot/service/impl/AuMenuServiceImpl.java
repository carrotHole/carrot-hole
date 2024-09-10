package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuMenu;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;
import io.github.carrothole.carrot.mapper.AuMenuMapper;
import io.github.carrothole.carrot.service.AuMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.AuMenuTableDef.AU_MENU;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuMenuServiceImpl extends ServiceImpl<AuMenuMapper, AuMenu> implements AuMenuService {

    @Override
    public List<AuMenuResultVO> listByProjectId(String projectId) {
        return super.listAs(
                QueryWrapper.create()
                        .select(AU_MENU.ALL_COLUMNS)
                        .from(AU_MENU)
                        .and(AU_MENU.PROJECT_ID.eq(projectId))
                , AuMenuResultVO.class
        );
    }
}
