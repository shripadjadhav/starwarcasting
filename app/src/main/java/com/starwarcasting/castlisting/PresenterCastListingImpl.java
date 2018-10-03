package com.starwarcasting.castlisting;

import com.starwarcasting.api.ApiClient;
import com.starwarcasting.api.ApiInterface;
import com.starwarcasting.base.BasePresenter;
import com.starwarcasting.response.ResponseStarWarCasting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("NullableProblems")
public class PresenterCastListingImpl extends BasePresenter implements PresenterCastListing {

    private ViewCastListing viewCastListing;

    public PresenterCastListingImpl(ViewCastListing viewCastListing) {
        this.viewCastListing = viewCastListing;
    }

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
