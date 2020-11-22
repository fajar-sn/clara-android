package com.pens.it.d4b2018.clara_android.contract;

import com.pens.it.d4b2018.clara_android.fragment.BaseView;
import com.pens.it.d4b2018.clara_android.presenter.BasePresenter;

public interface SplashScreenContract {

    interface View extends BaseView<BasePresenter> {
        void redirectToDashboard();
        void redirectToSignIn();
    }

}
