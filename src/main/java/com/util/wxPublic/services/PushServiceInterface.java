package com.util.wxPublic.services;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.Map;

/**
 * Created by Administrator on 2020/6/14.
 */
public interface PushServiceInterface {
    public String pushMessage(String toUser, String templateId, Map params);
}
