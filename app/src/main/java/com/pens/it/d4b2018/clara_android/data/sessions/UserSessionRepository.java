package com.pens.it.d4b2018.clara_android.data.sessions;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.pens.it.d4b2018.clara_android.data.models.User;

public class UserSessionRepository implements SessionRepository<User> {

    private static final String SESSION_USER = "SessionUser";
    private final SharedPreferences SHARED_PREFERENCES;

    public UserSessionRepository(Context context) {
        SHARED_PREFERENCES = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public User initialize(User sessionData) {
        setSessionData(sessionData);
        return getSessionData();
    }

    @Override
    public User getSessionData() {
        String sessionDataJson = SHARED_PREFERENCES.getString(SESSION_USER, null);
        if (sessionDataJson != null)
            return new Gson().fromJson(sessionDataJson, User.class);
        return null;
    }

    @Override
    public void setSessionData(User sessionData) {
        SharedPreferences.Editor editor = SHARED_PREFERENCES.edit();
        editor.putString(SESSION_USER, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        SHARED_PREFERENCES.edit().clear().apply();
    }

    @Override
    public void update(User sessionData) {
        destroy();
        setSessionData(sessionData);
    }
}
