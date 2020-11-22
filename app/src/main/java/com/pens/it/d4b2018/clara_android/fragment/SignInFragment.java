package com.pens.it.d4b2018.clara_android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;
import com.pens.it.d4b2018.clara_android.R;

public class SignInFragment extends BaseFragment {

    TextInputEditText email;
    TextInputEditText password;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.signin_layout, container, false);



        return fragmentView;
    }

}
