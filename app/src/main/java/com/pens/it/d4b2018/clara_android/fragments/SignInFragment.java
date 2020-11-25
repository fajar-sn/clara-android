package com.pens.it.d4b2018.clara_android.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.activities.DashboardActivity;
import com.pens.it.d4b2018.clara_android.activities.SignInActivity;
import com.pens.it.d4b2018.clara_android.contracts.SignInContract;
import com.pens.it.d4b2018.clara_android.data.models.LoginRequest;
import com.pens.it.d4b2018.clara_android.data.models.LoginResponse;
import com.pens.it.d4b2018.clara_android.data.remotes.APIService;
import com.pens.it.d4b2018.clara_android.data.remotes.APIUtils;
import com.pens.it.d4b2018.clara_android.data.sessions.UserSessionRepository;
import com.pens.it.d4b2018.clara_android.presenters.SignInPresenter;

import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class SignInFragment extends BaseFragment<SignInActivity, SignInContract.Presenter> implements SignInContract.View, View.OnClickListener {

    private TextInputEditText emailTextInputEditText;
    private TextInputEditText passwordTextInputEditText;
    private TextView signupTextView;
    private Button signInButton;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.signin_layout, container, false);
        UserSessionRepository userSessionRepository = new UserSessionRepository(Objects.requireNonNull(getActivity()));
        mPresenter = new SignInPresenter(this, userSessionRepository);
        mPresenter.start();

        signupTextView = fragmentView.findViewById(R.id.signup_tv);
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

    @SuppressLint("ShowToast")
    @Override
    public void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
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
        LoginRequest loginRequest = new LoginRequest(email, password);
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            mPresenter.performLogin(loginRequest);
        }

    }

}
