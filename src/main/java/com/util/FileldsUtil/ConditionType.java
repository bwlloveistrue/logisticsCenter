package com.util.FileldsUtil;

/**
 * Created by Administrator on 2020/5/21.
 */
public enum ConditionType {
    /**
     * INPUT : 文本输入框
     * SELECT : 下拉选择框
     * DATE ： 日期格式E8
     * DATEPICKER ： 日期格式 不带区间
     * TIMEPICKER ：时间格式 不带区间
     * RANGEPICKER : 日期区间
     * BROWSER :浏览框
     * SELECT_LINKAGE ：下拉选择框联动
     * INPUT_INTERVAL	:input 区间
     * SCOPE : input 区间
     * CHECKBOX:check框
     * SWITCH:switch开关
     * TIME_INTERVAL ：时间区间
     * DATE_INTERVAL : 日期区间
     * CUSTOM : 自定义组件(流程自定义查询相关)
     * TEXTAREA : 多行文本
     * RESOURCEIMG : 人力資源
     * COLORPICKER ：颜色选择器
     * CASCADER：级联组件
     * INPUTNUMBER:单个数字输入框
     * UPLOAD 附件上传
     * RICHTEXT 富文本
     * TAGGROUP 标签组
     * TEXT 文本
     * CASCADERCUSTOMFIELD CASCADER的子组件
     * PASSWORD 密码框
     * DESCRIPTION 描述类型
     * RADIO 单选框
     * DATEGROUP 日期格式
     */
    INPUT,
    TEXTAREA,
    SELECT,
    DATE,
    DATEPICKER,
    TIMEPICKER,
    RANGEPICKER,
    DATE_INTERVAL,
    TIME_INTERVAL,
    BROWSER,
    SELECT_LINKAGE,
    INPUT_INTERVAL,
    SCOPE,
    CHECKBOX,
    SWITCH,
    CUSTOM,
    RESOURCEIMG,
    COLORPICKER,
    CASCADER,
    INPUTNUMBER,
    CUSTOMFIELD,
    TIMERANGEPICKER,
    UPLOAD,
    RICHTEXT,
    TAGGROUP,
    TEXT,
    CASCADERCUSTOMFIELD,
    PASSWORD,
    DESCRIPTION,
    RADIO,
    DATEGROUP
    ;
}