package com.wangwang.management.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "background.management")
public class SystemConfig {

    /**
     * 系统加密数据的密钥
     */
    private String secretKey;

}
