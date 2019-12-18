package com.tianruan.bishe.utils.HttpTools;


import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/*1.调用api请求通过code获得openid等信息*/
@Component
public class HttpTool {
    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    /**
     *
     * @param url 地址
     * @param httpMethod 请求方式
     * @param headers 头
     * @param body 入参
     * @param contentType
     * @return
     */
    public HttpResult doHttp(String url, HttpMethod httpMethod, MultiValueMap<String, String> headers, String body,
                             String contentType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType type = MediaType.APPLICATION_JSON_UTF8;
        try {
            type = MediaType.valueOf(contentType);
        } catch (Exception ex) {
        }

        httpHeaders.setContentType(type);
        if (headers != null && headers.size() > 0) {
            httpHeaders.putAll(headers);
        }
        HttpEntity<String> formEntity = null;
        if (body == null || body.length() < 1) {
            formEntity = new HttpEntity<String>(httpHeaders);
        } else {
            formEntity = new HttpEntity<String>(body, httpHeaders);
        }
        String responseBody = "";
        HttpStatus httpStatus = HttpStatus.OK;

        boolean ifSuccess = false;
        try {
            ResponseEntity<String> result = restTemplate.exchange(url, httpMethod, formEntity, String.class);
            httpStatus = result.getStatusCode();
            responseBody = result.getBody();
            if (result.getStatusCode() == HttpStatus.OK) {
                ifSuccess = true;
            }
        } catch (HttpClientErrorException ex) {
            httpStatus = ex.getStatusCode();
            responseBody = ex.getResponseBodyAsString();
        } catch (HttpServerErrorException ex) {
            httpStatus = ex.getStatusCode();
            responseBody = ex.getResponseBodyAsString();
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseBody = ex.getMessage();
        }
        long ts = System.currentTimeMillis();
        if (!ifSuccess) {
            HttpResult httpResult = HttpResult.fail(responseBody, httpStatus);
            httpResult.setTs(ts);
            return httpResult;
        } else {
            HttpResult httpResult = HttpResult.success(responseBody);
            httpResult.setTs(ts);
            return httpResult;
        }
    }


    /**liwenxuan：
     * use：公众号获取全局accesstoken
     * HttpsUtil方法https请求结果返回蔚json类型
     * @param Url http请求地址
     * @param Method http请求类型支持POST GET
     * @param Output
     * @return InputStream转换成JSONObject后返回
     * @throws Exception
     */
    public JSONObject HttpsUtil(String Url,String Method,String Output) throws Exception{
        JSONObject jsonObject = null;
        URL conn_url =  new URL(Url);
        HttpURLConnection conn = (HttpsURLConnection)conn_url.openConnection();
        conn.setRequestMethod(Method);
        conn.setReadTimeout(5000);
        conn.setConnectTimeout(5000);
        conn.connect();
        //output获取access_token是不会用到
        if(Output != null){
            OutputStream  outputstream =conn.getOutputStream();
            //字符集，防止出现中文乱码
            outputstream.write(Output.getBytes("UTF-8"));
            outputstream.close();
        }
        //正常返回代码为200
        if(conn.getResponseCode()==200){
            InputStream stream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(stream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            stream.close();
            conn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        }
        System.out.println(conn.getResponseCode());
        return jsonObject;
    }



}
