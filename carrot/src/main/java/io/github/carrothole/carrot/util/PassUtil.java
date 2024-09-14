package io.github.carrothole.carrot.util;

import cn.hutool.crypto.digest.BCrypt;

/**
 * 密码工具
 *
 * @author moon
 * @since 0.0.1
 */

public class PassUtil {

    /**
     * 加密
     * @param password 明文明码
     * @return 密文密码
     */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }

    /**
     * 校验
     * @param password 密码
     * @param hash 密文密码
     * @return boolean
     */
    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password,hash);
    }
}
