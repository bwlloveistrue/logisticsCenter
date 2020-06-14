package com.util.wxPublic.services.impl;

import com.cache.Cache;
import com.cache.CacheManager;
import com.logisticscenter.model.MessageEntity;
import com.util.SpringUtil;
import com.util.Utils;
import com.util.wxPublic.TemplateParam;
import com.util.wxPublic.services.PushServiceInterface;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 装货到达提醒
 * Created by Administrator on 2020/6/14.
 */
public class GoodsArriveImpl implements PushServiceInterface{
    @Override
    public String pushMessage(String toUser, String templateId, Map params) {
        String msgId = "";
        WxMpService wxMpService = SpringUtil.getBean(WxMpService.class);
        List<TemplateParam> templateParams = new ArrayList<>();
        TemplateParam templateParam = new TemplateParam("first","您好，您有新的订单！","");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword1","xxxxxxx","");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword2","xxxxxx","");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword3", Utils.null2String(params.get("client")) ,"");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword4","","");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword5","","");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("remark","11111111","");
        templateParams.add(templateParam);
        List<Cache> cacheList = CacheManager.getCacheListInfo("messageEntity_CACHE");
        String redirectUrl = "";
        for (int i = 0; i < cacheList.size(); i++) {
            MessageEntity messageEntity = (MessageEntity) cacheList.get(i).getValue();
            if(messageEntity.getMouldId().equals(templateId)){
                redirectUrl = messageEntity.getRedirectUrl();
                break;
            }
        }
        // 发送模板消息接口
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                // 接收者openid
                .toUser(toUser)
                // 模板id
                .templateId(templateId)
                // 模板跳转链接
                .url(redirectUrl)
                .build();
        // 添加模板数据

        templateParams.stream().forEach(_params->{
            templateMessage.addData(new WxMpTemplateData(_params.getName(), _params.getValue(), _params.getColor()));
        });
        try {
            // 发送模板消息
            msgId = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return msgId;
    }
}
