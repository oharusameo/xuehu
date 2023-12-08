package com.harusame.template.common.utils;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.crypto.digest.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FileUtils {

    public static List<String> imgTypeList = Arrays.asList("jpg", "jpeg", "png", "gif");

    public static void checkImageType(MultipartFile image) {
        try (InputStream inputStream = image.getInputStream()) {
            String type = FileTypeUtil.getType(inputStream);
            if (!imgTypeList.contains(type)) {
                throw new RuntimeException("图片类型不正确");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkImageType(InputStream inputStream) {
        String type = FileTypeUtil.getType(inputStream);
        if (!imgTypeList.contains(type)) {
            throw new RuntimeException("图片类型不正确");
        }
    }

    public static String getMD5Hash(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            return DigestUtil.md5Hex(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getMD5Hash(InputStream inputStream) {
        return DigestUtil.md5Hex(inputStream);
    }
}
