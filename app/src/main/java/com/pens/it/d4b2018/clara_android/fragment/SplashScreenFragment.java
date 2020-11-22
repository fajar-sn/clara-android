package com.pens.it.d4b2018.clara_android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.activity.DashboardActivity;
import com.pens.it.d4b2018.clara_android.activity.SignInActivity;
import com.pens.it.d4b2018.clara_android.activity.SplashScreenActivity;
import com.pens.it.d4b2018.clara_android.contract.SplashScreenContract;
import com.pens.it.d4b2018.clara_android.presenter.BasePresenter;
import com.pens.it.d4b2018.clara_android.presenter.SplashScreenPresenter;

public class SplashScreenFragment extends BaseFragment <SplashScreenActivity, BasePresenter> implements SplashScreenContract.View {

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.splash_screen_layout, container, false);
        mPresenter = new SplashScreenPresenter(this);
        mPresenter.start();
        return fragmentView;
    }

    @Override
    public void redirectToDashboard() {
        Intent intent = new Intent(activity, DashboardActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void redirectToSignIn() {
        Intent intent = new Intent(activity, SignInActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = presenter;
    }
}
