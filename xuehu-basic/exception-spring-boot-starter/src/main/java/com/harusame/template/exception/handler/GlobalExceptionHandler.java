package com.harusame.template.exception.handler;


import cn.hutool.json.JSONUtil;
import com.harusame.template.domain.pojo.Result;
import com.harusame.template.exception.BusinessException;
import com.harusame.template.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.HashMap;
import java.util.List;

import static cn.hutool.http.HttpStatus.*;

@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        //获取异常相应结果
        BindingResult bindingResult = e.getBindingResult();
        //获取多个字段的异常信息
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        HashMap<String, String> messages = new HashMap<>();
        for (FieldError error : fieldErrors) {
            messages.put(error.getField(), error.getDefaultMessage());
        }
        String json = JSONUtil.toJsonStr(messages);
        log.info(json);
        return Result.error(HTTP_BAD_REQUEST, json);
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result exceptionHandler(BusinessException e) {
        log.error(e.getMessage());
        if (e.getStatusCode() == null) {
            return Result.error(HTTP_INTERNAL_ERROR, e.getMessage());
        }
        return Result.error(e.getStatusCode(), e.getMessage());
    }

    @ExceptionHandler(SystemException.class)
    @ResponseBody
    public Result exceptionHandler(SystemException e) {
        log.error(e.getMessage());
        if (e.getStatusCode() == null) {
            return Result.error(HTTP_INTERNAL_ERROR, e.getMessage());
        }
        return Result.error(e.getStatusCode(), e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public Result exceptionHandler(DuplicateKeyException e) {
        log.error(e.getMessage());
        return Result.error(HTTP_BAD_REQUEST, "该数据已存在，不允许重复添加");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.error(HTTP_FORBIDDEN, "网络不稳定");
    }

}


