package com.example.combotoolseller.config;

public class UserLogin {
    private static UserLogin userLogin;
    private  String code;

    public static UserLogin getUserLogin() {
        if (userLogin == null) {
            userLogin = new UserLogin();
        }
        return userLogin;
    }

    public UserLogin() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
