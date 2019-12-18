package com.tianruan.bishe.utils.wechat;

import com.tianruan.bishe.service.BaseService;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class CertUtil extends BaseService {//用来加载证书


    /**
     * 加载证书
     */
    public static SSLConnectionSocketFactory initCert() throws Exception {
        FileInputStream instream = null;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        Resource resource = new ClassPathResource("certificate/apiclient_cert.p12");
        String Path= resource.getFile().getPath();
        instream = new FileInputStream(new File(Path));
        keyStore.load(instream, WXPAYMCHID.toCharArray());
        if (null != instream) {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,WXPAYMCHID.toCharArray()).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        return sslsf;
    }


}
