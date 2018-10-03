package com.starwarcasting.base;

import android.content.Context;

public interface BaseView {

    Context getContext();

    void showContentView();

    void showNoInternetConnection();

    void showNoDataAvailable();

    void showProgress();

    void hideProgress();

}
