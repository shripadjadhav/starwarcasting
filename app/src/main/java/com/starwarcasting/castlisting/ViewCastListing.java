package com.starwarcasting.castlisting;

import com.starwarcasting.base.BaseView;
import com.starwarcasting.model.DataStarWarCast;

import java.util.ArrayList;

/**
 * This is View class to interact with Cast listing screen from Presenter
 */
public interface ViewCastListing extends BaseView {

    /**
     * This method is called after fetching List of Characters
     *
     * @param listData contains list of Star War Character list
     */
    void onFetchDataCastListing(ArrayList<DataStarWarCast> listData);

    /**
     * This is method is called if API call is failed
     */
    void failure();
}
