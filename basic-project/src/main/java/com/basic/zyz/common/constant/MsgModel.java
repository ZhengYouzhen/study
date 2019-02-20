package com.basic.zyz.common.constant;
/**
 * 返回信息类
 * @author zyz
 * @date 2018/12/16
 */
public class MsgModel {

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回结果描述
     */
    private Object msg;

    /**
     * 返回内容
     */
    private Object body;

    /**
     * 用于分页统计数量
     */
    private Long total;


    public MsgModel() {

    }


    public MsgModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.body = "";
    }

    public MsgModel(String code, String msg, Object responsebody) {
        this.code = code;
        this.msg = msg;
        this.body = responsebody;
    }

    public MsgModel(ResultStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.body = "";
    }

    public MsgModel(ResultStatus status, Object responsebody) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.body = responsebody;
    }

    public MsgModel(String code, Object msg, Object body, Long total) {
        this.code = code;
        this.msg = msg;
        this.body = body;
        this.total = total;
    }

    public static MsgModel ok(Object responsebody) {
        return new MsgModel(ResultStatus.SUCCESS, responsebody);
    }

    public static MsgModel ok() {
        return new MsgModel(ResultStatus.SUCCESS);
    }

    public static MsgModel error(ResultStatus error) {
        return new MsgModel(error);
    }

    //自定义错误信息
    public static MsgModel error(String error) {
        return new MsgModel("9999", error);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
