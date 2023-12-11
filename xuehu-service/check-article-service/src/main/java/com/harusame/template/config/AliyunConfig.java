package com.harusame.template.config;

import com.aliyun.imageaudit20191230.Client;
import com.aliyun.teaopenapi.models.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AliyunConfig {
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.endPoint}")
    private String endPoint;

    @Bean
    public Client createClient() throws Exception {
        /*
          初始化配置对象com.aliyun.teaopenapi.models.Config
          Config对象存放AccessKeyId、AccessKeySecret、endpoint等配置
         */
        /*com.aliyun.teaopenapi.models*/
        Config config = new com.aliyun.teaopenapi.models.Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret)
                .setEndpoint(endPoint);
        // 访问的域名
//        config.endpoint = endPoint;
        return new Client(config);
    }
}
