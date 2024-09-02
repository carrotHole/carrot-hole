package com.carrothole.carrot.util;

import cn.hutool.crypto.digest.BCrypt;

/**
 * Description: 密码工具
 * date: 2024-9-2 21:01
 *
 * @author moon
 * @since 0.0.1
 */

public class PassUtil {

    /**
     * 加密
     * @param password 明文明码
     * @param salt 盐
     * @return 密文密码
     */
    public static String encrypt(String password, String salt) {
        return BCrypt.hashpw(password,salt);
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
