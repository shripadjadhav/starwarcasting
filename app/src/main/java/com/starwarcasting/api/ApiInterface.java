package com.starwarcasting.api;

import com.starwarcasting.response.ResponseStarWarCasting;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("people")
    Call<ResponseStarWarCasting> getStarWarCastList();

}
