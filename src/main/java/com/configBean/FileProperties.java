package com.configBean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2020/5/21.
 */
@Component
@ConfigurationProperties(prefix = "file")
public class FileProperties {

    /**
     * 公众号appId
     */
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
