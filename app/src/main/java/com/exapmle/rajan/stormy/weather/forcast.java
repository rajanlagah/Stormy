package com.exapmle.rajan.stormy.weather;

import com.exapmle.rajan.stormy.R;

/**
 * Created by rajan on 06-08-2017.
 */

public class forcast {
    private CurrentWeather mCurrentWeather;
    private Hour[] mHours;
    private day[] mDays;

    public CurrentWeather getCurrentWeather() {
        return mCurrentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        mCurrentWeather = currentWeather;
    }

    public Hour[] getHours() {
        return mHours;
    }

    public void setHours(Hour[] hours) {
        mHours = hours;
    }

    public day[] getDays() {
        return mDays;
    }

    public void setDays(day[] days) {
        mDays = days;
    }

    public static int getIconId(String iconData){
        int iconId=R.drawable.clear_day;
        if (iconData.equals("clear-day")) {
            iconId = R.drawable.clear_day;
        }
        else if (iconData.equals("clear-night")) {
            iconId = R.drawable.clear_night;
        }
        else if (iconData.equals("rain")) {
            iconId = R.drawable.rain;
        }
        else if (iconData.equals("snow")) {
            iconId = R.drawable.snow;
        }
        else if (iconData.equals("sleet")) {
            iconId = R.drawable.sleet;
        }
        else if (iconData.equals("wind")) {
            iconId = R.drawable.wind;
        }
        else if (iconData.equals("fog")) {
            iconId = R.drawable.fog;
        }
        else if (iconData.equals("cloudy")) {
            iconId = R.drawable.cloudy;
        }
        else if (iconData.equals("partly-cloudy-day")) {
            iconId = R.drawable.partly_cloudy;
        }
        else if (iconData.equals("partly-cloudy-night")) {
            iconId = R.drawable.cloudy_night;
        }
        return iconId;

    }
}
