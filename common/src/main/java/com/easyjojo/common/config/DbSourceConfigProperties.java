package com.easyjojo.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app")
// 显式指定配置文件路径
@PropertySource(value = "classpath:application-common.properties", encoding = "UTF-8", ignoreResourceNotFound = true)
public class DbSourceConfigProperties {
    //默认启用的数据库 ID
    public String defaultDbId;
    //自动映射数组配置
    public List<DbConfigItem> databases = new ArrayList<>();

    @Data
    public static class DbConfigItem{
        private String id;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
    }
}
