package com.pens.it.d4b2018.clara_android.activities;

import android.view.View;

import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.fragments.DashboardFragment;

public class DashboardActivity extends BaseFragmentHolderActivity {

    @Override
    protected void initializeFragment() {
        initializeView();
        contentRelativeLayout.setVisibility(View.VISIBLE);
        titleTextView.setVisibility(View.GONE);
        fragmentContainerFrameLayout.setVisibility(View.GONE);
        bottomNavigationView.setVisibility(View.VISIBLE);
        DashboardFragment dashboardFragment = new DashboardFragment();
        setCurrentFragment(dashboardFragment, false, R.id.content_relative_layout);
    }
}
