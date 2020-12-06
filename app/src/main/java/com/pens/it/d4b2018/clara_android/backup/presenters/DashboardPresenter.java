package com.pens.it.d4b2018.clara_android.backup.presenters;

import com.pens.it.d4b2018.clara_android.backup.contracts.DashboardContract;

public class DashboardPresenter implements DashboardContract.Presenter {

    private final DashboardContract.View VIEW;

    public DashboardPresenter(DashboardContract.View VIEW) {
        this.VIEW = VIEW;
    }

    @Override
    public void redirectToReservations() {
        VIEW.redirectToReservations();
    }

    @Override
    public void start() {
//        redirectToReservations();
    }
}
