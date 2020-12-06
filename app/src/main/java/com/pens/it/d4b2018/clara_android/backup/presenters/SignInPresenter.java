package com.pens.it.d4b2018.clara_android.backup.presenters;

import com.pens.it.d4b2018.clara_android.backup.contracts.SignInContract;
import com.pens.it.d4b2018.clara_android.backup.data.models.LoginRequest;
import com.pens.it.d4b2018.clara_android.backup.data.models.LoginResponse;
import com.pens.it.d4b2018.clara_android.backup.data.remotes.APIService;
import com.pens.it.d4b2018.clara_android.backup.data.remotes.APIUtils;
import com.pens.it.d4b2018.clara_android.backup.data.sessions.SessionRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.View VIEW;
    private final SessionRepository<LoginResponse> SESSION_REPOSITORY;
    private APIService mAPIService;

    public SignInPresenter(SignInContract.View view, SessionRepository<LoginResponse> sessionRepository) {
        VIEW = view;
        this.SESSION_REPOSITORY = sessionRepository;
        mAPIService = APIUtils.getAPIService();
    }

    @Override
    public void performLogin(LoginRequest loginRequest) {
        mAPIService.performLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    SESSION_REPOSITORY.setSessionData(loginResponse);
                    VIEW.showToast("Login successful");
                    VIEW.redirectToDashboard();
                } else {
                    ResponseBody responseBody = response.errorBody();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody.string());
                        VIEW.showToast(jsonObject.getJSONObject("error").getString("message"));
                    } catch (JSONException | IOException e) {
                        VIEW.showToast(e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                VIEW.showToast(t.getMessage());
            }
        });

//        User loggedUser = new User(email, password);
//        SESSION_REPOSITORY.setSessionData(loggedUser);
//        VIEW.redirectToDashboard();
    }

    @Override
    public void start() {
        if (SESSION_REPOSITORY.getSessionData() != null)
            VIEW.redirectToDashboard();
    }
}
