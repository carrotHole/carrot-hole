package io.github.carrothole.carrot.config.mf;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Description:
 * date: 2024-9-7 16:02
 *
 * @author moon
 * @since 0.0.1
 */

public class MfTenantInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通过 request 去获取租户 ID
        Long tenantId = getTenantIdByReuqest(request);

        //设置租户ID到 request 的 attribute
        request.setAttribute("tenantId", tenantId);

        return true;
    }
}