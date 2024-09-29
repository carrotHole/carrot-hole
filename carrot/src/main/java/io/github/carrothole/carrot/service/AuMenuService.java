package io.github.carrothole.carrot.service;

import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.AuMenu;
import io.github.carrothole.carrot.entity.ro.AuMenuResultVO;

import java.util.List;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 0.0.1
 */
public interface AuMenuService extends IService<AuMenu> {


    /**
     * 根据项目id获取菜单列表
     * @param projectId 项目id
     * @return {@link List<AuMenuResultVO>}
     */
    List<AuMenuResultVO> listByProjectId(String projectId);

    /**
     * 获取所有菜单树
     *
     * @param projectId 项目id
     * @return
     */
    List<AuMenuResultVO> getTree(String projectId);
}
