package com.pens.it.d4b2018.clara_android.contracts;

import com.pens.it.d4b2018.clara_android.fragments.BaseView;
import com.pens.it.d4b2018.clara_android.presenters.BasePresenter;

public interface SignInContract {
    interface Presenter extends BasePresenter {
        void performLogin(String email, String password);
    }

    interface View extends BaseView<Presenter> {
        void redirectToDashboard();
    }
}
