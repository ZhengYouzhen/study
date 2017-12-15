package com.zyz.bean;

public class Permission {
    private Long id;

    private String permission;

    private String desZh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }

    public String getDesZh() {
        return desZh;
    }

    public void setDesZh(String desZh) {
        this.desZh = desZh == null ? null : desZh.trim();
    }
}