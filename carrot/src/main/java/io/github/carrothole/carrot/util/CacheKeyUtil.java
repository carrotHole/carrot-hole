package io.github.carrothole.carrot.util;

/**
 * 缓存key工具类
 *
 * @author moon
 * @since 0.0.1
 */
public class CacheKeyUtil {

    private static final String PREFIX = "carrot:";

    public static String getTokenKey(String tenantId, String username, String randomKey) {
        return PREFIX  + tenantId + ":" + username + ":" + randomKey + ":token";
    }

    public static String getUserKey(String tenantId, String userId, String randomKey) {
        return PREFIX  + tenantId + ":" + userId + ":" + randomKey + ":user";
    }

    public static String getIdentifierKey(String identifierKey) {
        return PREFIX + "identifierKey:" + identifierKey;
    }

    public static String getVerifyCodeKey(String codeKey) {
        return PREFIX + "verifyCode:" + codeKey;
    }



}
