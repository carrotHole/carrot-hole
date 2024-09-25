package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.Dict;
import io.github.carrothole.carrot.mapper.DictMapper;
import io.github.carrothole.carrot.service.DictService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 2024-09-21
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

}
