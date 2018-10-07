package com.starwarcasting.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.starwarcasting.BuildConfig.BASE_URL;

/**
 * This class is used to init Retrofit object
 */
public class ApiClient {

    /**
     * This is Retrofit object used to call API
     */
    private static Retrofit retrofit = null;

    /**
     * This is used to get Retrofit object
     *
     * @return retrofit object
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
