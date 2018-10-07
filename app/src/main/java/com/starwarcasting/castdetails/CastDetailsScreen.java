package com.starwarcasting.castdetails;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.starwarcasting.R;
import com.starwarcasting.base.BaseActivity;
import com.starwarcasting.model.DataStarWarCast;
import com.starwarcasting.util.DateUtils;
import com.starwarcasting.util.UiUtils;

import static com.starwarcasting.constant.ParamConstant.DATA_STAR_WAR_CAST;
import static com.starwarcasting.util.DateUtils.DF_DD_MM_YYYY_HH_MM;
import static com.starwarcasting.util.DateUtils.DF_YYYY_MM_DD_T_HH_MM_SS_SSS;

/**
 * This activity is used to show details of character
 */
public class CastDetailsScreen extends BaseActivity {

    /**
     * This is used to show name of character
     */
    private TextView        tvName;
    /**
     * This is used to show height of character
     */
    private TextView        tvHeight;
    /**
     * This is used to show mass of character
     */
    private TextView        tvMass;
    /**
     * This is used to show character's CreatedDate
     */
    private TextView        tvCreatedDate;
    /**
     * This is data variable holds the details of character
     */
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
        // get name value
        String STR_NAME = getString(R.string.str_name_colon);
        String strNameValue = STR_NAME + " " + paramStartWarCast.name;

        // get height value
        String STR_HEIGHT = getString(R.string.str_height_colon);
        float heightInMeter = Float.parseFloat(paramStartWarCast.height) / 100;
        String strHeightValue = STR_HEIGHT + " " + heightInMeter + " " + getString(R.string.str_unit_meter);

        // get weight value
        String STR_MASS = getString(R.string.str_mass_colon);
        String strMassValue = STR_MASS + " " + paramStartWarCast.mass + " " + getString(R.string.str_unit_kg);

        // get created value
        String STR_CREATED_DATE = getString(R.string.str_createdDate_colon);
        // format date
        String formattedDate =
                new DateUtils().getFormattedDate(paramStartWarCast.created,
                        DF_YYYY_MM_DD_T_HH_MM_SS_SSS, DF_DD_MM_YYYY_HH_MM);
        String strDateValue = STR_CREATED_DATE + " " + formattedDate;

        int colorId = ContextCompat.getColor(CastDetailsScreen.this, R.color.colorTextLabel);
        tvName.setText(UiUtils.getInstance().getFormattedText(strNameValue, STR_NAME, colorId));
        tvHeight.setText(UiUtils.getInstance().getFormattedText(strHeightValue, STR_HEIGHT, colorId));
        tvMass.setText(UiUtils.getInstance().getFormattedText(strMassValue, STR_MASS, colorId));
        tvCreatedDate.setText(UiUtils.getInstance().getFormattedText(strDateValue, STR_CREATED_DATE, colorId));
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
