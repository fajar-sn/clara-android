package com.pens.it.d4b2018.clara_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
//        setContentView(R.layout.profile_layout);
//
//        setHeaderTitle("Asset");
//
//        setCurrentFragment(new TestFragment());
    }

//    private void setCurrentFragment(Fragment fragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.main_frame_container, fragment)
//                .commit();
//    }
//
//    private void setHeaderTitle(String title) {
//        TextView mainTitleTV = findViewById(R.id.profile_title_textview);
//        mainTitleTV.setText(title);
//    }

}