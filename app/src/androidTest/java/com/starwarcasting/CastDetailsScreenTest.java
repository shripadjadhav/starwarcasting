package com.starwarcasting;

import android.content.Intent;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.starwarcasting.castdetails.CastDetailsScreen;
import com.starwarcasting.model.DataStarWarCast;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.starwarcasting.constant.ParamConstant.DATA_STAR_WAR_CAST;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class CastDetailsScreenTest {

    @Rule
    public ActivityTestRule<CastDetailsScreen> rule =
            new ActivityTestRule(CastDetailsScreen.class, true, false);

    /**
     * This is used to check Details Screen loaded or not
     * also Checks name is displayed in correct format
     */
    @Test
    public void checkIsDataDisplayCorrectly() {
        // create data
        DataStarWarCast paramStartWarCast = new DataStarWarCast();
        paramStartWarCast.name = "xyz";
        paramStartWarCast.height = "167";
        paramStartWarCast.mass = "75";
        paramStartWarCast.created = "2014-12-09T13:50:51.644000Z";

        // add data in an intent
        Intent intent = new Intent();
        intent.putExtra(DATA_STAR_WAR_CAST, paramStartWarCast);

        // launch activity with intent data
        rule.launchActivity(intent);

        // validate test case
        onView(withId(R.id.tv_castDetails_name)).check(matches(withText("Name : xyz")));

    }
}
