package io.github.carrothole.carrot.controller;

import io.github.carrothole.carrot.service.ImageVerifyCodeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码控制层
 *
 * @author moon
 * @since 0.0.1
 */

@RestController
@Tag(name = "验证码")
@RequestMapping("/verifyCode")
public class SysVerifyCodeController {

    @Autowired
    private ImageVerifyCodeService imageVerifyCodeService;

    public ImageVerifyCodeService.ImageVerifyCode genImageVerifyCode(HttpServletRequest request, HttpServletResponse response){
        return imageVerifyCodeService.genCode(request, response);
    }

    public boolean checkImageVerifyCode(String key,String code){
        return imageVerifyCodeService.checkCode(key, code);
    }

}
