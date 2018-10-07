package com.starwarcasting;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.starwarcasting.castlisting.CastListingScreen;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.number.OrderingComparison.greaterThan;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class CastListingScreenTest {

    @Rule
    public ActivityTestRule<CastListingScreen> rule = new ActivityTestRule<>(CastListingScreen.class);

    /**
     * This test is used to check Recycler view shown or not
     */
    @Test
    public void ensureRecyclerViewIsPresent() {
        CastListingScreen activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.rv_starWarCastingList);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView rv = (RecyclerView) viewById;
        AdapterCastListing adapter = (AdapterCastListing) rv.getAdapter();
        assertThat(adapter, instanceOf(AdapterCastListing.class));
        assertThat(adapter.getItemCount(), greaterThan(1));
    }
}
