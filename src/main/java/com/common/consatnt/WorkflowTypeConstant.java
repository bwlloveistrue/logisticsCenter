package com.common.consatnt;

public enum  WorkflowTypeConstant {
    TAKER("预录", 1),
    APPORTION("分配", 2),
    RECEIPT_FINISH("回执完成", 3),
    GOODS_COMPLETE("装货完成", 4),
    SENDED("送达", 5),
    FINSHED("完成", 6);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private WorkflowTypeConstant(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }

}

