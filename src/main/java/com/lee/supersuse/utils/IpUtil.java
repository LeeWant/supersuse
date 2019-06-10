package com.lee.supersuse.utils;

import com.lee.supersuse.pojo.Ip;
import com.lee.supersuse.pojo.UserLoginView;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class IpUtil {
    public static Ip getIp(UserLoginView userLoginView,HttpServletRequest request) {
        Ip ip = new Ip();
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");

        String ipAddress = null;
        if (realIp == null) {
            if (forwarded == null) {
                ipAddress = remoteAddr;
            } else {
                ipAddress = remoteAddr + "/" + forwarded;
            }
        } else {
            if (realIp.equals(forwarded)) {
                ipAddress = realIp;
            } else {
                ipAddress = realIp + "/" + forwarded.replaceAll(", " + realIp, "");
            }
        }
        ip.setIpId(UUID.randomUUID().toString());
        ip.setUserId(userLoginView.getUserId());
        ip.setUserName(userLoginView.getName());
        ip.setIpAddress(ipAddress);
        return ip;
    }

}
