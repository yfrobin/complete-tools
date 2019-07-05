package com.yf.completetools.constants;

/**
 * @Author: yefei
 * @Date: create in 2019-07-05
 * @Desc:
 */
public enum WeekEnum {
    Sunday(1, "周日"),
    Monday(2, "周一"),
    Tuesday(3, "周二"),
    Wednesday(4, "周三"),
    Thursday(5, "周四"),
    Friday(6, "周五"),
    Saturday(7, "周六");


    private Integer code;
    private String msg;

    WeekEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
