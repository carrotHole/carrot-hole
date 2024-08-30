package com.carrothole.carrot.util;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 用户安全工具类 <br>
 * Date: 2024/8/30 15:51 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@Configuration
public class SecurityUtil {



    public static String getToken(){
        return request.getHeader("Authorization");
    }


    private static HttpServletRequest request;

    @Autowired
    public HttpServletRequest request_;


    @PostConstruct
    public void init(){
        SecurityUtil.request = request_;
    }

}
