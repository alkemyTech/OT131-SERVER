package com.alkemy.ong.util;

public final class Constants {

    public static final String REQ_MAPP_ACTIVITIES = "/activities";
    public static final String REQ_MAPP_ORG = "/organizations";
    public static final String REQ_MAPP_CATEGORIES = "/categories";
    public static final String REQ_MAPP_NEWS = "/news";
    public static final String POINT_GET_MAPP = "/public/{name}";
    public static final String POINT_DELETE_MAPP = "/{id}";
    public static final String POINT_POST_MAPP = "/public";
    public static final String ENTITY_NOT_FOUND = "ENTITY NOT FOUND";
    public static final String NAME_EXIST = "THE NAME OF THE ORGANIZATION ALREADY EXISTS";
    public static final String[] URL_AUTH_REQUEST = {"/auth/register", "/auth/login"};
    public static final String[] AUTHENTICATED_ROLES = {"ROLE_ADMIN", "ROLE_USER"};
    public static final String AUTH_ONLY_ADMINS = "/auth/**";
    public static final String REQ_MAPP_CLASS_USER = "/auth";
    public static final String REQ_MAPP_POST_LOGIN_USER = "/login";
    public static final String REQ_MAPP_POST_REGISTER_USER = "/register";
    public static final String INCOMPLETE_PARAMETERS = "Incomplete Parameters";
    public static final String MAIL_ONG = "ong131Alkemy@gmail.com";
    public static final String REQ_MAPP_DELETE_LOGIN_USER = "/users/{id}";
    public static final String BAD_REQUEST = "Invalid parameter supplied";
    public static final String ERR_ACT_NOT_FOUND = "Requested activity was not found";
    public static final String ERR_AWS_NOT_FOUND = "You haven't selected any file to upload";
    public static final String ERR_AWS_NOT_SAVED = "The file couldn't be saved";
    public static final String REQ_MAPP_GET_AUTH_ME_USER = "/me";
    public static final String REQ_MAPP_ID = "/{id}";

    //ActivitiesController Docs
    public static final String ACTIVITIES_GET_INFO = "Get a list of all active activities.";
    public static final String ACTIVITIES_GET_INFO_OK = "Returning all activities...";
    public static final String ACTIVITIES_PUT_INFO = "Update an Activity related to sent id.";
    public static final String ACTIVITIES_PUT_INFO_OK = "Activity successfully updated.";
    public static final String ACTIVITIES_DELETE_INFO = "Delete an Activity related to sent id.";
    public static final String ACTIVITIES_DELETE_OK = "Activity successfully deleted.";
    public static final String ACTIVITIES_POST_INFO = "Create a new Activity.";
    public static final String ACTIVITIES_POST_OK = "Activity successfully created.";




    /*
    Internal Key for Token
     */
    public static final String SECRET_KEY = "SECRET_KEY";

    public Constants() {
    }

}
