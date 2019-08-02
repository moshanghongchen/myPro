package com.lch.my_air_pan.model.user.entity;

import lombok.Data;

import java.io.Serializable;


@Data
public class User implements Serializable {
    private String id;

    private String userName;

    private String password;

    private static final long serialVersionUID = 1L;
}