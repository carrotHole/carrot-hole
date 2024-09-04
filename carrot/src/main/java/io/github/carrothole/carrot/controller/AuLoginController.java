package io.github.carrothole.carrot.controller;

import io.github.carrothole.carrot.entity.vo.LoginVO;
import io.github.carrothole.carrot.service.AuUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
public class AuLoginController {

    @Autowired
    private AuUserService userService;

    @PostMapping
    @Operation(description="登录")
    public String login(@RequestBody @Validated LoginVO vo){

        return "";
    }

}
