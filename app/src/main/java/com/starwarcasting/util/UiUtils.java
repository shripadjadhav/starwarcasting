package com.starwarcasting.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Toast;

import com.starwarcasting.R;
import com.starwarcasting.base.BaseActivity;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class UiUtils {

    private static UiUtils        uiUtils;
    private static ProgressDialog progressBar;

    private UiUtils() {
    }

    public static UiUtils getInstance() {
        if (uiUtils == null) {
            uiUtils = new UiUtils();
        }
        return uiUtils;
    }

    /**
     * This is used to handle click event on toolbar
     * Also handle icon on toolbar
     */
    public void handleToolBar(BaseActivity activity, String title, boolean isBackArrowEnable) {
        Toolbar mToolbar = activity.findViewById(R.id.toolbar_actionbar);
        View toolbarContent = activity.getLayoutInflater().inflate(R.layout.toolbar_title, null);
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

    public void showProgressBar(Context context) {
        if (progressBar == null) {
            progressBar = new ProgressDialog(context);
            progressBar.setMessage(context.getString(R.string.str_loading));
        }
        progressBar.show();
    }

    public void hideProgress() {
        if (progressBar != null && progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    public void showToast(Context context, int stringId, int toastLength) {
        Toast.makeText(context, context.getText(stringId), toastLength).show();
    }

    public void showToast(Context context, String string, int toastLength) {
        Toast.makeText(context, string, toastLength).show();
    }

    public SpannableStringBuilder getFormattedText(Context context, String contentValue, String spanningText) {
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(contentValue);
        ssBuilder.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorTextLabel)),
                contentValue.indexOf(spanningText),
                contentValue.indexOf(spanningText) + String.valueOf(spanningText).length(),
                SPAN_EXCLUSIVE_EXCLUSIVE
        );
        return ssBuilder;
    }
}
