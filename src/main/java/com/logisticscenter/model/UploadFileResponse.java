package com.logisticscenter.model;

import java.util.Map;

public class UploadFileResponse {
    private String name;
    private String uid;
    private String status;//uploading done error removed
    private String url;
    private String thumbUrl;
    private Long size;
    private Map response;
    private Map linkProps;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Map getResponse() {
        return response;
    }

    public void setResponse(Map response) {
        this.response = response;
    }

    public Map getLinkProps() {
        return linkProps;
    }

    public void setLinkProps(Map linkProps) {
        this.linkProps = linkProps;
    }
}
