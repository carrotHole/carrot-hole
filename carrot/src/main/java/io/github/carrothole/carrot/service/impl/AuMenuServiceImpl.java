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

import java.util.*;

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
    public boolean save(AuMenu auMenu){
        if (auMenu.getParentId() == null){
            auMenu.setParentId(CarrotConst.MENU_ROOT_ID);
        }
        return super.save(auMenu);
    }
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
        return formatTree(auMenuResultVOList);
    }

    public List<AuMenuResultVO> formatTree(List<AuMenuResultVO> auMenuResultVOList){
        if (auMenuResultVOList.isEmpty()) return null;
        final ArrayList<AuMenuResultVO> resultList = new ArrayList<>();

        // 映射
        Map<String, AuMenuResultVO> map = new HashMap<>();
        auMenuResultVOList.forEach(auMenuResultVO -> map.put(auMenuResultVO.getId(), auMenuResultVO));

        // 遍历
        auMenuResultVOList.forEach(auMenuResultVO -> {
            if (auMenuResultVO.getParentId().equals(CarrotConst.MENU_ROOT_ID)){
                resultList.add(auMenuResultVO);
            }else {
                AuMenuResultVO parent = map.get(auMenuResultVO.getParentId());
                List<AuMenuResultVO> children = parent.getChildren();
                if (children == null){
                    children = new ArrayList<>();
                    parent.setChildren(children);
                }
                children.add(auMenuResultVO);
            }
        });

        treeSort(resultList);

        return resultList;
    }

    private void treeSort(List<AuMenuResultVO> resultList) {
        resultList.sort(Comparator.comparing(AuMenuResultVO::getSort));
        resultList.forEach(auMenuResultVO -> {
            if (auMenuResultVO.getChildren() != null){
                treeSort(auMenuResultVO.getChildren());
            }
        });
    }

}
