package io.github.carrothole.carrot.config.mf;

import cn.hutool.core.util.IdUtil;
import com.mybatisflex.core.keygen.IKeyGenerator;

/**
 * Description: 自定义id生成类 <br>
 * Date: 2024/8/30 13:11 <br>
 *
 * @author moon
 * @since 0.0.1
 */
public class MfUUIDGenerator implements IKeyGenerator {

    @Override
    public Object generate(Object entity, String keyColumn) {
        return IdUtil.simpleUUID();
    }
}
