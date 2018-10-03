package com.starwarcasting.castlisting;

import com.starwarcasting.base.BaseView;
import com.starwarcasting.model.DataStarWarCast;

import java.util.ArrayList;

public interface ViewCastListing extends BaseView {

    void onFetchDataCastListing(ArrayList<DataStarWarCast> listData);

    void failure();
}
