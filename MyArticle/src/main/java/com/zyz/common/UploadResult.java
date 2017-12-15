package com.zyz.common;

public class UploadResult {

    private int code;
    private String msg;
    private String data;
    private String src;
    private String title;

    public UploadResult() {}

    public UploadResult(int code, String msg, String data, String src, String title) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.src = src;
        this.title  = title;
    }

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
