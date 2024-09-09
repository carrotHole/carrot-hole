package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuMenu;
import io.github.carrothole.carrot.mapper.AuMenuMapper;
import io.github.carrothole.carrot.service.AuMenuService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuMenuServiceImpl extends ServiceImpl<AuMenuMapper, AuMenu> implements AuMenuService {

}
