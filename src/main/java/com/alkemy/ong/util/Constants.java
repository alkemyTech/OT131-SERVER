package com.alkemy.ong.util;

public final class Constants {

  public static final String REQ_GET_MAPP_ACTIVITIES = "/activities";
	public static final String REQ_GET_MAPP = "/organizations"; 
	public static final String POINT_GET_MAPP = "/public/{name}";
	public static final String ENTITY_NOT_FOUND = "ENTITY NOT FOUND";
	public static final String NAME_EXIST = "THE NAME OF THE ORGANIZATION ALREADY EXISTS";
	public static final String[] URL_AUTH_REQUEST = {"/", "/auth/**", "/public/**", "/js/**", "/css/**"};
	public static final String REQ_MAPP_CLASS_USER = "/auth";
	public static final String REQ_MAPP_POST_LOGIN_USER = "/login";
	public static final String REQ_MAPP_DELETE_LOGIN_USER = "/users/{id}";
        
        /*
        Internal Key for Token
        */
        public static final String SECRET_KEY = "SECRET_KEY";
	
        
  public Constants() {}


}

