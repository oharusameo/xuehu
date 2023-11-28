package com.harusame.template.common.utils;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class IpUtils {
    public static String getIpAddr() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
//        String ip = request.getRemoteAddr();
        String ip = "59.42.25.78";
        String json = HttpUtil.get("http://ip-api.com/json/" + ip);
        @SuppressWarnings({"all"})
        Map addrMap = JSONUtil.toBean(json, Map.class);
        return (String) addrMap.get("city");
    }
}
