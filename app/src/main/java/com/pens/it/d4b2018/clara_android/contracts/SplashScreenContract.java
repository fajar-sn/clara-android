package com.pens.it.d4b2018.clara_android.contracts;

import com.pens.it.d4b2018.clara_android.fragments.BaseView;
import com.pens.it.d4b2018.clara_android.presenters.BasePresenter;

public interface SplashScreenContract {

    interface View extends BaseView<BasePresenter> {
        void redirectToSignIn();
    }

}
