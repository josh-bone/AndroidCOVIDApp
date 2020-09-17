package com.example.covid;

public class CountryData {
    private String Country;
    private String CountryCode;
    private String Slug;
    private int NewConfirmed;
    private int TotalConfirmed;
    private int NewDeaths;
    private int TotalDeaths;
    private int NewRecovered;
    private int TotalRecovered;
    private String Date;

    public String getCountry() {
        return Country;
    }

    public String getDate() {
        return Date;
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

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public String getSlug() {
        return Slug;
    }

    public String getCountryCode() {
        return CountryCode;
    }
}
