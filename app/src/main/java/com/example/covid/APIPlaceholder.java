package com.example.covid;

import java.util.ArrayList;

public class APIPlaceholder {
    private GlobalData Global;
    private ArrayList<CountryData> Countries;
    private String Date;

    public int getGlobalConfirmed(){
        if(Global == null){
            return -1;
        }
        else {
            return Global.getTotalConfirmed();
        }
    }

    public int getGlobalRecovered(){
        if(Global == null){
            return -1;
        }
        else {
            return Global.getTotalRecovered();
        }
    }

    public int getGlobalDeaths(){
        if(Global == null){
            return -1;
        }
        else {
            return Global.getTotalDeaths();
        }
    }

    public String getDate(){
        return Date;
    }

}