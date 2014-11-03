package org.addhen.smssync.sway.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sway.sway.model.DirectionResult;
import com.sway.sway.model.Leg;
import com.sway.sway.model.Route;
import com.sway.sway.model.Step;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Utility {
    /**
     * Is there internet connection
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivity.getActiveNetworkInfo();
        // NetworkInfo info

        if (networkInfo != null && networkInfo.isConnected()
                && networkInfo.isAvailable()) {
            return true;
        }
        return false;

    }


    public static List<String> getRoadsFromWayPoints(DirectionResult result){
        List<String> roadList = new ArrayList<String>();

        for (Route route : result.routes){
            for (Leg leg : route.legs){
                for (Step step : leg.steps){
                    String html = step.htmlInstructions;
                    
                    String road = getRoad(html);
                }
            }
        }
    }

    private static String getRoad(String html) {
    }
}
