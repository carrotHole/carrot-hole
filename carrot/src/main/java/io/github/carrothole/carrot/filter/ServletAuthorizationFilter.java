package io.github.carrothole.carrot.filter;

import io.github.carrothole.carrot.property.CarrotProperty;
import io.github.carrothole.carrot.util.SecurityUtil;
import io.github.carrothole.carrot.util.TokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Description: Servlet权限控制过滤器<br/>
 * date: 2024-9-8 22:27
 *
 * @author moon
 * @since 0.0.1
 */
@Order(Integer.MIN_VALUE)
@Component
public class ServletAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private CarrotProperty carrotProperty;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        final String token = request.getHeader(carrotProperty.web.tokenKey);
        // 校验token
        TokenUtil.verify(token);
        // 设置token
        SecurityUtil.setToken(token);
        filterChain.doFilter(request, response);
    }
}
