package com.example.covid;

import com.example.covid.ui.CountryData;
import com.example.covid.ui.GlobalData;

import java.util.ArrayList;
import java.util.List;

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

    public String getDate(){
        return Date;
    }

}