package io.github.carrothole.carrot.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.service.IService;
import io.github.carrothole.carrot.entity.DictContentSys;
import io.github.carrothole.carrot.entity.vo.PageVO;

/**
 *  服务层。
 *
 * @author Administrator
 * @since 2024-09-21
 */
public interface DictContentSysService extends IService<DictContentSys> {

    /**
     * 根据字典类型查询字典内容
     *
     * @param vo {@link PageVO}
     * @param type 字典类型
     * @return {@link Page<DictContentSys>}
     */
    Page<DictContentSys> pageByType(PageVO vo, String type);
}
