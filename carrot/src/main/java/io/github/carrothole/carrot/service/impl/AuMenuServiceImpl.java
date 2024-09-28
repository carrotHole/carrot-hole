package io.github.carrothole.carrot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.constant.CarrotConst;
import io.github.carrothole.carrot.entity.AuMenu;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;
import io.github.carrothole.carrot.mapper.AuMenuMapper;
import io.github.carrothole.carrot.service.AuMenuService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<AuMenuResultVO> getTree(String projectId) {
        final List<AuMenuResultVO> auMenuResultVOList = this.listByProjectId(projectId);
        return formatTree(auMenuResultVOList, CarrotConst.MENU_ROOT_ID);
    }

    public List<AuMenuResultVO> formatTree(List<AuMenuResultVO> auMenuResultVOList, String parentId ){
        final ArrayList<AuMenuResultVO> resultList = new ArrayList<>();
        for (int i = auMenuResultVOList.size() - 1; i >= 0; i--) {
            final AuMenuResultVO menuResultVO = auMenuResultVOList.get(i);
            if (Objects.equals(menuResultVO.getParentId(), parentId)){
                auMenuResultVOList.remove(i);
                final AuMenuResultVO treeResultVO = BeanUtil.copyProperties(menuResultVO, AuMenuResultVO.class);
                treeResultVO.setChildren(formatTree(auMenuResultVOList, treeResultVO.getId()));
            }
        }
        return resultList;
    }

}
