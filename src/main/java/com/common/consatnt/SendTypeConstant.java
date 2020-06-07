package com.common.consatnt;

public enum SendTypeConstant {
    ADMIN("管理员", 1),
    DRIVER("司机", 2),
    CLIENT("客户", 3);
    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private SendTypeConstant(String name, int index) {
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
