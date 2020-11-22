package com.pens.it.d4b2018.clara_android.contract;

import com.pens.it.d4b2018.clara_android.fragment.BaseView;
import com.pens.it.d4b2018.clara_android.presenter.BasePresenter;

public interface SignInContract {
    interface Presenter extends BasePresenter {
        void performLogin(final String EMAIL, final String PASSWORD);
    }

    interface View extends BaseView<Presenter> {
        void redirectToDashboard();
    }
}
