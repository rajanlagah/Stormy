package com.exapmle.rajan.stormy.ui;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.exapmle.rajan.stormy.Adapters.HourAdapter;
import com.exapmle.rajan.stormy.R;
import com.exapmle.rajan.stormy.weather.Hour;

import java.util.Arrays;

public class HourlyForcast1 extends AppCompatActivity  {

    public Hour mHour[];
    public RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_forcast1);

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);

        Intent intent=getIntent();
        Parcelable parcelable[]=intent.getParcelableArrayExtra(MainActivity.Hourly_DATA);
        mHour= Arrays.copyOf(parcelable,parcelable.length,Hour[].class);

        HourAdapter adapter = new HourAdapter(this,mHour);
        mRecyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);


    }
}
