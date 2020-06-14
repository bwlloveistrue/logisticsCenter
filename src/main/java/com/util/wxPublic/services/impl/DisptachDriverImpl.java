package com.util.wxPublic.services.impl;

import com.cache.Cache;
import com.cache.CacheManager;
import com.common.CommonTransMethod;
import com.logisticscenter.model.MessageEntity;
import com.util.SpringUtil;
import com.util.Utils;
import com.util.wxPublic.Template;
import com.util.wxPublic.TemplateParam;
import com.util.wxPublic.services.PushServiceInterface;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.*;

/**
 * 装货到达提醒
 * Created by Administrator on 2020/6/14.
 */
public class DisptachDriverImpl implements PushServiceInterface{
    @Override
    public String pushMessage(String toUser, String templateId, Map params) {
        String msgId = "";
        Calendar today = Calendar.getInstance();
        String client = Utils.null2String(params.get("client")) ;
        String driver = Utils.null2String(params.get("driver"));
        String goodsType = Utils.null2String(params.get("goodsType"));
        String truckNumber = Utils.null2String(params.get("truckNumber"));
        String beginDate = Utils.null2String(params.get("beginDate"));
        String endDate = Utils.null2String(params.get("endDate"));
        StringBuffer place = new StringBuffer();
        Arrays.asList(goodsType.split(",")).stream().forEach(_item->{
            if(!place.toString().equals("")){
                place.append("\r\n");
            }
            place.append(CommonTransMethod.getGoodsTypeName(Utils.null2String(_item)));
            place.append("：");
            place.append(params.get("startPlace_"+_item));
            place.append("到");
            place.append(params.get("endPlace_"+_item));
        });
        StringBuffer remark = new StringBuffer("发票抬头:上海予程物流有限公司\r\n");
        remark.append("入库抬头：苏州执宝\r\n");
        remark.append("注意事项：自己开送货单  码单带回。装好货出库码单，吊费票，标牌拍照片。" +
                "请注意钢板平整度和锈程度。装车装整齐。\r\n");
        String currentdate = Utils.add0(today.get(Calendar.YEAR), 4) + "-" + Utils.add0(today.get(Calendar.MONTH) + 1, 2) + "-" + Utils.add0(today.get(Calendar.DAY_OF_MONTH), 2);
        String currenttime = Utils.add0(today.get(Calendar.HOUR_OF_DAY), 2) + ":" + Utils.add0(today.get(Calendar.MINUTE), 2) ;
        WxMpService wxMpService = SpringUtil.getBean(WxMpService.class);
        List<TemplateParam> templateParams = new ArrayList<>();
        TemplateParam templateParam = new TemplateParam("first","您好，您有新的订单，请及时处理！","");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword1",CommonTransMethod.getDriverName(driver),"");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("keyword2", place.toString(),"");
        templateParams.add(templateParam);


        templateParam = new TemplateParam("keyword3", beginDate ,"");
        templateParams.add(templateParam);
        templateParam = new TemplateParam("remark",remark.toString(),"#91d5ff");
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
