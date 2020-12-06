package com.pens.it.d4b2018.clara_android.backup.contracts;

import com.pens.it.d4b2018.clara_android.backup.data.models.LoginRequest;
import com.pens.it.d4b2018.clara_android.backup.fragments.BaseView;
import com.pens.it.d4b2018.clara_android.backup.presenters.BasePresenter;

public interface SignInContract {
    interface Presenter extends BasePresenter {
        void performLogin(LoginRequest loginRequest);
    }

    interface View extends BaseView<Presenter> {
        void redirectToDashboard();
        void showToast(String message);
    }
}
