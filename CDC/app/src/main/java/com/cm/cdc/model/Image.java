package com.cm.cdc.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ... on 04/04/16.
 */
public class Image implements Serializable {
    private String name;
    private String small, medium, large;
    private String timestamp;
    public ArrayList<String> imgl;

    public Image() {
    }

    public Image(String name, String small, String medium, String large, String timestamp) {
        this.name = name;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.timestamp = timestamp;
    }

    public ArrayList<String> getURL(){
        return imgl;
    }

    public void setURL(ArrayList<String> s){
        imgl = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
