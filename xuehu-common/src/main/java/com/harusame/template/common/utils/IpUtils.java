package com.harusame.template.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class IpUtils {
    private static final String reqUrl = "http://ip-api.com/json/";

    @SuppressWarnings({"all"})
    public static String getIpAddr() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
//        String ip = request.getRemoteAddr();
        String ip = "59.42.25.78";
        StringBuilder sb = new StringBuilder();
        sb.append(reqUrl).append(ip);
        String json = HttpUtil.get(sb.toString());
        Map addrMap = JSONUtil.toBean(json, Map.class);
        return (String) addrMap.get("city");
    }
}
