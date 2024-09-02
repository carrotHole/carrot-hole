package com.carrothole.carrot.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Description: 鉴权过滤器 <br>
 * Date: 2024/9/2 13:42 <br>
 *
 * @author moon
 * @since 0.0.1
 */
public class AuthorizeFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // todo 对请求进行鉴权
    }
}
