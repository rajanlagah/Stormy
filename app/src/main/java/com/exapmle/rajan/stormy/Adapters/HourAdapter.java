package com.exapmle.rajan.stormy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.exapmle.rajan.stormy.R;
import com.exapmle.rajan.stormy.weather.Hour;

/**
 * Created by rajan on 09-08-2017.
 */

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> implements View.OnClickListener{

    private Hour mHour[];
    public TextView mTimeLabel;
    public TextView mSummaryLabel;
    public TextView mTemperatureLabel;
    public ImageView mIconImageView;
    public Context mContext;
    public HourAdapter(Context context, Hour[] hours){
        mContext=context;
        mHour=hours;
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hour_adapter_layout,parent,false);

        HourViewHolder viewHolder=new HourViewHolder(view);

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mHour.length;
    }

    @Override
    public void onBindViewHolder(HourViewHolder holder, int position) {
        holder.BindHour(mHour[position]);
    }

    @Override
    public void onClick(View v) {

    }


    public class HourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         TextView mTimeLabel;
         TextView mSummaryLabel;
         TextView mTemperatureLabel;
         ImageView mIconImageView;


    public HourViewHolder(View itemView) {
        super(itemView);
      //  itemView.setOnClickListener((View.OnClickListener) this);
        mIconImageView=(ImageView)itemView.findViewById(R.id.circle);
        mTemperatureLabel=(TextView)itemView.findViewById(R.id.temp);
        mSummaryLabel=(TextView)itemView.findViewById(R.id.summury);
        mTimeLabel=(TextView)itemView.findViewById(R.id.time);

        itemView.setOnClickListener(this);
        }

        public void BindHour(Hour hour){
        mIconImageView.setImageResource(hour.getIconId());
            mTemperatureLabel.setText(hour.getTempurature()+"");
            mSummaryLabel.setText(hour.getSummery());
            mTimeLabel.setText(hour.getHour());
        }

        @Override
        public void onClick(View v) {
            String sumury="the tempurature expected is "+mSummaryLabel.getText().toString();
            Toast.makeText(mContext,sumury,Toast.LENGTH_LONG).show();

        }
    }
}
