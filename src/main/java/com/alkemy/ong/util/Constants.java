package com.alkemy.ong.util;

public final class Constants {
    
    //CONST LOGIN 
    public static final String URL_LOGIN = "/auth/login";
    public static final String URL_LOGOUT = "/auth/login";
    public static final String URL_LOGIN_SUCCESS = "/auth/login";
    public static final String URL_LOGIN_FAILURE = "/auth/login?error=false";
    public static final String NAME_PARAM_EMAIL = "useremail";
    public static final String NAME_PARAM_PASSWORD = "password";
    public static final String[] URL_AUTH_REQUEST = {"/", "/auth/**", "/public/**", "/js/**", "/css/**"};
    
}
