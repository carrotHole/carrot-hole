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
import org.springframework.util.AntPathMatcher;
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

    private final static AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        // 对比carrotProperty.web.ignoreUris 如果包含则不校验token,uri中可能包含*,则使用相应的匹配规则
        if (isMatch(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 获取token
        final String token = request.getHeader(carrotProperty.web.tokenKey);

        // 校验token
        TokenUtil.verify(token);
        // 设置token
        SecurityUtil.setToken(token);
        try {

            filterChain.doFilter(request, response);
        }finally {
            SecurityUtil.tokenLocal.remove();
        }
    }


    public boolean isMatch(String path) {
        for (String ignoreUri : carrotProperty.web.ignoreUris) {
            if (antPathMatcher.match(ignoreUri, path)) {
                return true;
            }
        }
        return false;
     }

}
