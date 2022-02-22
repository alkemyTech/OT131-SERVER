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
    public static final String[] SWAGGER_SECURITY = {"swagger-ui.html","/swagger-ui/**","/v3/api-docs/**"};
    public static final String[] URL_ORG_SECURITY = {"/organizations/public/**"};
    public static final String REQ_MAPP_GET_AUTH_ME_USER = "/me";
    public static final String REQ_MAPP_ID = "/{id}";

    public static final String REQ_MAPP_MEMBERS = "/members";
    public static final String GET_MAPP_LIST_MEMBERS = "/members";
    public static final String ERR_MEMBER_ALREADY_EXISTS = "Member already exists";
    public static final String ERR_FB_MEMBER_ALREADY_EXISTS = "There is already a member created with the Facebook entered";
    public static final String ERR_IG_MEMBER_ALREADY_EXISTS = "There is already a member created with the Instagram entered";
    public static final String ERR_LI_MEMBER_ALREADY_EXISTS = "There is already a member created with the Linkedin entered";
    public static final String MEMBERS_POST_INFO = "Create a new member";
    public static final String MEMBERS_CREATED_OK = "Member created successfully";
    public static final String MEMBERS_LIST_INFO = "Get a list of Members";
    public static final String MEMBERS_LIST_OK = "Returns a list of members";
    public static final String ERR_MEMBER_ALREADY_REMOVED ="The member no longer exists";
    public static final String MEMBERS_PUT_INFO = "Update a member";
    public static final String MEMBERS_UPDATE_OK = "Member update successfully";

    public static final String REQ_MAPP_TESTIMONIALS = "/testimonials";
    public static final String ERR_CONTACT_ALREADY_EXISTS = "The entered contact already exists";
    public static final String REQ_MAPP_CONTACTS = "/contacts";
    public static final String ERR_CONTACT_NOT_FOUND = "Requested contact was not found";
    public static final String ERR_USER_ALREADY_EXISTS = "User already exists";

    //ActivitiesController Docs
    public static final String ACTIVITIES_GET_INFO = "Get a list of all active activities.";
    public static final String ACTIVITIES_GET_INFO_OK = "Returning all activities...";
    public static final String ACTIVITIES_PUT_INFO = "Update an Activity related to sent id.";
    public static final String ACTIVITIES_PUT_INFO_OK = "Activity successfully updated.";
    public static final String ACTIVITIES_DELETE_INFO = "Delete an Activity related to sent id.";
    public static final String ACTIVITIES_DELETE_OK = "Activity successfully deleted.";
    public static final String ACTIVITIES_POST_INFO = "Create a new Activity.";
    public static final String ACTIVITIES_POST_OK = "Activity successfully created.";

    public static final String REQ_MAPP_SLIDES = "/slides";
    public static final String BAD_ORG_ID = "The organization was not found.";

    //SlidesController Docs
    public static final String SLIDES_POST_INFO = "Create a new Slide";
    public static final String SLIDES_POST_OK = "Slide successfully created.";
    public static final String SLIDES_GET_INFO = "Show list of Slides";
    //ContactsController Docs
    public static final String CONTACTS_POST_INFO = "Create a new contact";
    public static final String CONTACTS_CREATED_OK = "Contact created successfully";
    public static final String CONTACTS_DELETE_INFO = "Unsubscribe the contact entered by id";
    public static final String CONTACTS_DELETE_OK = "Contact unsubscribed successfully";


    public static final String NOT_DATA_DISPLAY = "No data to display";
    
    //Pagination
    public static final Integer PAGE_SIZE = 10;
    public static final String PAGE_NOT_FOUND = "The requested page doesn't exist";
    public static final String WRONG_PAGE_NUMBER = "Page number can't be less than 0";
    
    //News
    public static final String NEWS_PAGE_URL = "localhost:8080/news?page=";
    public static final String NEWS_PAGE_INFO = "Shows the paginated news of 10 elements";
    public static final String NEWS_PAGE_OK = "Get news listing";

    //Testimonials
    public static final String TESTIMONIALS_PAGE_URL = "localhost:8080/testimonials?page=";
    public static final String TESTIMONIALS_PAGE_INFO = "Returns a page of 10 testimonials elements";
    public static final String TESTIMONIALS_PAGE_OK = "Get testimonials listing";

    //Confirmation email
    public static final String CONTACT_CONFIRMATION_SUBJECT = "¡Contacto registrado exitosamente!";
    public static final String CONTACT_CONFIRMATION_PATH = "src/main/resources/templates/confirmationEmail.html";

    //Members
    public static final String MEMBERS_PAGE_URL = "localhost:8080/members?page=";
    public static final String MEMBERS_PAGE_INFO = "Returns a page of 10 members elements";
    public static final String MEMBERS_PAGE_OK = "Get members list";
    
    //Message the error
    public static final String ERROR_EXIST = "The item you want to add already exists";

    /*
    Internal Key for Token
     */
    public static final String SECRET_KEY = "SECRET_KEY";
    
    //Slides Update
    public static final String REQ_MAPP_UPDATE_SLIDES = "/{id}";
    public static final String REQ_MAPP_DELETE_SLIDES = "/{id}";
    
    public static final String REQ_MAPP_GET_LIST_USER = "/users";

    public static final String REQ_MAPP_DETAIL_CAT = "/{id}";
    
    
  //Tests
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";
    public static final String URL_PAGE_TEST = "?page={page}";
    
    public Constants() {
    }

}
