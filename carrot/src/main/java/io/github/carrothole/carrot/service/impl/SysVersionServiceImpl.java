package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.SysVersion;
import io.github.carrothole.carrot.mapper.SysVersionMapper;
import io.github.carrothole.carrot.service.SysVersionService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class SysVersionServiceImpl extends ServiceImpl<SysVersionMapper, SysVersion> implements SysVersionService {

}
