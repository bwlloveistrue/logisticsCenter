package com.util.wxPublic.pushFactory;

import com.cache.Cache;
import com.cache.CacheManager;
import com.logisticscenter.model.MessageEntity;
import com.util.Utils;
import com.util.wxPublic.services.PushServiceInterface;

import java.util.List;

/**
 * Created by Administrator on 2020/6/14.
 */
public class WechatPushFactory {
    private static PushServiceInterface instance = null;

    public static PushServiceInterface getInstance(String templateId) {

        String clazzName = "";
        List<Cache> cacheList = CacheManager.getCacheListInfo("messageEntity_CACHE");
        for (int i = 0; i < cacheList.size(); i++) {
            MessageEntity messageEntity = (MessageEntity) cacheList.get(i).getValue();
            if(messageEntity.getMouldId().equals(templateId)){
                clazzName = messageEntity.getClazzName();
                break;
            }
        }
        instance = getSyncClazzObject(clazzName);
        return instance;
    }

    /**
     * 获取同步接口类
     * @param clazzName
     * @return
     */
    public static PushServiceInterface getSyncClazzObject(String clazzName){
        PushServiceInterface obj=null;
        if(clazzName!=null&&!"".equals(clazzName)){
            try {
                obj=(PushServiceInterface)Class.forName(clazzName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
