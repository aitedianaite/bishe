package com.tianruan.bishe.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Users {

    @ApiModelProperty(name = "userName", value = "用户名")
    private String userName;

    @ApiModelProperty(name = "passWord", value = "密码")
    private String passWord;

    @ApiModelProperty(name = "userName", value = "手机号")
    private String phoneCode;


}
