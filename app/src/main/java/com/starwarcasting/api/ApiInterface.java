package com.starwarcasting.api;

import com.starwarcasting.response.ResponseStarWarCasting;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This is used to list out API required in Application
 */
public interface ApiInterface {

    /**
     * This api is used to get all list of characters
     *
     * @return list of Star War cast
     */
    @GET("people")
    Call<ResponseStarWarCasting> getStarWarCastList();

}
