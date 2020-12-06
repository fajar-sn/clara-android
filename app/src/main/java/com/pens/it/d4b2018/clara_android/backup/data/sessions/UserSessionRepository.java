package com.pens.it.d4b2018.clara_android.backup.data.sessions;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pens.it.d4b2018.clara_android.backup.data.models.LoginResponse;

public class UserSessionRepository implements SessionRepository<LoginResponse> {

    private static final String USER_TOKEN = "user_token";
    private final SharedPreferences SHARED_PREFERENCES;

    public UserSessionRepository(Context context) {
        SHARED_PREFERENCES = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public LoginResponse initialize(LoginResponse sessionData) {
        setSessionData(sessionData);
        return getSessionData();
    }

    @Override
    public LoginResponse getSessionData() {
        String sessionDataJson = SHARED_PREFERENCES.getString(USER_TOKEN, null);
        if (sessionDataJson != null)
            return new Gson().fromJson(sessionDataJson, LoginResponse.class);
        return null;
    }

    @Override
    public void setSessionData(LoginResponse sessionData) {
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
        editor.putString(USER_TOKEN, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        SHARED_PREFERENCES.edit().clear().apply();
    }

    @Override
    public void update(LoginResponse sessionData) {
        destroy();
        setSessionData(sessionData);
    }
}
