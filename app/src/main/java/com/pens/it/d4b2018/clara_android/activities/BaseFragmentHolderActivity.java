package com.pens.it.d4b2018.clara_android.activities;

import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pens.it.d4b2018.clara_android.R;

public abstract class BaseFragmentHolderActivity extends BaseActivity {

    protected RelativeLayout contentRelativeLayout;
    protected BottomNavigationView bottomNavigationView;
    protected TextView titleTextView;
    protected FrameLayout fragmentContainerFrameLayout;

    @Override
    protected void initializeView() {
        setContentView(R.layout.base_activity);
        contentRelativeLayout = findViewById(R.id.content_relative_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        titleTextView = findViewById(R.id.title_text_view);
        fragmentContainerFrameLayout = findViewById(R.id.fragment_container_frame_layout);
    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

}
