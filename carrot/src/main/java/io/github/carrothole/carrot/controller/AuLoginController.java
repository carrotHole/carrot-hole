package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import io.github.carrothole.carrot.entity.vo.LoginResultVO;
import io.github.carrothole.carrot.entity.vo.LoginVO;
import io.github.carrothole.carrot.exception.ParamException;
import io.github.carrothole.carrot.service.AuLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.support.DefaultClientCodecConfigurer;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:  <br>
 * Date: 2024/8/29 13:44 <br>
 *
 * @author moon
 * @since
 */
@RestController
@Tag(name = "登录")
@RequestMapping("/login")
public class AuLoginController extends DefaultClientCodecConfigurer {

    @Autowired
    private AuLoginService auLoginService;
    @PostMapping("username")
    @Operation(description="用户名密码登录")
    public LoginResultVO login(@RequestBody @Validated LoginVO vo){
        if (StrUtil.isNotBlank(vo.getPasswordEnc())){
            // todo 解密并赋值给password
            // vo.setPassword();
        }
        if (StrUtil.isBlank(vo.getCaptcha())){
            throw new ParamException("密码不能为空");
        }

        // todo 读取配置是否需要验证码,配合登录端类型判断是否需要做验证码校验
//        if (StrUtil.isBlank(vo.getCaptchaKey()) || StrUtil.isBlank(vo.getCaptcha())){
//            throw new ParamException("请输入验证码");
//        }
        return auLoginService.login(vo);
    }

    @GetMapping("token")
    @Operation(description="生成token")
    public String createToken(@Schema(description = "identifyKey")@Valid @NotBlank(message = "identifyKey不能为空") String identifyKey,@Schema(description = "部门主键") @Valid @NotBlank(message = "部门主键不能为空") String deptId){
       return auLoginService.createToken(identifyKey,deptId);
    }



    @PostMapping("logout")
    @Operation(description="退出登录")
    public boolean logout(){
       return auLoginService.logout();
    }
}
