package org.addhen.smssync.sway.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by segunfamisa on 10/25/14.
 */

public class Step implements Serializable {

    @SerializedName("html_instructions")
    public String htmlInstructions;
}