package io.github.carrothole.carrot.service;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import io.github.carrothole.carrot.exception.VerifyCodeExceedException;
import io.github.carrothole.carrot.exception.VerifyCodeNotExistException;
import io.github.carrothole.carrot.util.CacheKeyUtil;
import io.github.carrothole.carrot.util.CacheUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 图片验证码服务
 *
 * @author moon
 * @since 0.0.1
 */
@Service("imageVerifyCodeService")
public class ImageVerifyCodeService {

    /**
     * 获取验证码
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     */
    public ImageVerifyCode genCode(HttpServletRequest request, HttpServletResponse response) {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100);
        String key = IdUtil.simpleUUID();
        CacheUtil.addCache(CacheKeyUtil.getVerifyCodeKey(key),captcha.getCode(),1000*60*5);
        return (new ImageVerifyCode(key,captcha.getImageBytes()));
    }

    /**
     * 校验验证码
     * @param key key
     * @param code 值
     * @return boolean
     * @throws VerifyCodeNotExistException,VerifyCodeExceedException 验证码过期和验证码不存在
     */
    public boolean checkCode(String key, String code) {
        CacheUtil.CacheValue cache = CacheUtil.getCache(CacheKeyUtil.getVerifyCodeKey(key));
        if (cache == null){
            throw new VerifyCodeNotExistException("验证码已失效");
        }
        if (cache.getExpireTime() < System.currentTimeMillis()){
            throw new VerifyCodeExceedException("验证码已过期");
        }
        return Objects.equals(cache.getValue(), code);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImageVerifyCode {
        private String key;
        private byte[] imageBytes;
    }


}
