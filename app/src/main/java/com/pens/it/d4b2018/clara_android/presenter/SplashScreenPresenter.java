package com.pens.it.d4b2018.clara_android.presenter;

import com.pens.it.d4b2018.clara_android.contract.SplashScreenContract;

import android.os.Handler;

public class SplashScreenPresenter implements BasePresenter {

    private final SplashScreenContract.View VIEW;

    public SplashScreenPresenter(SplashScreenContract.View view) {
        VIEW = view;
    }

    @Override
    public void start() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                VIEW.redirectToSignIn();
            }
        }, 750);
    }
}
