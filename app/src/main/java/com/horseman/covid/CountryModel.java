package com.horseman.covid;

public class CountryModel {
    private String flag,country,Cases,Todaycases,deaths,Todaydeaths,Recovered,Active,Critical;

    public CountryModel() {
    }

    public CountryModel(String flag, String country, String cases, String todaycases, String deaths, String todaydeaths, String recovered, String active, String critical) {
        this.flag = flag;
        this.country = country;
        Cases = cases;
        Todaycases = todaycases;
        this.deaths = deaths;
        Todaydeaths = todaydeaths;
        Recovered = recovered;
        Active = active;
        Critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return Cases;
    }

    public void setCases(String cases) {
        Cases = cases;
    }

    public String getTodaycases() {
        return Todaycases;
    }

    public void setTodaycases(String todaycases) {
        Todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return Todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        Todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getCritical() {
        return Critical;
    }

    public void setCritical(String critical) {
        Critical = critical;
    }
}

