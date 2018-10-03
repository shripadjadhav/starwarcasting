package com.starwarcasting.response;

import com.starwarcasting.model.DataStarWarCast;

import java.util.ArrayList;

public class ResponseStarWarCasting {
    public int    count;
    public String next;
    public String previous;
    public ArrayList<DataStarWarCast> results = new ArrayList<>();
}
