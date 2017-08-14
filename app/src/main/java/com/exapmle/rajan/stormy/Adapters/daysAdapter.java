package com.exapmle.rajan.stormy.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exapmle.rajan.stormy.R;
import com.exapmle.rajan.stormy.ui.MainActivity;
import com.exapmle.rajan.stormy.weather.day;

/**
 * Created by rajan on 07-08-2017.
 */

public class daysAdapter extends BaseAdapter {

    public day [] mDays;
    public Context mContext;

    daysAdapter(){}

    public daysAdapter(Context context, day[] days)  {
        mDays=days;
        mContext=context;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.dayadapter,null);
            viewHolder=new ViewHolder();
            viewHolder.iconImageView=(ImageView)convertView.findViewById(R.id.tempIcon);
             viewHolder.iconImageView1=(ImageView)convertView.findViewById(R.id.circle);
            viewHolder.tempuratureLabel=(TextView)convertView.findViewById(R.id.tempDisplay);
            viewHolder.dayLabel=(TextView)convertView.findViewById(R.id.dayDisplay);


            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
            day days=new day();
            days = mDays[position];
            viewHolder.iconImageView.setImageResource(days.getIconId());
            viewHolder.iconImageView1.setImageResource(R.drawable.clear_day);
            viewHolder.tempuratureLabel.setText(days.getTempMax()+"");
            viewHolder.dayLabel.setText(days.getDaysOfWeek());

        return convertView;
    }
    private static class ViewHolder{
        ImageView iconImageView;//=(ImageView)android.R.id.icon;//all public by default
        ImageView iconImageView1;
        TextView tempuratureLabel;
        TextView dayLabel;


    }
}
