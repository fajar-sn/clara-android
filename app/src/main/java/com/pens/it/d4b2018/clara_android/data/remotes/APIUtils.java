package com.pens.it.d4b2018.clara_android.data.remotes;

public class APIUtils {

    private APIUtils() {}

    public static final String BASE_URL = "https://clara-app.tech/api/";
    public static final String LOGIN_URL = "login/";
    public static final String REGISTER_URL = BASE_URL + "register/student/";
    public static final String ASSETS_URL = BASE_URL + "assets/";
    public static final String PROFILE_URL = BASE_URL + "profile/";
    public static final String LOGOUT_URL = BASE_URL + "logout/";
    public static final String RESERVATIONS_URL = BASE_URL + "reservations/student/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
