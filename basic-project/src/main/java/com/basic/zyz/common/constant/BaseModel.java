package com.basic.zyz.common.constant;
/**
 * 返回信息类
 * @author zyz
 * @date 2018/12/16
 */
public class BaseModel {

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
    private Object data;



    public BaseModel() {

    }


    public BaseModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = "";
    }

    public BaseModel(String code, String msg, Object responsedata) {
        this.code = code;
        this.msg = msg;
        this.data = responsedata;
    }

    public BaseModel(ResultStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.data = "";
    }

    public BaseModel(ResultStatus status, Object responsedata) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.data = responsedata;
    }

    public static BaseModel ok(Object data) {
        return new BaseModel(ResultStatus.SUCCESS, data);
    }

    public static BaseModel ok() {
        return new BaseModel(ResultStatus.SUCCESS);
    }

    public static BaseModel error(ResultStatus error) {
        return new BaseModel(error);
    }

    //自定义错误信息
    public static BaseModel error(String error) {
        return new BaseModel("9999", error);
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
