package com.tianruan.bishe.controller;

import com.tianruan.bishe.pojo.Users;
import com.tianruan.bishe.service.LoginService;
import com.tianruan.bishe.utils.Enum.ResultCodeEnum;
import com.tianruan.bishe.utils.ResponseUtil.ResponseMessage;
import com.tianruan.bishe.utils.ResponseUtil.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "登陆注册接口")
@RequestMapping("/administrators")
public class LoginController {

    private final Logger logger= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @ApiOperation(value = "注册人员", notes = "{\n" +
            "  \"account\": \"陈小春\",\n" +
            "  \"name\": \"陈小春\",\n" +
            "  \"password\": \"123456\",\n" +
            "  \"phone\": \"13652157272\",\n" +
            "  \"storeId\": 1,\n" +
            "  \"storeName\": \"有需文印\",\n" +
            "\"roleIdList\":[1,2]\n" +
            "}")
    @PostMapping("/register")
    public ResponseMessage register(@RequestBody Users users) {
        Integer insertRegisterAdministrator = loginService.register(users);
        if (StringUtils.isEmpty(insertRegisterAdministrator)) {
            return Result.error(ResultCodeEnum.ERROE_CODE.getValueCode(), "失败");
        }
        if (insertRegisterAdministrator == 0) {
            return Result.error(ResultCodeEnum.ERROE_CODE.getValueCode(), "该账户已存在");
        }
        return Result.success(ResultCodeEnum.SUCCESS_CODE.getValueCode(), "成功");
    }
}
