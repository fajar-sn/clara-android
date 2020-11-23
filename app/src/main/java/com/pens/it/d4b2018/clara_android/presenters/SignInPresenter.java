package com.pens.it.d4b2018.clara_android.presenters;

import com.pens.it.d4b2018.clara_android.contracts.SignInContract;
import com.pens.it.d4b2018.clara_android.data.models.User;
import com.pens.it.d4b2018.clara_android.data.sessions.SessionRepository;

public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.View VIEW;
    private final SessionRepository<User> SESSION_REPOSITORY;

    public SignInPresenter(SignInContract.View view, SessionRepository<User> sessionRepository) {
        VIEW = view;
        this.SESSION_REPOSITORY = sessionRepository;
    }

    @Override
    public void performLogin(String email, String password) {
        User loggedUser = new User(email, password);
        SESSION_REPOSITORY.setSessionData(loggedUser);
        VIEW.redirectToDashboard();
    }

    @Override
    public void start() {
        if (SESSION_REPOSITORY.getSessionData() != null)
            VIEW.redirectToDashboard();
    }
}
