package com.starwarcasting.util;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import com.starwarcasting.R;
import com.starwarcasting.base.BaseActivity;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

/**
 * This is Utility class and it provides Ui related common utility functions.
 * This is a Singleton class
 */
public class UiUtils {

    private static UiUtils        uiUtils;
    private static ProgressDialog progressBar;

    /**
     * Private constructor to implement Singleton design pattern
     */
    private UiUtils() {
    }

    /**
     * This is used to get Instance of the class
     *
     * @return uiUtils object
     */
    public static UiUtils getInstance() {
        if (uiUtils == null) {
            uiUtils = new UiUtils();
        }
        return uiUtils;
    }

    /**
     * This is used to initialize toolbar
     *
     * @param activity          Current activity where toolbar is shown
     * @param title             Title of toolbar
     * @param isBackArrowEnable is used to show/hide back arrow on toolbar
     */
    public void handleToolBar(BaseActivity activity, String title, boolean isBackArrowEnable) {
        Toolbar mToolbar = activity.findViewById(R.id.toolbar_actionbar);
        @SuppressLint("InflateParams") View toolbarContent = activity.getLayoutInflater().inflate(R.layout.toolbar_title, null);
        AppCompatTextView tvTitle = toolbarContent.findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(title);
        mToolbar.addView(toolbarContent);
        mToolbar.setContentInsetsAbsolute(0, 0);
        mToolbar.setContentInsetsRelative(0, 0);
        activity.setSupportActionBar(mToolbar);

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(isBackArrowEnable);
            activity.getSupportActionBar().setHomeButtonEnabled(isBackArrowEnable);
        }
    }

    /**
     * This is used to show ProgressDialog
     *
     * @param context Context of the activity or fragment where Progress dialog is shown
     */
    public void showProgressDialog(Context context) {
        if (progressBar == null) {
            progressBar = new ProgressDialog(context);
            progressBar.setMessage(context.getString(R.string.str_loading));
        }
        progressBar.show();
    }

    /**
     * This is used to hide progress dialog
     */
    public void hideProgressDialog() {
        if (progressBar != null && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    /**
     * This is ued to show toast message
     *
     * @param context     Context of current Activity/Fragment
     * @param stringId    Message Id i.e. string value to be displayed
     * @param toastLength Time for which toast message appeared
     */
    public void showToast(Context context, int stringId, int toastLength) {
        Toast.makeText(context, context.getText(stringId), toastLength).show();
    }

    /**
     * This is used to get spanned colored text value
     *
     * @param contentValue Total String value
     * @param spanningText Part of 'ContentValue' which is getting spanned
     * @param spanColorId  spanned color value to be set to 'SpanningText'
     * @return Spanned Text value with color
     */
    public SpannableStringBuilder getFormattedText(String contentValue, String spanningText, int spanColorId) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(contentValue);
        ssBuilder.setSpan(
                new ForegroundColorSpan(spanColorId),
                contentValue.indexOf(spanningText),
                contentValue.indexOf(spanningText) + String.valueOf(spanningText).length(),
                SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return ssBuilder;
    }
}
