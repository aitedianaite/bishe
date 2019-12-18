package com.tianruan.bishe.utils.OtherUtil;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class ClientIPUtils {
	/**
	 * 获得请求ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request){
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotEmpty(ip)&&!"unKnown".equalsIgnoreCase(ip)){
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else
				return ip;
		}
		ip = request.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(ip)&&!"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}
}
