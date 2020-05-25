package com.util.wxPublic;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/21.
 */
@Component
public class WxMsgPush {

    /**
     * 微信公众号API的Service
     */
    private final WxMpService wxMpService;

    /**
     * 构造注入
     */
    public WxMsgPush(WxMpService wxMpService) {
        this.wxMpService = wxMpService;
    }


    /**
     * 发送微信模板信息
     *
     * @param openId 接受者openId
     * @return 是否推送成功
     */
    public Boolean SendWxMsg(String openId) {
        // 发送模板消息接口
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                // 接收者openid
                .toUser(openId)
                // 模板id
                .templateId("WfdkLK444OwiK4mMkkEaqlO2eLXcjRov8WbI-jdHd3s")
                // 模板跳转链接
                .url("/index.html")
                .build();
        // 添加模板数据
        templateMessage.addData(new WxMpTemplateData("first", "您好", "#FF00FF"))
                .addData(new WxMpTemplateData("keyword1", "程大涛", "#A9A9A9"))
                .addData(new WxMpTemplateData("keyword2", "想我没", "#FF00FF"))
                .addData(new WxMpTemplateData("keyword3", "哈哈哈", "#FF00FF"))
                .addData(new WxMpTemplateData("keyword4", "这又是个测试", "#FF00FF"))
                .addData(new WxMpTemplateData("keyword5", "这又是个测试", "#FF00FF"))
                .addData(new WxMpTemplateData("remark", "这还是个测试", "#000000"));
        String msgId = null;
        try {
            // 发送模板消息
            msgId = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return msgId != null;
    }

    public List<String> getOpenId(){
        List<String> openIdList = new ArrayList<String>();
        openIdList = recursionOpenId(openIdList,null);
        return openIdList;
    }

    /**
     * 获取人员IDs
     *
     * @return 获取人员openid
     */
    public List<String> recursionOpenId(List<String> openIdList,String nextOpenId) {
        try {
            WxMpUserList openList = wxMpService.getUserService().userList(null);
            for(String strId :openList.getOpenids()){
                openIdList.add(strId);
            }
            if(openList.getOpenids().size()>10000){
                recursionOpenId(openIdList,openList.getNextOpenid());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return openIdList;

    }
}