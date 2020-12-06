package com.pens.it.d4b2018.clara_android.backup.contracts;

import com.pens.it.d4b2018.clara_android.backup.fragments.BaseView;
import com.pens.it.d4b2018.clara_android.backup.presenters.BasePresenter;

public interface SplashScreenContract {

    interface View extends BaseView<BasePresenter> {
        void redirectToSignIn();
    }

}
