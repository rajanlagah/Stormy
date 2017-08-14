package com.exapmle.rajan.stormy.weather;

import com.exapmle.rajan.stormy.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rajan on 06-08-2017.
 */

public class CurrentWeather {
    private String mIcon;
    private long mTime;
    private double mTemperature;
    private double mHumidity;
    private double mPreipChance;
    private String mSummary;
    private String timeZone;
    private Double presure;
    private int iconId;

    public Double getPresure() {
        return presure;
    }

    public void setPresure(Double presure) {
        this.presure = presure;
    }

    public String getFormatedTime(){
        SimpleDateFormat time=new SimpleDateFormat("h:mm a");
        time.setTimeZone(TimeZone.getTimeZone(getTimeZone()));

        Date date=new Date(getTime()*1000);
        String formatedTime=time.format(date);
        return formatedTime;
    }
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getIconId() {
        return forcast.getIconId(mIcon);
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public double getTemperature() {
        return mTemperature;
    }

    public void setTemperature(double temperature) {
        mTemperature = temperature;
    }

    public double getHumidity() {
        return mHumidity;
    }

    public void setHumidity(double humidity) {
        mHumidity = humidity;
    }

    public double getPreipChance() {
        return mPreipChance;
    }

    public void setPreipChance(double preipChance) {
        mPreipChance = preipChance;
    }

    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }
}
