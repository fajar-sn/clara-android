package com.pens.it.d4b2018.clara_android.presenters;

import com.pens.it.d4b2018.clara_android.contracts.SplashScreenContract;

import android.os.Handler;

public class SplashScreenPresenter implements BasePresenter {

    private final SplashScreenContract.View VIEW;

    public SplashScreenPresenter(SplashScreenContract.View view) {
        VIEW = view;
    }

    @Override
    public void start() {
        int delay = 750; //in millis
        final Handler handler = new Handler();
        handler.postDelayed(VIEW::redirectToSignIn, delay);
    }
}
