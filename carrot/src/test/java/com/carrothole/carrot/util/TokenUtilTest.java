package com.carrothole.carrot.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestMethod;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

/**
 * ClassName: TokenUtil
 * Description:
 * date: 2024-8-31 20:38
 *
 * @author moon
 * @since JDK 1.8
 */

public class TokenUtilTest {

    @Test
    public void create_and_verify() {
        String tenantId = IdUtil.simpleUUID();
        String username = RandomUtil.randomString(11);
        Long expireTime = 36000L;

        String token = TokenUtil.create(tenantId, username, expireTime,false);
        System.out.println(token);
        // 断言不为空
        assertTrue(StrUtil.isNotBlank(token));
        final boolean verify = TokenUtil.verify(token, false, true);
        assertEquals(true,verify);

        final TokenUtil.TokenPayLoad payLoad = TokenUtil.getPayLoad(token, false);
        assertEquals(tenantId,payLoad.getTenantId());
        assertEquals(username,payLoad.getUsername());
        assertNotEquals(expireTime,payLoad.getExpireTime());
    }

}
