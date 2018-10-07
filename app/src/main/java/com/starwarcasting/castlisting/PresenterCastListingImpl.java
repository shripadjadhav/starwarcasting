package com.starwarcasting.castlisting;

import com.starwarcasting.api.ApiClient;
import com.starwarcasting.api.ApiInterface;
import com.starwarcasting.base.BasePresenter;
import com.starwarcasting.response.ResponseStarWarCasting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This is implementation class of Presenter for Cast listing screen
 */
@SuppressWarnings("NullableProblems")
public class PresenterCastListingImpl extends BasePresenter implements PresenterCastListing {

    /**
     * This is View object to interact with Listing Screen UI component
     */
    private ViewCastListing viewCastListing;

    /**
     * Constructor
     *
     * @param viewCastListing Listing screen View reference
     */
    public PresenterCastListingImpl(ViewCastListing viewCastListing) {
        this.viewCastListing = viewCastListing;
    }

    /**
     * This is used to get list of all Characters in Star War
     */
    @Override
    public void getCastListingData() {
        ApiInterface apiClient = ApiClient.getClient().create(ApiInterface.class);
        apiClient.getStarWarCastList().enqueue(new Callback<ResponseStarWarCasting>() {
            @Override
            public void onResponse(Call<ResponseStarWarCasting> call, Response<ResponseStarWarCasting> response) {
                viewCastListing.showContentView();
                if (response.isSuccessful() && response.body() != null) {
                    ResponseStarWarCasting responseStarWarCasting = response.body();
                    if (responseStarWarCasting != null
                            && responseStarWarCasting.results != null
                            && responseStarWarCasting.results.size() > 0) {
                        viewCastListing.onFetchDataCastListing(responseStarWarCasting.results);
                    } else {
                        viewCastListing.showNoDataAvailable();
                    }
                } else {
                    viewCastListing.showNoDataAvailable();
                }
            }

            @Override
            public void onFailure(Call<ResponseStarWarCasting> call, Throwable t) {
                viewCastListing.failure();
            }
        });
    }
}
