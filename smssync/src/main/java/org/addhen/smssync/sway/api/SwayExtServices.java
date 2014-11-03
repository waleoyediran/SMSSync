package org.addhen.smssync.sway.api;


import org.addhen.smssync.sway.model.DirectionResult;

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

    @GET("/directions/json")
    void getDirectionsAsync(@QueryMap Map<String, String> options,
                            Callback<DirectionResult> resultCallback);

    @GET("/directions/json")
    DirectionResult getDirectionsSync(@QueryMap Map<String, String> options);

//    @POST("/test")
//    void startTweetService(@Body TweetServiceRequest request, Callback<TweetServiceResponse> resultCallback);


}
