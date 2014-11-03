package org.addhen.smssync.sway.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by segunfamisa on 10/25/14.
 */
public class Route {
    public List<Leg> legs;

    @SerializedName("overview_polyline")
    public OverviewPolyLine overviewPolyLine;
}