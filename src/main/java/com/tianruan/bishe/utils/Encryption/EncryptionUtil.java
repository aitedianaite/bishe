package com.tianruan.bishe.utils.Encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Andon on 2017/8/7.
 */
public class EncryptionUtil {
    private static String passwordConstSalt = "k%34.gEb6v[eUNt1E4K.KSpNt+xn~vzI";
    /**
     * 加密算法
     * @param password 密码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String Encode(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        String result = "";
        result = getSha1(EncoderByMd5(passwordConstSalt + password) + password);
        return result;
    }
    private static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update((str).getBytes("UTF-8"));
        byte b[] = messageDigest.digest();
        int i;
        StringBuffer buffer = new StringBuffer("");
        for(int offset = 0; offset < b.length; offset++){
            i = b[offset];
            if(i < 0){
                i += 256;
            }if(i < 16){
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(i));
        }
        return buffer.toString();
    }
    private static String getSha1(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
        mdTemp.update(str.getBytes("UTF-8"));

        byte[] md = mdTemp.digest();
        int j = md.length;
        char[] buf = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
            buf[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(buf);
    }
    /**
     * 32位MD5加密的大写字符串
     *
     * @param s
     * @return
     */
    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

        public static String encryptSHA1(String str){
            if(str == null || str.length() == 0){
                return null;
            }
            char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

            try {
                MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
                mdTemp.update(str.getBytes("UTF-8"));

                byte[] md = mdTemp.digest();
                int j = md.length;
                char buf[] = new char[j*2];
                int k = 0;
                for(int i =0;i<j;i++){
                    byte byteO = md[i];
                    buf[k++] = hexDigits[byteO >>> 4 & 0xf];
                    buf[k++] = hexDigits[byteO & 0xf];
                }
                return new String(buf);
            } catch (NoSuchAlgorithmException e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }
}
