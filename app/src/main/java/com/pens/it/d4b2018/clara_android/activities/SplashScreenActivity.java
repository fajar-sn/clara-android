package com.pens.it.d4b2018.clara_android.activities;

import android.view.View;

import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.fragments.SplashScreenFragment;

public class SplashScreenActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        contentRelativeLayout.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.GONE);
        SplashScreenFragment splashScreenFragment = new SplashScreenFragment();
        setCurrentFragment(splashScreenFragment, false, R.id.base_activity_layout);
    }

}
