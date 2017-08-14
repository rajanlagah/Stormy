package com.exapmle.rajan.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;

import com.exapmle.rajan.stormy.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by rajan on 06-08-2017.
 */

public class day implements Parcelable {
    private String mSummery="";
    private String mTimeZone="";
    private long mTime=0l;
    private double mTempMax=0.0;
    private String mIcon;

    public day(){}

    protected day(Parcel in) {
        mSummery = in.readString();
        mTimeZone = in.readString();
        mTime = in.readLong();
        mTempMax = in.readDouble();
        mIcon = in.readString();
    }

    public static final Creator<day> CREATOR = new Creator<day>() {
        @Override
        public day createFromParcel(Parcel in) {
            return new day(in);
        }

        @Override
        public day[] newArray(int size) {
            return new day[size];
        }
    };

    public String getSummery() {
        return mSummery;
    }

    public void setSummery(String summery) {
        mSummery = summery;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    public long getTime() {
        return mTime;
    }

    public void setTime(long time) {
        mTime = time;
    }

    public int getTempMax() {
        return (int)Math.round(mTempMax);
    }

    public void setTempMax(double tempMax) {
        mTempMax = tempMax;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public int getIconId() {
        return forcast.getIconId(mIcon);
    }
    public String getDaysOfWeek(){
        SimpleDateFormat format=new SimpleDateFormat("EEEE");
        format.setTimeZone(TimeZone.getTimeZone(mTimeZone));
        Date dateTime =new Date(mTime*1000);
        return format.format(dateTime);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mSummery);
        dest.writeString(mTimeZone);
        dest.writeLong(mTime);
        dest.writeDouble(mTempMax);
        dest.writeString(mIcon);
    }
}
