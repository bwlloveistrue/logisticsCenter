package com.util;


import com.common.consatnt.ParamConstant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class ParamUtil {

    public static Map<String, Object> request2Map(HttpServletRequest request) {
//        // 参数Map
//        Map<String, String[]> properties = request.getParameterMap();
//        // 返回值Map
//        Map<String, Object> returnMap = new HashMap<String, Object>();
//        Iterator<Map.Entry<String, String[]>> entries = properties.entrySet().iterator();
//        Map.Entry entry;
//        String name = "";
//        Object value = null;
//        while (entries.hasNext()) {
//            entry = (Map.Entry) entries.next();
//            name = (String) entry.getKey();
//            Object valueObj = entry.getValue();
//            if (null == valueObj) {
//                value = null;
//            } else if (valueObj instanceof String[]) {
//                String[] values = (String[]) valueObj;
//                if (values.length == 1) {
//                    value = values[0];
//                } else {
//                    value = values;
//                }
//            } else {
//                value = valueObj.toString();
//            }
//            returnMap.put(name, value);
//        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        try{
            InputStream inputStream = request.getInputStream();
            StringBuilder stringBuilder = new StringBuilder();
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
            Gson gson = new Gson();
            returnMap =  gson.fromJson(stringBuilder.toString(), new TypeToken<Map<String, Object>>() {
            }.getType());
        }catch(Exception e){
            e.printStackTrace();
        }

        if(returnMap == null){
            returnMap = new HashMap<String, Object>();
        }
        //放入ip
        returnMap.put(ParamConstant.PARAM_IP, Utils.getIpAddr(request));
        returnMap.put(ParamConstant.REQUEST_HEADER_USER_AGENT,request.getHeader("user-agent"));
        return returnMap;
    }
}
