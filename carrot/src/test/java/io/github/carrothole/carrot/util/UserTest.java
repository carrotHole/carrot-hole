package io.github.carrothole.carrot.util;

import io.github.carrothole.carrot.entity.AuUser;
import org.junit.jupiter.api.Test;

/**
 * @author moon
 * @since 0.0.1
 */
public class UserTest {


    @Test
    public void test(){

        AuUser user = AuUser.builder()
                .username("moon")
                .password("moon")
                .nickname("超级管理员")
                .realUser(1)
                .build();
        System.out.println(user);

    }

}
