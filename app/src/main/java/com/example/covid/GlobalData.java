package com.example.covid;

public class GlobalData
{
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;

    public int getTotalConfirmed(){
        return TotalConfirmed;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public int getNewDeaths() {
        return NewDeaths;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }
}