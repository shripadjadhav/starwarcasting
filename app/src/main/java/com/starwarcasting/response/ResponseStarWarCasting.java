package com.starwarcasting.response;

import com.starwarcasting.model.DataStarWarCast;

import java.util.ArrayList;

/**
 * This is used to store response of api /people
 */
public class ResponseStarWarCasting {
    /**
     * This variable shows total number of record count
     */
    public int                        count;
    /**
     * This is used to show Next page link
     */
    public String                     next;
    /**
     * This is used to show Previous page link
     */
    public String                     previous;
    /**
     * Data Entity list variable
     */
    public ArrayList<DataStarWarCast> results = new ArrayList<>();
}
