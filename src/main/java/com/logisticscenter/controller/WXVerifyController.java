package com.logisticscenter.controller;

import com.javabean.SystemInfoBean;
import com.util.wxPublic.WXPublicUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/8/29.
 */

@RestController
@RequestMapping(value = "/api/wxpublic")
public class WXVerifyController {
    /**
     *
     */
    private static final long serialVersionUID = 1L;



    /**
     * 微信验证
     * @return
     */
    @ResponseBody
    @GetMapping("/verify_wx_token")
    public String verify_wx_token(HttpServletRequest request , HttpServletResponse response){
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        try{
            if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
                return echostr;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
