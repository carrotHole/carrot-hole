package io.github.carrothole.carrot.config.mf;

import com.mybatisflex.core.tenant.TenantFactory;
import io.github.carrothole.carrot.util.SecurityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Description: 获取租户id
 * date: 2024-9-7 16:08
 *
 * @author moon
 * @since 0.0.1
 */
@Component
public class MfTenantFactory implements TenantFactory {

    public Object[] getTenantIds(){
        final String tenantId = SecurityUtil.getPayLoad().getTenantId();
        return new Object[]{tenantId};
    }
}