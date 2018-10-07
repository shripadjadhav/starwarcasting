package com.starwarcasting.castlisting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.starwarcasting.AdapterCastListing;
import com.starwarcasting.R;
import com.starwarcasting.base.BaseActivity;
import com.starwarcasting.castdetails.CastDetailsScreen;
import com.starwarcasting.model.DataStarWarCast;
import com.starwarcasting.util.NetworkUtils;
import com.starwarcasting.util.UiUtils;

import java.util.ArrayList;

import static com.starwarcasting.constant.ParamConstant.DATA_STAR_WAR_CAST;

public class CastListingScreen extends BaseActivity implements ViewCastListing, AdapterCastListing.Callback {

    /**
     * This is used to show list of
     */
    private RecyclerView         rvList;
    /**
     * Presenter for Listing functionality
     */
    private PresenterCastListing presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast_listing_screen);
    }

    /**
     * This is used to call API to get list of star war characters
     */
    private void loadDataFromApi() {
        if (NetworkUtils.isConnected(CastListingScreen.this)) {
            showProgress();
            presenter.getCastListingData();
        } else {
            showNoInternetConnection();
        }
    }

    @Override
    protected void getIntentData() {

    }

    @Override
    protected void bindToolbar() {
        showToolbar(getString(R.string.str_titleCastListing), false);
    }

    @Override
    protected void bindControls() {
        // data ui variable init
        rvList = findViewById(R.id.rv_starWarCastingList);
    }

    @Override
    protected void bindListeners() {
    }

    @Override
    protected void bindValues() {
        presenter = new PresenterCastListingImpl(this);
        loadDataFromApi();
    }

    @Override
    protected void btnRetryClicked() {
        loadDataFromApi();
    }

    @Override
    public void onSelected(DataStarWarCast data) {
        Intent intent = new Intent(CastListingScreen.this, CastDetailsScreen.class);
        intent.putExtra(DATA_STAR_WAR_CAST, data);
        startActivity(intent, false, RIGHT_IN_LEFT_OUT);
    }

    @Override
    public void onFetchDataCastListing(ArrayList<DataStarWarCast> listData) {
        hideProgress();
        AdapterCastListing adapter =
                new AdapterCastListing(CastListingScreen.this,
                        listData, CastListingScreen.this);
        rvList.setAdapter(adapter);
    }

    @Override
    public void failure() {
        hideProgress();
    }

    @Override
    public void showNoDataAvailable() {
        UiUtils.getInstance().showToast(getContext(), R.string.err_noDataFound, Toast.LENGTH_LONG);
        hideProgress();
    }
}
