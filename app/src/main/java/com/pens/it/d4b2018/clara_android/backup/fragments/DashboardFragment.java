package com.pens.it.d4b2018.clara_android.backup.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.pens.it.d4b2018.clara_android.R;
import com.pens.it.d4b2018.clara_android.backup.activities.DashboardActivity;
import com.pens.it.d4b2018.clara_android.backup.contracts.DashboardContract;

public class DashboardFragment extends BaseFragment<DashboardActivity, DashboardContract.Presenter> implements DashboardContract.View {

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        mPresenter = new DashboardPresenter(this);
//        mPresenter.start();

        return fragmentView;
    }

    @Override
    public void redirectToReservations() {

    }

    @Override
    public void setPresenter(DashboardContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
