package io.github.carrothole.carrot.util;

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

    public static ThreadLocal<String> tokenLocal = new ThreadLocal<>();

    public static void setToken(String token){
        SecurityUtil.tokenLocal.set(token);
    }

    /**
     * 获取token<br>
     * 当前方法应该可以通过各种环境获取token
     * 1. servlet
     * 2. dubbo
     * 3. ws
     * @return token
     */
    public static String getToken(){
        String token = tokenLocal.get();
        if (token != null) return token;

        token = request.getHeader("Authorization");
        if (token != null) return token;

        // todo 其他环境获取token
        return token;
    }

    public static TokenUtil.TokenPayLoad getPayLoad(){
        return TokenUtil.getPayLoad(getToken());
    }


    private static HttpServletRequest request;

    @Autowired
    public HttpServletRequest request_;


    @PostConstruct
    public void init(){
        SecurityUtil.request = request_;
    }

}
