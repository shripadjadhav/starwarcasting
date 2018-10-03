package com.starwarcasting.presenter;

import com.starwarcasting.castlisting.PresenterCastListing;
import com.starwarcasting.castlisting.PresenterCastListingImpl;
import com.starwarcasting.castlisting.ViewCastListing;
import com.starwarcasting.model.DataStarWarCast;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

public class PresenterCastListingTest {

    private PresenterCastListing presenterCastListing;

    @Mock
    private ViewCastListing viewCastListing;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenterCastListing = new PresenterCastListingImpl(viewCastListing);
    }

    /*
     * Test the behaviour after API call
     */
    @Test
    public void testStarWarCastListApi() {
        presenterCastListing.getCastListingData();
    }

}
