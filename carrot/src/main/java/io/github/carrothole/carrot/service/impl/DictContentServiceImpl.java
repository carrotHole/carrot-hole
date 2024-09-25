package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.DictContent;
import io.github.carrothole.carrot.mapper.DictContentMapper;
import io.github.carrothole.carrot.service.DictContentService;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.github.carrothole.carrot.entity.table.DictContentTableDef.DICT_CONTENT;

/**
 * 服务层实现。
 *
 * @author Administrator
 * @since 2024-09-21
 */
@Service
public class DictContentServiceImpl extends ServiceImpl<DictContentMapper, DictContent> implements DictContentService {


    @Override
    public List<DictContent> listByType(String type) {
        return super.list(
                QueryWrapper.create()
                        .and(DICT_CONTENT.TYPE.eq(type))
                        .orderBy(DICT_CONTENT.SORT.desc(),DICT_CONTENT.MARK.asc())
        );
    }

    @Override
    public boolean removeByType(String type) {
        return super.remove(QueryWrapper.create().and(DICT_CONTENT.TYPE.eq(type)));
    }
}
