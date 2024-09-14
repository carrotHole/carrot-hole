package io.github.carrothole.carrot.controller;

import cn.hutool.core.util.StrUtil;
import io.github.carrothole.carrot.entity.ro.AuUserResultVO;
import io.github.carrothole.carrot.entity.vo.LoginResultVO;
import io.github.carrothole.carrot.entity.vo.LoginVO;
import io.github.carrothole.carrot.exception.ParamException;
import io.github.carrothole.carrot.service.AuLoginService;
import io.github.carrothole.carrot.service.AuUserService;
import io.github.carrothole.carrot.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Description:  <br>
 * Date: 2024/8/29 13:44 <br>
 *
 * @author moon
 * @since 0.0.1
 */
@RestController
@Tag(name = "登录")
@RequestMapping("/login")
public class AuLoginController{

    @Autowired
    private AuLoginService auLoginService;

    @Autowired
    private AuUserService auUserService;

    @PostMapping("/username")
    @Operation(description = "用户名密码登录")
    public LoginResultVO login(@RequestBody @Valid LoginVO vo) {
        if (StrUtil.isNotBlank(vo.getPasswordEnc())) {
            // todo 解密并赋值给password
            // vo.setPassword();
        }
        if (StrUtil.isBlank(vo.getPassword())) {
            throw new ParamException("密码不能为空");
        }

        // todo 读取配置是否需要验证码,配合登录端类型判断是否需要做验证码校验
//        if (StrUtil.isBlank(vo.getCaptchaKey()) || StrUtil.isBlank(vo.getCaptcha())){
//            throw new ParamException("请输入验证码");
//        }
        return auLoginService.login(vo);
    }

    @GetMapping("token")
    @Operation(description = "生成token")
    public String createToken(@Schema(description = "identifyKey") String identifyKey, @Schema(description = "部门主键")  String deptId) {
        return auLoginService.createToken(identifyKey, deptId);
    }

    @PostMapping("logout")
    @Operation(description = "退出登录")
    public boolean logout() {
        return auLoginService.logout();
    }

    @GetMapping("user")
    @Operation(description = "获取用户信息")
    public AuUserResultVO getUser() {
        return SecurityUtil.getUser();
    }

    @GetMapping("menu")
    @Operation(description = "获取菜单")
    public List<io.github.carrothole.carrot.entity.ro.AuMenuResultVO> getMenu(@Schema(description = "应用主键") @Valid @NotBlank(message = "应用主键不能为空") String projectId){
        return SecurityUtil.getMenuList(projectId);
    }

}
