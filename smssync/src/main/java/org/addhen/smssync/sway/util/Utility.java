package org.addhen.smssync.sway.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import org.addhen.smssync.sway.model.DirectionResult;
import org.addhen.smssync.sway.model.Leg;
import org.addhen.smssync.sway.model.Route;
import org.addhen.smssync.sway.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

                    roadList.addAll(getRoad(html));
                }
            }
        }
        return roadList;
    }

    private static List<String> getRoad(String html) {
        List<String> roadTags = new ArrayList<String>();

        Pattern p = Pattern.compile("<b>(.*?)</b>");
        Matcher m = p.matcher(html);
//        List<String> temp = new ArrayList<String>();
        while(m.find()){
            String s = m.group(1);
            if(s.matches(".*(rd|Rd|way|Way|Av|av|Ave|Str|str|st|St|street|Street).*"))
                roadTags.add(s);
        }



        return roadTags;
    }
}
