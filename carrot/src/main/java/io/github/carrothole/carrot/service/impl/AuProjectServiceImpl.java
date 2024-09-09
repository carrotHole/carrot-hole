package io.github.carrothole.carrot.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import io.github.carrothole.carrot.entity.AuProject;
import io.github.carrothole.carrot.mapper.AuProjectMapper;
import io.github.carrothole.carrot.service.AuProjectService;
import org.springframework.stereotype.Service;

/**
 *  服务层实现。
 *
 * @author Administrator
 * @since 0.0.1
 */
@Service
public class AuProjectServiceImpl extends ServiceImpl<AuProjectMapper, AuProject> implements AuProjectService {

}
