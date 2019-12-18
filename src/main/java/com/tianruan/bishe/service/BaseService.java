package com.tianruan.bishe.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Andon on 2017/9/20.
 */
public class BaseService {
    private final static Logger logger = LoggerFactory.getLogger(BaseService.class);
    public String path = "http://lisheng-aricle.oss-cn-beijing.aliyuncs.com";
    public static String ali_endpoint = "oss-cn-beijing.aliyuncs.com";
    public static String ali_accesskey_id = "LTAIqKkqq1qxxp6V";
    public static String ali_accesskey_secret = "zrtMiZu72pwt1SFA8J1HnshhdXp5O1";
    public static String ali_logstorage = "lisheng-aricle";
    /**
     * 微信支付 APPID
     */
    public String WXPAYAPPID = "wx74808915eae21ef7";//有需文印Appid
    /**
     * 微信支付 密约
     */
    public String WXPAYSECRET = "493874f18e91e273fbf643cd9bea4de0";//有需文印AppSecret
    /**
     * 微信支付 商户号
     */
    public static String WXPAYMCHID = "1536452691";//有需文印

    /**
     * 微信支付 商户Key
     */
    public static String WXPAYKEY = "20190920youxuwenyinQAEHGFtYKMdsw";
    /**
     * 微信支付 APPID
     */
    public String WXPAYTRADETYPE;
    /**
     * 微信支付 回掉地址
     */
    public String WXPAYNOTIFYURL= "https://fqapi.lishengjt.com/api/wepay_codeUrl";
    public String WXPAYNOTIFYURLMEMBER = "https://fqapi.lishengjt.com/api/wepay_codeUrl_topUp";
    public String WXPAYNOTIFYURLSHOPPINGMALL = "https://fqapi.lishengjt.com/api/wepay_codeUrl_shopping_mall";
    public String WXPAYNOTIFYURLADMISSIONTICKET = "https://fqapi.lishengjt.com/api/payCallback_admission_ticket";





    /**
     * 微信支付 证书
     */
    public String WXPAYCERT;
    /**
     * 微信支付 证书
     */
    public String WXPAYREDIRECTURL;
    /**
     * 微信支付
     */
    public String WXPAYSCOPE = "snsapi_userinfo";
    /**
     * 微信支付
     */
    public String WXPAYCODEAPI = "https://open.weixin.qq.com/connect/oauth2/authorize?";
    /**
     * 微信支付
     */
    public String WXPAYACCESSTOKENURL;
    /**
     * 微信支付
     */
    public String WXPAYGRANTTYPE;
//============================================一下物流才用得到============
    /**
     * IOT 公共服务 APPID
     */
    @Value("${iot.sms.app.id}")
    public String IOTSMSAPPID;
    /**
     * IOT 公共服务 SECRET
     */
    @Value("${iot.sms.app.secret}")
    public String IOTSMSSECRATE;
    /**
     * IOT 公共服务 网关
     */
    @Value("${iot.sms.getway}")
    public String IOTSMSGETWAY;


}
