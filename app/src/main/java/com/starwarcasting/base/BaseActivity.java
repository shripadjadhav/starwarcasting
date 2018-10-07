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

/**
 * This is Base Activity class to store all common methods/functionality
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * Constant used in activity transition
     * Transition from Right to Left
     */
    protected static final int RIGHT_IN_LEFT_OUT = 1;
    /**
     * Constant used in activity transition
     * Transition from Left to Right
     */
    protected static final int LEFT_IN_RIGHT_OUT = 2;

    /**
     * Layout where child activity layout is getting loaded
     */
    private FrameLayout  childLayout;
    /**
     * No internet connection view
     */
    private LinearLayout llNoInternet;

    /**
     * This method is called after onResume method of Child Activity
     * This method is used get Intent data if passed from previous activity
     */
    abstract protected void getIntentData();

    /**
     * This method is called after getIntentData method
     * This method is used to initialize toolbar
     */
    abstract protected void bindToolbar();

    /**
     * This method is called after bindToolbar method
     * This method is used to initialize UI controls
     */
    abstract protected void bindControls();

    /**
     * This method is called after bindControls method
     * This method is used to set listeners to UI control
     */
    abstract protected void bindListeners();

    /**
     * This method is called after bindListener method
     * This method is used to set values to UI controls
     */
    abstract protected void bindValues();

    /**
     * This method will be called if user clicks on Retry button
     */
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
     *
     * @param intent          It contains next Activity reference with Bundle data if any
     * @param isFinishCurrent Finishes current Activity if true otherwise not
     * @param inOut           It is used for Transition of activity
     */
    public void startActivity(Intent intent, boolean isFinishCurrent, int inOut) {
        startActivity(intent);
        if (isFinishCurrent) {
            finish();
        }
        transition(inOut);
    }

    /**
     * This is used to finish Current Activity
     *
     * @param inOut It is used for Transition of activity
     */
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
        UiUtils.getInstance().showProgressDialog(BaseActivity.this);
    }

    @Override
    public void hideProgress() {
        UiUtils.getInstance().hideProgressDialog();
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

    /**
     * This is used to show Toolbar
     *
     * @param title             Title of the toolbar
     * @param isBackArrowEnable This is used to show/hide back arrow on Toolbar
     */
    public void showToolbar(String title, boolean isBackArrowEnable) {
        findViewById(R.id.toolbar_actionbar).setVisibility(View.VISIBLE);
        UiUtils.getInstance().handleToolBar(this, title, isBackArrowEnable);
    }

    /**
     * This is used for activity transition
     *
     * @param inOut It is used for Transition of activity
     */
    private void transition(int inOut) {
        if (inOut == RIGHT_IN_LEFT_OUT) {
            this.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        } else {
            this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }

}
