package com.carrothole.carrot.util;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.carrothole.carrot.exception.AuthorizationException;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: token工具类 <br>
 * Date: 2024/8/30 15:59 <br>
 *
 * @author moon
 * @since 0.0.1
 */
public class TokenUtil {

    private static final String tenantIdKey = "ti";
    private static final String usernameKey = "un";
    private static final String expireTimeKey = "et";
    private static final String randomKey = "rk";

    // todo 做成可配置
    private static final byte[] key = "'c@rR0t1!'".getBytes(StandardCharsets.UTF_8);


    /**
     * 创建token，默认过期时间12小时
     *
     * @param tenantId 租户主键
     * @param username 用户名
     * @return token
     */
    public static String create(String tenantId, String username) {
        return create(tenantId, username, 12 * 60 * 60 * 1000L);
    }

    public static String create(String tenantId, String username, long expireTime) {
        return create(tenantId, username, expireTime, true);
    }

    /**
     * 创建token
     *
     * @param tenantId   租户主键
     * @param username   用户名
     * @param expireTime 过期时间
     * @return token
     */
    public static String create(String tenantId, String username, long expireTime, boolean cache) {
        // 生成一个随机的8位字符串,用于区分多端登录
        final TokenPayLoad tokenPayLoad = new TokenPayLoad(tenantId, username, RandomUtil.randomString(8), System.currentTimeMillis()+expireTime);
        final String token = JWTUtil.createToken(tokenPayLoad, key);
        if (cache) {
            // todo cache
        }
        return token;
    }

    /**
     * 有效验证
     *
     * @param token token
     * @return 是否有效
     */
    public static boolean verify(String token) {
        return verify(token, true, true);
    }


    /**
     * 有效验证
     *
     * @param token  token
     * @param cache  是否缓存验证
     * @param throwE 是否抛出异常
     * @return 是否有效
     */
    public static boolean verify(String token, boolean cache, boolean throwE) {
        String errMsg = "token校验失败";

        final JWT jwt = JWT.of(token);
        boolean verify = jwt.setKey(key).verify();

        ii:
        if (verify) {
            final JSONObject payloads = jwt.getPayloads();
            final Long expireTime = payloads.getLong(expireTimeKey);
            if (expireTime == null) {
                verify = false;
                errMsg = "token解析出错";
                break ii;
            }

            if (expireTime < System.currentTimeMillis()) {
                verify = false;
                errMsg = "token已过期";
                break ii;
            }

            if (cache) {
                // todo cache
            }
        }

        if (!verify && throwE) {
            throw new AuthorizationException(errMsg);
        }

        return verify;
    }

    /**
     * 获取token中的数据
     * @param token token
     * @return {@link TokenPayLoad}
     */
    public static TokenPayLoad getPayLoad(String token) {
        return getPayLoad(token, true);
    }

    /**
     * 获取token中的数据
     * @param token token
     * @param cache 是否缓存校验
     * @return {@link TokenPayLoad}
     */
    public static TokenPayLoad getPayLoad(String token, boolean cache) {

        final JWT jwt = JWT.of(token);
        boolean verify = jwt.setKey(key).verify();

        if (!verify) {
            throw new AuthorizationException("token校验失败");
        }
        final JSONObject payloads = jwt.getPayloads();
        final Long expireTime = payloads.getLong(expireTimeKey);
        if (expireTime == null) {
            throw new AuthorizationException("token解析出错");
        }

        if (expireTime < System.currentTimeMillis()) {
            throw new AuthorizationException("token已过期");
        }

        if (cache){
            // todo cache ,从缓存数据中获取用户信息
        }

        return new TokenPayLoad(payloads.getStr(tenantIdKey), payloads.getStr(usernameKey), payloads.getStr(randomKey), expireTime);

    }

    static class TokenPayLoad extends HashMap<String, Object> {
        public TokenPayLoad(String tenantId, String username, String randomStr, long expireTime) {
            super();
            setTenantId(tenantId);
            setUsername(username);
            setRandom(randomStr);
            setExpireTime(expireTime);
        }


        public String getTenantId() {
            return (String) this.get(tenantIdKey);
        }

        public void setTenantId(String tenantId) {
            this.put(tenantIdKey, tenantId);
        }

        public String getUsername() {
            return (String) this.get(usernameKey);
        }

        public void setUsername(String username) {
            this.put(usernameKey, username);
        }

        public String getRandom() {
            return (String) this.get(randomKey);
        }

        public void setRandom(String random) {
            this.put(randomKey, random);
        }

        public Long getExpireTime() {
            return (Long) this.get(expireTimeKey);
        }

        public void setExpireTime(Long expireTime) {
            this.put(expireTimeKey, expireTime);
        }
    }

}



