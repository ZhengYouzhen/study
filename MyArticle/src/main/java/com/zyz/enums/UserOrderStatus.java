package com.zyz.enums;


/**
 * @author zyz
 */
public enum UserOrderStatus {
    PAY_NO(0,"未付款"),
    PAY_YES(1,"已付款"),
    SHIPPED(2,"已发货"),
    DEAL_CLOSE(3,"交易完成"),
    HAVE_EVAL(4,"已评价"),
    FOR_A_REFUND(5,"待退款"),
    REFUNDED(6,"已退款"),
    REFUND_FAIL(7,"退款失败"),
    CANCEL(8,"取消");

    public static String getValue(int code) {
        for (UserOrderStatus c : UserOrderStatus.values()) {
            if (c.getCode() == code) {
                return c.msg;
            }
        }
        return null;
    }
    UserOrderStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    private int code;
    private String msg;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

}

