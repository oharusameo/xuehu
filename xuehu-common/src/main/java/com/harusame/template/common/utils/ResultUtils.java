package com.harusame.template.common.utils;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.harusame.template.domain.pojo.Result;

public class ResultUtils {


    public static <T> T getResult(Result result, Class<T> clazz) {
        return new JsonMapper().convertValue(result, clazz);
    }
}
