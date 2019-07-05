package com.lch.my_air_pan.model.user.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String id;

    private String useName;

    private String passpowd;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName == null ? null : useName.trim();
    }

    public String getPasspowd() {
        return passpowd;
    }

    public void setPasspowd(String passpowd) {
        this.passpowd = passpowd == null ? null : passpowd.trim();
    }
}