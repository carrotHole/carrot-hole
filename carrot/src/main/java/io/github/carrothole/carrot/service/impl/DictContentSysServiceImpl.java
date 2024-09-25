package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.DictContentSys;
import io.github.carrothole.carrot.entity.vo.PageVO;
import io.github.carrothole.carrot.mapper.DictContentSysMapper;
import io.github.carrothole.carrot.service.DictContentSysService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.DictContentSysTableDef.DICT_CONTENT_SYS;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 2024-09-21
 */
@Service
public class DictContentSysServiceImpl extends ServiceImpl<DictContentSysMapper, DictContentSys> implements DictContentSysService {


    @Override
    public List<DictContentSys> listByType(String type) {
        return super.list(
                QueryWrapper.create()
                        .and(DICT_CONTENT_SYS.TYPE.eq(type))
                        .orderBy(DICT_CONTENT_SYS.SORT.desc(),DICT_CONTENT_SYS.MARK.asc())
        );
    }

    @Override
    public boolean removeByType(String type) {
        return super.remove(QueryWrapper.create().and(DICT_CONTENT_SYS.TYPE.eq(type)));
    }
}
