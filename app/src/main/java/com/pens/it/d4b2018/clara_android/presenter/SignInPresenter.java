package com.pens.it.d4b2018.clara_android.presenter;

import com.pens.it.d4b2018.clara_android.contract.SignInContract;

public class SignInPresenter implements SignInContract.Presenter {

    private final SignInContract.View VIEW;

    public SignInPresenter(SignInContract.View view) {
        VIEW = view;
    }

    @Override
    public void performLogin(String EMAIL, String PASSWORD) {

    }

    @Override
    public void start() {

    }
}
