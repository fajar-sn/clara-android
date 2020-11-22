package com.pens.it.d4b2018.clara_android.activity;

import android.view.View;

import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.fragment.SignInFragment;

public class SignInActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        contentRelativeLayout.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        SignInFragment signInFragment = new SignInFragment();
        setCurrentFragment(signInFragment, false, R.id.base_activity_layout);
    }

}
