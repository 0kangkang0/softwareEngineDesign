package com.kangkang.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

@Data
@Repository
@ConfigurationProperties(prefix = "server")
public class FileInfo {
    private String filePath;
}
