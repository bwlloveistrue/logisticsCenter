package com.util.wxPublic;

import java.util.List;

public class Template {
    // 消息接收方
    private String toUser;
    // 模板id
    private String templateId;
    // 模板消息详情链接
    private String url;
    // 参数列表
    private List<TemplateParam> templateParamList;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<TemplateParam> getTemplateParamList() {
        return templateParamList;
    }

    public void setTemplateParamList(List<TemplateParam> templateParamList) {
        this.templateParamList = templateParamList;
    }

}
