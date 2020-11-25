package com.pens.it.d4b2018.clara_android.contracts;

import com.pens.it.d4b2018.clara_android.data.models.LoginRequest;
import com.pens.it.d4b2018.clara_android.data.remotes.APIService;
import com.pens.it.d4b2018.clara_android.fragments.BaseView;
import com.pens.it.d4b2018.clara_android.presenters.BasePresenter;

public interface SignInContract {
    interface Presenter extends BasePresenter {
        void performLogin(LoginRequest loginRequest);
    }

    interface View extends BaseView<Presenter> {
        void redirectToDashboard();
        void showToast(String message);
    }
}
