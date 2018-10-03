package com.starwarcasting.castdetails;

import android.os.Bundle;
import android.widget.TextView;

import com.starwarcasting.R;
import com.starwarcasting.base.BaseActivity;
import com.starwarcasting.model.DataStarWarCast;
import com.starwarcasting.util.UiUtils;

import static com.starwarcasting.constant.ParamConstant.DATA_STAR_WAR_CAST;

public class CastDetailsScreen extends BaseActivity {

    // UI control
    private TextView        tvName;
    private TextView        tvHeight;
    private TextView        tvMass;
    private TextView        tvCreatedDate;
    // param value
    private DataStarWarCast paramStartWarCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_details_screen);
    }

    @Override
    protected void getIntentData() {
        // get bundle data
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(DATA_STAR_WAR_CAST)) {
            paramStartWarCast = bundle.getParcelable(DATA_STAR_WAR_CAST);
        }
    }

    @Override
    protected void bindToolbar() {
        showToolbar(getString(R.string.str_titleCastDetails), true);
    }

    @Override
    protected void bindControls() {
        tvName = findViewById(R.id.tv_castDetails_name);
        tvHeight = findViewById(R.id.tv_castDetails_height);
        tvMass = findViewById(R.id.tv_castDetails_mass);
        tvCreatedDate = findViewById(R.id.tv_castDetails_createdDate);
    }

    @Override
    protected void bindListeners() {

    }

    @Override
    protected void bindValues() {
        String STR_NAME = getString(R.string.str_name_colon);
        String strNameValue = STR_NAME + " " + paramStartWarCast.name;

        String STR_HEIGHT = getString(R.string.str_height_colon);
        float heightInMeter = Float.parseFloat(paramStartWarCast.height) / 100;
        String strHeightValue = STR_HEIGHT + " " + heightInMeter + " " + getString(R.string.str_unit_meter);

        String STR_MASS = getString(R.string.str_mass_colon);
        String strMassValue = STR_MASS + " " + paramStartWarCast.mass + " " + getString(R.string.str_unit_kg);

        String STR_CREATED_DATE = getString(R.string.str_createdDate_colon);
        String strDateValue = STR_CREATED_DATE + " " + paramStartWarCast.created;

        tvName.setText(UiUtils.getInstance().getFormattedText(getContext(), strNameValue, STR_NAME));
        tvHeight.setText(UiUtils.getInstance().getFormattedText(getContext(), strHeightValue, STR_HEIGHT));
        tvMass.setText(UiUtils.getInstance().getFormattedText(getContext(), strMassValue, STR_MASS));
        tvCreatedDate.setText(UiUtils.getInstance().getFormattedText(getContext(), strDateValue, STR_CREATED_DATE));
    }

    @Override
    protected void btnRetryClicked() {

    }

    @Override
    public void showNoDataAvailable() {
    }

    @Override
    public void onBackPressed() {
        finishActivity(LEFT_IN_RIGHT_OUT);
    }
}
