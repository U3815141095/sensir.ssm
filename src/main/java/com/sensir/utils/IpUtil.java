package com.sensir.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liny5 on 2016-7-1.
 */
public class IpUtil {

    private IpUtil(){}

    /**
     * 获取真实IP地址
     *
     * @param request
     * @return
     */
    public static String getRealIpAddr(HttpServletRequest request){
        String ip  =  request.getHeader( " x-forwarded-for " );
        if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {
            ip  =  request.getHeader( " Proxy-Client-IP " );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {
            ip  =  request.getHeader( " WL-Proxy-Client-IP " );
        }
        if (ip  ==   null   ||  ip.length()  ==   0   ||   " unknown " .equalsIgnoreCase(ip))  {
            ip  =  request.getRemoteAddr();
        }
        return ip;
    }
}
