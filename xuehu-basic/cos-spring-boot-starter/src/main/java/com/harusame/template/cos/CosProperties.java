package com.harusame.template.cos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "cos")
@AllArgsConstructor
@NoArgsConstructor
public class CosProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucket;
}
