package com.tianruan.bishe.utils.OtherUtil;

import java.io.File;

public class UploadUtils {
    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String IMG_PATH_PREFIX = "static/images/acceptanceImages";
    public final static String IMG_PATH_PREFIX_QRcode = "http://lisheng-aricle.oss-cn-beijing.aliyuncs.com/barcode/";

    public static File getImgDirFile(String imgPathPerfix){
        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String(imgPathPerfix);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }



    public final static String IMG_PATH_PREFIX_QRcodeCeshi = "static/images/QRcode";

    public static File getImgDirFileCeshi(String imgPathPerfix){
        // 构建上传文件的存放 "文件夹" 路径
        String fileDirPath = new String("huinong/src/main/resources/" + imgPathPerfix);

        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }
}
