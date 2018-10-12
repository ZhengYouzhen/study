package com.zyz.learn;

/**
 * @author zyz
 * @date 2018/7/24
 */
public enum EnumTest {
    A(1, "one"),
    B(2, "two"),
    C(3, "three"),
    D(4, "four");

    EnumTest(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;
    private String msg;

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

//根据code获取对应的msg
    public static String msyByCode(Integer code) {
        for (EnumTest e : EnumTest.values()) {
            if(e.getCode().equals(code)) {
                return e.msg;
            }
        }
        return null;
    }
}
