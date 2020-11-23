package com.pens.it.d4b2018.clara_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.activities.DashboardActivity;
import com.pens.it.d4b2018.clara_android.activities.SignInActivity;
import com.pens.it.d4b2018.clara_android.contracts.SignInContract;
import com.pens.it.d4b2018.clara_android.data.sessions.UserSessionRepository;
import com.pens.it.d4b2018.clara_android.presenters.SignInPresenter;

import java.util.Objects;

public class SignInFragment extends BaseFragment<SignInActivity, SignInContract.Presenter> implements SignInContract.View, View.OnClickListener {

    TextInputEditText emailTextInputEditText;
    TextInputEditText passwordTextInputEditText;
    Button signInButton;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.signin_layout, container, false);
        UserSessionRepository userSessionRepository = new UserSessionRepository(getActivity());
        mPresenter = new SignInPresenter(this, userSessionRepository);
        mPresenter.start();

        emailTextInputEditText = fragmentView.findViewById(R.id.email_input);
        passwordTextInputEditText = fragmentView.findViewById(R.id.password_input);
        signInButton = fragmentView.findViewById(R.id.signin_btn);
        signInButton.setOnClickListener(this);

        return fragmentView;
    }

    @Override
    public void redirectToDashboard() {
        Intent intent = new Intent(activity, DashboardActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(SignInContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signin_btn :
                setSignInButtonClick();
                break;
        }
    }

    private void setSignInButtonClick() {
        String email = Objects.requireNonNull(emailTextInputEditText.getText()).toString();
        String password = Objects.requireNonNull(passwordTextInputEditText.getText()).toString();
        mPresenter.performLogin(email, password);
    }
}
