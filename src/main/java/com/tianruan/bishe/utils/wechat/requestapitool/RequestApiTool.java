package com.tianruan.bishe.utils.wechat.requestapitool;

import com.alibaba.fastjson.JSONObject;
import com.tianruan.bishe.service.BaseService;
import com.tianruan.bishe.utils.HttpTools.HttpResult;
import com.tianruan.bishe.utils.HttpTools.HttpTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/*通过code获取openid*/
@Configuration
@Component
public class RequestApiTool extends BaseService {

    @Autowired
    public HttpTool httpTool;
  /*  @Bean
    public RestTemplate httpTool() {
        return new RestTemplate();
    }*/
  //获取openId
    public  JSONObject  register(String code){
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpMethod method = HttpMethod.GET;
        HttpResult results = httpTool.doHttp("https://api.weixin.qq.com/sns/jscode2session?appid=wx74808915eae21ef7&secret=493874f18e91e273fbf643cd9bea4de0&js_code="+code+"&grant_type=authorization_code",
                method, header, null,null);
        //1、使用JSONObject把刺符传对象转换成json对象
        JSONObject jsonObject= JSONObject.parseObject(results.getResult());
        return jsonObject;
    }
//获取accessToken
    public  JSONObject  getAccessToken(){
        MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        HttpMethod method = HttpMethod.GET;
        HttpResult results = httpTool.doHttp("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx74808915eae21ef7&secret=493874f18e91e273fbf643cd9bea4de0",
                method, header, null,null);
        //1、使用JSONObject把刺符传对象转换成json对象
        JSONObject jsonObject= JSONObject.parseObject(results.getResult());
        return jsonObject;
    }
}
