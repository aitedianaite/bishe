package com.tianruan.bishe.utils.OtherUtil;


import com.aliyun.oss.OSSClient;
import com.tianruan.bishe.service.BaseService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/4.
 */
public class OSSUploadUtil extends BaseService {

    private static final String FALSE = "上传失败";

    /**
     * 图片上传
     */
    public static String uploadBlog(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            if (file != null) {
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
                //获取文件后缀名
                String filename3 = file.getOriginalFilename();
                String extName = filename3.substring(filename3.lastIndexOf("."));
                path.append(extName);
//                System.out.println(path);
                //http://47.95.222.85/log/20190517/1558074106768.png
                String yuming1 = path.toString();
                File newFile = new File(filename);
                FileOutputStream os = new FileOutputStream(newFile);
                os.write(file.getBytes());
                os.close();
                // 上传到OSS /log/20190517/1218209821212.jpg
                OSSUploadUtil.uploadFile(ali_endpoint, ali_accesskey_id, ali_accesskey_secret, ali_logstorage, newFile, yuming1);
                // 删除上传的文件
                File file1 = new File(filename);
                String s = file1.getAbsolutePath();
                DeleteFileUtil.delete(s);
                //先拼接域名:
                StringBuilder yuming = new StringBuilder("https://lisheng-aricle.oss-cn-beijing.aliyuncs.com/");
                //再拼接/log
                yuming.append(yuming1);
                return (yuming.toString());
            }
            return FALSE;
        } catch (Exception ex) {
            ex.printStackTrace();
            return FALSE;
        }
    }
    /**
     * 图片上传
     */
    public static String uploadBlog(@RequestParam("file") MultipartFile file, String pathPart) {
        String filename = file.getOriginalFilename();
        try {
            if (file != null) {
                //拼接/image
                StringBuilder path = new StringBuilder(pathPart);
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
                //获取文件后缀名
                String filename3 = file.getOriginalFilename();
                String extName = filename3.substring(filename3.lastIndexOf("."));
                path.append(extName);
                String yuming1 = path.toString();
                File newFile = new File(filename);
                FileOutputStream os = new FileOutputStream(newFile);
                os.write(file.getBytes());
                os.close();
                // 上传到OSS /log/20190517/1218209821212.jpg
                OSSUploadUtil.uploadFile(ali_endpoint, ali_accesskey_id, ali_accesskey_secret, ali_logstorage, newFile, yuming1);
                // 删除上传的文件
                File file1 = new File(filename);
                String s = file1.getAbsolutePath();
                DeleteFileUtil.delete(s);
                //先拼接域名:
                StringBuilder yuming = new StringBuilder("http://lisheng-aricle.oss-cn-beijing.aliyuncs.com/");
                //再拼接/log
                yuming.append(yuming1);
                return (yuming.toString());
            }
            return FALSE;
        } catch (Exception ex) {
            ex.printStackTrace();
            return FALSE;
        }
    }
    /**
     *
     * @MethodName: uploadFile
     * @param file
     */
    public static void uploadFile(String endpoint, String accessKeyId, String accessKeySecret, String storageName, File file, String name) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件
        ossClient.putObject(storageName, name,new File(String.valueOf(file)));
        // 关闭client
        ossClient.shutdown();
    }
    public static void deleteFile(String endpoint, String accessKeyId, String accessKeySecret, String storageName, String name) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件
        ossClient.deleteObject(storageName, name);
        // 关闭client
        ossClient.shutdown();
    }
}

