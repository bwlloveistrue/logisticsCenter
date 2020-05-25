package com.util.FileldsUtil;

/**
 * Created by Administrator on 2020/5/21.
 */
import com.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 会议字段处理
 * User: lmx
 * Date: 2017-5-22

 */
public class FieldUtil {

    public static final String INPUT = "INPUT";
    public static final String INT = "INPUTNUMBER";
    public static final String FLOAT = "INPUTNUMBER";
    public static final String TEXTAREA = "TEXTAREA";
    public static final String BROWSER = "BROWSER";
    public static final String DATE = "DATE";
    public static final String CHECKBOX = "CHECKBOX";
    public static final String SELECT = "SELECT";
    public static final String SELECTPAGE = "SELECTPAGE";
    public static final String ATTACHEMENT = "ATTACHEMENT";
    public static final String HYPERLINK = "HYPERLINK";
    public static final String SELECT_LINKAGE = "SELECT_LINKAGE";
    public static final String DATEPICKER = "DATEPICKER";
    public static final String TIMEPICKER = "TIMEPICKER";
    public static final String SWITCH = "SWITCH";
    public static final String COLORPICKER = "COLORPICKER";
    public static final String PERIOD = "PERIOD";
    public static final String TIMERANGEPICKER = "TIMERANGEPICKER";
    public static final String TAGGROUP = "TAGGROUP";
    /**
     * 数字框
     */
    public static final String INPUTNUMBER = "INPUTNUMBER";

    /**
     *
     * @param htmlType
     * @return
     */
    public ConditionType getConditionType(String htmlType){
        return this.getConditionType(Utils.getIntValue(htmlType,0));
    }
    /**
     *
     * @param htmlType
     * @return
     */
    public ConditionType getConditionType(int htmlType){

//		INPUT : 文本输入框 SELECT : 下拉选择框 DATE ： 日期格式 DATEPICKER ： 日期格式 不带区间 
//		RANGEPICKER : 日期区间 BROWSER :浏览框 SELECT_LINKAGE ：下拉选择框联动 INPUT_INTERVAL :input 区间 SCOPE : input 区间 
//		CHECKBOX:check框 SWITCH:switch开关 TIMEPICKER ：时间 TIME_INTERVAL ：时间区间 DATE_INTERVAL : 日期区间 
//		CUSTOM : 自定义组件(流程自定义查询相关) TEXTAREA : 多行文本 RESOURCEIMG : 

        if(htmlType!=0){
            switch (htmlType){
                case 1:
                    return ConditionType.INPUT;
                case 2:
                    return ConditionType.TEXTAREA;
                case 3:
                    return ConditionType.BROWSER;
                case 4:
                    return ConditionType.CHECKBOX;
                case 5:
                    return ConditionType.SELECT;
                case 6:
                    return ConditionType.SELECT;
            }
        }
        return ConditionType.SELECT;
    }

    /**
     * 会议构建字段的公共方法
     * @param ConditionType
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    private static  Map getPubFormItem(String ConditionType,String fieldName, String fieldLabel, String value, int viewAttr){
        Map<String, Object> formItem = new HashMap<String, Object>();
        List<String> domKeyList = new ArrayList<String>();
        domKeyList.add(fieldName);
        formItem.put("domkey", domKeyList);
        formItem.put("label", fieldLabel);
        formItem.put("colSpan", 1);
        formItem.put("labelcol", 6);
        formItem.put("fieldcol", 11);
        formItem.put("formItemType", ConditionType);
        formItem.put("conditionType", ConditionType);
        formItem.put("value", value == null ? "" : value);
        formItem.put("viewAttr", viewAttr);
        if(viewAttr==3){
            List rulesList = new ArrayList();
            Map requireMap = new HashMap();
            requireMap.put("required",true);
            requireMap.put("message","请填写"+fieldLabel);
            formItem.put("rules", "required");
        }
        return formItem;
    }

    /**
     * input
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForInput(String fieldName, String fieldLabel, String value, int viewAttr) {
        return getPubFormItem(INPUT,fieldName, fieldLabel, value, viewAttr);
    }

    /**
     * input
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForeculiar(String fieldName, String fieldLabel, String value, int viewAttr) {
        return getPubFormItem(INPUT,fieldName, fieldLabel, value, viewAttr);
    }
    



    /**
     * input
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForInt(String fieldName, String fieldLabel, String value, int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(INT,fieldName, fieldLabel, "", viewAttr);
        formItem.put("value", Utils.getIntValue(value,0));
        return formItem;
    }

    /**
     * input
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForFloat(String fieldName, String fieldLabel, String value, int viewAttr,int precision) {
        Map<String, Object> formItem = getPubFormItem(FLOAT,fieldName, fieldLabel, "", viewAttr);
        formItem.put("value", Utils.getDoubleValue(value,0));
        if(precision == 1){
            formItem.put("step", 0.1);
        }else if(precision == 2){
            formItem.put("step", 0.01);
        }else if(precision == 3){
            formItem.put("step", 0.001);
        }else if(precision == 4){
            formItem.put("step", 0.0001);
        }
        formItem.put("precision", precision);
        return formItem;
    }


    /**
     * input
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForInput18(String fieldName, String fieldLabel, String value, int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(INPUT,fieldName, fieldLabel, value, viewAttr);
        formItem.put("fieldcol", 18);
        return formItem;
    }

    /**
     * 封装数字输入框
     * @param fieldName 字段id
     * @param fieldLabel 字段名标签
     * @param value 当前值
     * @param min 输入数字最小值
     * @param max 输入数字最大值
     * @param viewAttr 编辑权限  1：只读，2：可编辑， 3：必填
     * @return
     */
    public  static Map<String, Object> getFormItemForInputNumber(String fieldName, String fieldLabel, String value,int min,int max, int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(INPUTNUMBER,fieldName, fieldLabel, value, viewAttr);
        formItem.put("min", min);
        formItem.put("max", max);
        return formItem;
    }

    /**
     * textarea
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForTextArea(String fieldName, String fieldLabel, String value, int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(TEXTAREA,fieldName, fieldLabel, value, viewAttr);
        if(viewAttr==2||viewAttr==3){
            formItem.put("hasBorder", true);
        }
        return formItem;
    }


    /**
     * date
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForDate(String fieldName, String fieldLabel, String value,int viewAttr,boolean showTime) {
        Map<String, Object> formItem = getPubFormItem(DATEPICKER,fieldName, fieldLabel, value, viewAttr);
        if(showTime){
            formItem.put("format","yyyy-MM-dd HH:mm");
            formItem.put("mode","datetime");
            formItem.put("showTime",true);
        }
        return formItem;
    }

    /**
     * date
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForSelectDate(String fieldName, String fieldLabel, String value,int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(DATE,fieldName, fieldLabel, value, viewAttr);
        return formItem;
    }


    /**
     * time
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForTime(String fieldName, String fieldLabel, String value,int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(TIMEPICKER,fieldName, fieldLabel, value, viewAttr);
        return formItem;
    }

    /**
     * time
     * @param fieldNames
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForTime(String[] fieldNames, String fieldLabel, String[] value,int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(TIMEPICKER, "", fieldLabel, "", viewAttr);
        List<String> domKeyList = new ArrayList<String>();
        for (String fileName : fieldNames) {
            domKeyList.add(fileName);
        }
        formItem.put("domkey", domKeyList);
        formItem.put("value", value);
        return formItem;
    }

    /**
     * TIMERANGEPICKER
     * @param fieldNames
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForTimeRangePicker(String[] fieldNames, String fieldLabel, String[] value,int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(TIMERANGEPICKER, "", fieldLabel, "", viewAttr);
        List<String> domKeyList = new ArrayList<String>();
        for (String fileName : fieldNames) {
            domKeyList.add(fileName);
        }
        formItem.put("domkey", domKeyList);
        formItem.put("startDate", value[0]);
        formItem.put("endDate", value[1]);
        formItem.put("value", value);
        return formItem;
    }

    /**
     * 提醒时间特殊处理
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getRemindItem(String fieldName, String fieldLabel, List value, int viewAttr) {
        Map<String, Object> formItem = getPubFormItem("","", fieldLabel, "", viewAttr);
        List<String> domKeyList = new ArrayList<String>();
        domKeyList = Utils.TokenizerString(fieldName, ",");
        List formTypeList =new ArrayList();
        formTypeList.add(CHECKBOX);
        formTypeList.add(INPUT);
        formTypeList.add(INPUT);
        formItem.put("value", value);
        formItem.put("domkey", domKeyList);
        formItem.put("formItemType", formTypeList);
        formItem.put("conditionType", formTypeList);
        return formItem;
    }

    /**
     * checkbox
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForCheckbox(String fieldName, String fieldLabel, String value, int viewAttr) {
        Map<String, Object> formItem = getPubFormItem(CHECKBOX, "", fieldLabel, value, viewAttr);
        List<String> domKeyList = new ArrayList<String>();
        domKeyList = Utils.TokenizerString(fieldName, ",");
        formItem.put("domkey", domKeyList);
        return formItem;
    }

    /**
     * switch
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @return
     */
    public static Map<String, Object> getFormItemForSwitch(String fieldName, String fieldLabel, String value, int viewAttr) {
        Map<String, Object> formItem =getPubFormItem(SWITCH, "", fieldLabel, value, viewAttr);
        List<String> domKeyList = new ArrayList<String>();
        domKeyList = Utils.TokenizerString(fieldName, ",");
        formItem.put("domkey", domKeyList);
        return formItem;
    }

    /**
     * select
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @param optionList
     * @return
     */
    public static Map<String, Object> getFormItemForSelect(String fieldName, String fieldLabel, String value, int viewAttr,int detailtype,List<SearchConditionOption> optionList) {

        return getFormItemForSelect(fieldName,fieldLabel,value,viewAttr,detailtype,optionList,false);
    }

    /**
     * select
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @param optionList
     * @return
     */
    public static Map<String, Object> getFormItemForSelect(String fieldName, String fieldLabel, String value, int viewAttr,int detailtype,List<SearchConditionOption> optionList, boolean multiple) {
        Map<String, Object> formItem = getPubFormItem(SELECT, fieldName, fieldLabel, value, viewAttr);
        for(SearchConditionOption opt :optionList){
            if((","+value+",").indexOf(","+opt.getKey()+",")>-1){
                opt.setSelected(true);
            }
        }
        formItem.put("options", optionList);
        formItem.put("detailtype", detailtype);
        formItem.put("value", value);
        formItem.put("multiple", multiple);
        return formItem;
    }

    /**
     * select
     * @param fieldName
     * @param fieldLabel
     * @param viewAttr
     * @param optionList
     * @return
     */
    public static Map<String, Object> getFormItemForSelect(String fieldName, String fieldLabel, int viewAttr,int detailtype,List<Map> optionList) {
        Map<String, Object> formItem = getPubFormItem(SELECT, fieldName, fieldLabel, "", viewAttr);
        formItem.put("options", optionList);
        formItem.put("detailtype", detailtype);
        formItem.remove("value");
        return formItem;
    }

    /**
     * select
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @param optionList
     * @return
     */
    public static Map<String, Object> getFormItemForSelect2(String fieldName, String fieldLabel,String value, int viewAttr,int detailtype,List<Map> optionList) {
        Map<String, Object> formItem = getPubFormItem(SELECT, fieldName, fieldLabel, value, viewAttr);
        formItem.put("options", optionList);
        formItem.put("detailtype", detailtype);
        return formItem;
    }

    /**
     * select
     * @param fieldName
     * @param fieldLabel
     * @param value
     * @param viewAttr
     * @param optionList
     * @return
     */
    public static Map<String, Object> getFormItemForSelectPage(String fieldName, String fieldLabel, String value, int viewAttr,int detailtype,List<SearchConditionOption> optionList,boolean multiChoose) {
        Map<String, Object> formItem = getPubFormItem(SELECTPAGE, fieldName, fieldLabel, value, viewAttr);
        for(SearchConditionOption opt :optionList){
            if((","+value+",").indexOf(","+opt.getKey()+",")>-1){
                opt.setSelected(true);
            }
        }
        formItem.put("options", optionList);
        formItem.put("detailtype", detailtype);
        formItem.put("value", value);
        Map otherParams = new HashMap();
        otherParams.put("multiChoose", multiChoose);
        formItem.put("otherParams",otherParams);
        return formItem;
    }

    public static Map<String, Object> getFormItemForColorPicker(String fieldName,String fieldLabel, String value) {
        Map<String, Object> formItem = getPubFormItem(COLORPICKER, fieldName, fieldLabel, value, 1);
        formItem.remove("viewAttr");
        return formItem;
    }

    public static Map<String, Object> getFormItemForPeriod(String[] fieldName,String fieldLabel, int[] value) {
        return getFormItemForPeriod(fieldName,fieldLabel, value,new int[]{0,23});
    }

    public static Map<String, Object> getFormItemForPeriod(String[] fieldName,String fieldLabel, int[] value,int[] valueConf) {
        Map<String, Object> formItem = getPubFormItem(PERIOD, "", fieldLabel, "", 1);
        formItem.put("value", value);
        formItem.put("domkey", fieldName);
        formItem.put("min",valueConf[0] );
        formItem.put("max",valueConf[1] );
        formItem.remove("viewAttr");
        return formItem;
    }

    public static Map<String, Object> getFormItemForTagGroup (String[] fieldName,String fieldLabel,int viewAttr, String value){
        List values = new ArrayList();
        String [] arr = value.split(",");
        for(int i = 0 ;i<arr.length;i++){
            Map m = new HashMap();
            String val = arr[i];
            if(!val.isEmpty()){
                m.put("id", val);
                m.put("name", val);
                values.add(m);
            }
        }

        Map<String, Object> formItem = getPubFormItem(TAGGROUP, "", fieldLabel, "", viewAttr);
        formItem.put("datas", values);
        formItem.put("hasBrowser",false);
        formItem.put("hasOps",false);
        formItem.put("splitChar", ",");
        formItem.remove("value");
        formItem.put("domkey", fieldName);
        return formItem;
    }

    /**
     * 返回imgfileid
     * 如果要返回 docid  /api/workflow/reqform/docUpload 需要单独写
     * @param fieldName
     * @param fieldLabel
     * @param category
     * @param maxSize
     * @param limitType
     * @param listType
     * @param viewAttr
     * @param value
     * @return
     */
    public static Map<String, Object> getFormItemForUpload (String fieldName,String fieldLabel,String category,int maxSize,String limitType,String listType,int viewAttr, String value){
        Map<String, Object> formItem = new HashMap<String, Object>();
        formItem.put("uploadUrl", "/api/doc/upload/uploadFile?category="+category);
        formItem.put("category", category);
        formItem.put("maxUploadSize", maxSize);
        formItem.put("limitType", limitType);
        formItem.put("listType", listType);
        formItem.put("autoUpload", false);
        formItem.put("conditionType", "UPLOAD");
        formItem.put("label", fieldLabel);
        formItem.put("colSpan", 1);
        formItem.put("labelcol", 6);
        formItem.put("fieldcol", 11);
        formItem.put("domkey", new String[]{fieldName});
        formItem.put("viewAttr", viewAttr);
//        formItem.put("datas", datas);
        return formItem;
    }

    /**
     * 返回docid
     * 如果要返回 docid  /api/workflow/reqform/docUpload 需要单独写
     * @param fieldName
     * @param fieldLabel
     * @param category
     * @param maxSize
     * @param limitType
     * @param listType
     * @param viewAttr
     * @param value
     * @return
     */
    public static Map<String, Object> getFormItemForUploadDoc (String fieldName,String fieldLabel,String category,Double maxSize,String limitType,String listType,int viewAttr, String value){

        List<Object> datas=null;
        Map<String, Object> formItem = new HashMap<String, Object>();
        formItem.put("uploadUrl", "/api/workflow/reqform/docUpload?category="+category);
        formItem.put("category", category);
        formItem.put("maxUploadSize", maxSize);
        formItem.put("limitType", limitType);
        formItem.put("listType", listType);
        formItem.put("autoUpload", false);
        formItem.put("conditionType", "UPLOAD");
        formItem.put("label", fieldLabel);
        formItem.put("colSpan", 1);
        formItem.put("labelcol", 6);
        formItem.put("fieldcol", 11);
        formItem.put("domkey", new String[]{fieldName});
        formItem.put("viewAttr", viewAttr);
        //formItem.put("datas", datas);
        return formItem;
    }

    /**
     * 返回docid
     * 如果要返回 docid  /api/workflow/reqform/docUpload 需要单独写
     * @param fieldName
     * @param fieldLabel
     * @param category
     * @param maxSize
     * @param minSize
     * @param limitType
     * @param listType
     * @param viewAttr
     * @param value
     * @return
     */
    public static Map<String, Object> getFormItemForUploadDoc (String fieldName,String fieldLabel,String category,Double maxSize,Double minSize,String limitType,String listType,int viewAttr, String value){

        List<Object> datas=null;
        Map<String, Object> formItem = new HashMap<String, Object>();
        formItem.put("uploadUrl", "/api/workflow/reqform/docUpload?category="+category);
        formItem.put("category", category);
        formItem.put("maxUploadSize", maxSize);
        formItem.put("mixUploadSize", minSize);
        formItem.put("limitType", limitType);
        formItem.put("listType", listType);
        formItem.put("autoUpload", false);
        formItem.put("conditionType", "UPLOAD");
        formItem.put("label", fieldLabel);
        formItem.put("colSpan", 1);
        formItem.put("labelcol", 6);
        formItem.put("fieldcol", 11);
        formItem.put("domkey", new String[]{fieldName});
        formItem.put("viewAttr", viewAttr);
        //formItem.put("datas", datas);
        return formItem;
    }

    /**
     * 返回docid
     * 如果要返回 docid  /api/workflow/reqform/docUpload 需要单独写
     * @param fieldName
     * @param fieldLabel
     * @param category
     * @param maxSize
     * @param minSize
     * @param limitType
     * @param listType
     * @param viewAttr
     * @param value
     * @return
     */
    public static Map<String, Object> getFormItemForUploadImage (String fieldName,String fieldLabel,String category,Double maxSize,Double minSize,String limitType,String listType,int viewAttr, String value){

        List<Object> datas=null;
        Map<String, Object> formItem = new HashMap<String, Object>();
        formItem.put("uploadUrl", "/api/workflow/reqform/docUpload?category="+category+"&listType=img");
        formItem.put("category", category);
        formItem.put("maxUploadSize", maxSize);
        formItem.put("mixUploadSize", minSize);
        formItem.put("limitType", limitType);
        formItem.put("listType", listType);
        formItem.put("autoUpload", false);
        formItem.put("conditionType", "UPLOAD");
        formItem.put("label", fieldLabel);
        formItem.put("colSpan", 1);
        formItem.put("labelcol", 6);
        formItem.put("fieldcol", 11);
        formItem.put("domkey", new String[]{fieldName});
        formItem.put("viewAttr", viewAttr);
        //formItem.put("datas", datas);
        return formItem;
    }

    public static Map getRightMenuCfg(String isControl,String isTop,String icon,String name,String type){
        Map m = new HashMap();
        m.put("isControl", isControl);
        m.put("isTop", isTop);
        m.put("menuFun", "");
        m.put("menuIcon", icon);
        m.put("menuName", name);
        m.put("type", type);
        return m;
    }
    
}
