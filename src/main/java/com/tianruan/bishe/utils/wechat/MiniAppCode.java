package com.tianruan.bishe.utils.wechat;


import com.tianruan.bishe.service.BaseService;
import com.tianruan.bishe.utils.OtherUtil.DeleteFileUtil;
import com.tianruan.bishe.utils.OtherUtil.OSSUploadUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MiniAppCode extends BaseService {

    /* *//* 发送 post请求 用HTTPclient 发送请求*//*
    public static byte[] post(String URL, String json) {
        String obj = null;
        InputStream inputStream = null;
        Buffer reader = null;
        byte[] data = null;
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(URL);
        httppost.addHeader("Content-type", "application/json; charset=utf-8");
        httppost.setHeader("Accept", "application/json");
        try {
            StringEntity s = new StringEntity(json, Charset.forName("UTF-8"));
            s.setContentEncoding("UTF-8");
            httppost.setEntity(s);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                // 获取相应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    inputStream = entity.getContent();
                    data = readInputStream(inputStream);
                }
                return data;
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;
    }*/

    /**
     * 将流 保存为数据数组
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }


    /**
     * 获取店铺推荐二维码
     *
     * @param accessToken
     * @return
     */
    public String getminiqrQr(int userId, String accessToken) {
        RestTemplate rest = new RestTemplate();
        FileOutputStream os = null;
        String originalName = "推荐二维码"+".png";
        StringBuilder completeRoute = null;
        try {
            String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + accessToken;
            Map<String, Object> param = new HashMap<>();
            param.put("scene", userId);
//            param.put("page", "pages/register/register");//-------------上线后换路径
            param.put("width", 430);
            param.put("auto_color", false);
            Map<String, Object> line_color = new HashMap<>();
            line_color.put("r", 0);
            line_color.put("g", 0);
            line_color.put("b", 0);
            param.put("line_color", line_color);
//            LOG.info("调用生成微信URL接口传参:" + param);
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
            HttpEntity requestEntity = new HttpEntity(param, headers);
            ResponseEntity<byte[]> entity = rest.exchange(url, HttpMethod.POST, requestEntity, byte[].class, new Object[0]);
//            LOG.info("调用小程序生成微信永久小程序码URL接口返回结果:" + entity.getBody());
            byte[] result = entity.getBody();
            File newFile = new File(originalName);
            os = new FileOutputStream(newFile);
            os.write(result);
            os.close();
            //拼接/log
            StringBuilder path = new StringBuilder("log/");
            //获取时间戳
            Date fileDate = new Date();
            StringBuilder fileName = new StringBuilder(String.valueOf(fileDate.getTime()));
            //获取时间文件夹,并且与时间戳进行拼接
            SimpleDateFormat dateFormatShow = new SimpleDateFormat("yyyyMMdd");
            String date = (dateFormatShow.format(new Date()));
            StringBuilder newName = new StringBuilder(date);
            path.append(newName.toString());
            path.append("/");
            path.append(fileName);
            path.append("/");
            //获取文件后缀名
            path.append(originalName);
            String uploadPath = path.toString();
            // 上传到OSS /log/20190517/1218209821212.jpg
            OSSUploadUtil.uploadFile(ali_endpoint, ali_accesskey_id, ali_accesskey_secret, ali_logstorage, newFile, uploadPath);
            // 删除上传的文件
            File file1 = new File(originalName);
            String absolutePath = file1.getAbsolutePath();
            DeleteFileUtil.delete(absolutePath);
            //http://lisheng-aricle.oss-cn-beijing.aliyuncs.com/log/20190517/1218209821212.jpg
            //先拼接域名:
            StringBuilder yuming = new StringBuilder("https://lisheng-aricle.oss-cn-beijing.aliyuncs.com/");
            //再拼接/log
            completeRoute = yuming.append(uploadPath);
        } catch (Exception e) {
//            LOG.error("调用小程序生成微信永久小程序码URL接口异常",e);
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        assert completeRoute != null;
        return completeRoute.toString();
    }
}
