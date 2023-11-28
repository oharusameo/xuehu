
package com.harusame.template.common.utils;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class TokenUtils {

    /**
     * 登录后返回token
     *
     * @param loginId   存入token的参数
     * @param extraData 额外的参数
     * @return token
     */

    public String loginAndGetToken(String loginId, Map<String, Object> extraData) {
        StpUtil.login(loginId, SaLoginConfig.setExtraData(extraData));
        return StpUtil.getTokenValue();
    }


    /**
     * 登录后返回token
     *
     * @param loginId 存入token的参数
     * @return token
     */

    public String loginAndGetToken(Object loginId) {
        StpUtil.login(loginId);
        return StpUtil.getTokenValue();
    }


    /**
     * 获取用户id
     *
     * @return userId
     */

    public String getUserIdFromHeader() {
        String token = getTokenFromHeader();
        return (String) StpUtil.getLoginIdByToken(token);
    }


    /**
     * 从token中获取json,并转为对象
     *
     * @param clazz 需要转换的对象
     * @param <T>   object
     * @return <T> object
     */

    public <T> T getObjectFromHeader(Class<T> clazz) {
        String token = getTokenFromHeader();
        JSON json = JSONUtil.parse(StpUtil.getLoginIdByToken(token));
        return json.toBean(clazz);
    }



/**
     * 根据传入的key获取token中的额外参数
     *
     * @param key key
     * @return extraValue
     */
    public String getExtraFromHeader(String key) {
        String token = getTokenFromHeader();
        return (String) StpUtil.getExtra(token, key);
    }


    /**
     * 获取token中多个额外参数
     *
     * @param keyMaps keyMaps
     * @return map
     */

    public Map<String, Object> getExtraFromHeader(Map<String, Object> keyMaps) {
        String token = getTokenFromHeader();
        HashMap<String, Object> extraMaps = new HashMap<>();
        for (String key : keyMaps.keySet()) {
            Object extra = StpUtil.getExtra(token, key);
            extraMaps.put(key, extra);
        }
        return extraMaps;
    }


    public String getTokenFromHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        return request.getHeader("token");
    }


}

