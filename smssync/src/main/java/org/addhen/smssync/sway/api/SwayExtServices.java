package org.addhen.smssync.sway.api;


import com.sway.sway.model.DirectionResult;
import com.sway.sway.model.PlacesResult;

import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.QueryMap;

/**
 *
 */
public interface SwayExtServices {

    @GET("/place/autocomplete/json")
    PlacesResult getPlacePrediction(@QueryMap Map<String, String> options);

    @GET("/directions/json")
    void getDirectionsAsync(@QueryMap Map<String, String> options,
                            Callback<DirectionResult> resultCallback);

    @POST("/tweet")
    void startTweetService(@Body List<String> roadPoints, Callback<String> resultCallback);
}
