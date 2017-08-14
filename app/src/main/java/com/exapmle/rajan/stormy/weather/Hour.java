package com.exapmle.rajan.stormy.weather;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.widget.SlidingPaneLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rajan on 06-08-2017.
 */


public class Hour  implements Parcelable {
    private long mtime;
    private String mSummery;
    private double mTempurature;
    private String mIcon;
    private String mTimeZone;

    public Hour(){}
    protected Hour(Parcel in) {
        mtime = in.readLong();
        mSummery = in.readString();
        mTempurature = in.readDouble();
        mIcon = in.readString();
        mTimeZone = in.readString();
    }

    public static final Creator<Hour> CREATOR = new Creator<Hour>() {
        @Override
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        @Override
        public Hour[] newArray(int size) {
            return new Hour[size];
        }
    };

    public long getMtime() {
        return mtime;
    }

    public void setMtime(long mtime) {
        this.mtime = mtime;
    }

    public String getSummery() {
        return mSummery;
    }

    public void setSummery(String summery) {
        mSummery = summery;
    }

    public int getTempurature() {
        return (int)Math.round(mTempurature);
    }
    public int getIconId(){
        return forcast.getIconId(mIcon);
    }

    public void setTempurature(double tempurature) {
        mTempurature = tempurature;
    }

    public String getIcon() {
        return mIcon;
    }

    public void setIcon(String icon) {
        mIcon = icon;
    }

    public String getHour(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("h a");
        Date date =new Date(mtime*1000);
        return dateFormat.format(date);
    }
    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mtime);
        dest.writeString(mSummery);
        dest.writeDouble(mTempurature);
        dest.writeString(mIcon);
        dest.writeString(mTimeZone);
    }
}
