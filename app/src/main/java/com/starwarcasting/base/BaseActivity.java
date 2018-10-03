package com.starwarcasting.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.starwarcasting.R;
import com.starwarcasting.util.UiUtils;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    protected static final int RIGHT_IN_LEFT_OUT = 1;
    protected static final int LEFT_IN_RIGHT_OUT = 2;

    // UI controls
    private FrameLayout  childLayout;
    private LinearLayout llNoInternet;

    /*
     * Below are methods for Base Activity - Mandatory to implement in Child Activity
     */
    abstract protected void getIntentData();

    abstract protected void bindToolbar();

    abstract protected void bindControls();

    abstract protected void bindListeners();

    abstract protected void bindValues();

    abstract protected void btnRetryClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        // activity root layout
        @SuppressLint("InflateParams") View rootView = getLayoutInflater().inflate(R.layout.activity_base, null);  // The base layout

        // child activity layout
        childLayout = rootView.findViewById(R.id.childActivity); // The frame layout where the activity content is placed.
        getLayoutInflater().inflate(layoutResID, childLayout, true); // Places the activity layout inside the activity content frame.

        // place holder
        llNoInternet = rootView.findViewById(R.id.ll_retry);
        // retry button
        AppCompatButton btnRetry = rootView.findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnRetryClicked();
            }
        });

        //   View rootView = getLayoutInflater().inflate(layoutResID, null);
        // super class method call
        super.setContentView(rootView);

        // init objects
        initializeViewAndObject();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }


    /**
     * <b>Note: </b> Override this method to use your own logic and sequence of binding
     */
    protected void initializeViewAndObject() {
        getIntentData();
        bindToolbar();
        bindControls();
        bindValues();
        bindListeners();
    }

    /**
     * This is used to start another activity
     */
    public void startActivity(Intent intent, boolean isFinishCurrent, int inOut) {
        startActivity(intent);
        if (isFinishCurrent) {
            finish();
        }
        transition(inOut);
    }

    public void finishActivity(int inOut) {
        finish();
        transition(inOut);
    }

    @Override
    public Context getContext() {
        return this;
    }

    /**
     * This is used to show no internet connection message
     */
    @Override
    public void showNoInternetConnection() {
        llNoInternet.setVisibility(View.VISIBLE);
        childLayout.setVisibility(View.GONE);
        hideProgress();
    }

    @Override
    public void showProgress() {
        UiUtils.getInstance().showProgressBar(BaseActivity.this);
    }

    @Override
    public void hideProgress() {
        UiUtils.getInstance().hideProgress();
    }

    /**
     * This is used to show original layout
     */
    @Override
    public void showContentView() {
        llNoInternet.setVisibility(View.GONE);
        childLayout.setVisibility(View.VISIBLE);
        hideProgress();
    }

    public void showToolbar(String title, boolean isBackArrowEnable) {
        findViewById(R.id.toolbar_actionbar).setVisibility(View.VISIBLE);
        UiUtils.getInstance().handleToolBar(this, title, isBackArrowEnable);
    }

    public void transition(int inOut) {
        if (inOut == RIGHT_IN_LEFT_OUT) {
            this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        } else {
            this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }

}
