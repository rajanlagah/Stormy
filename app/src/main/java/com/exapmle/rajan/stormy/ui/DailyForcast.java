package com.exapmle.rajan.stormy.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.exapmle.rajan.stormy.Adapters.daysAdapter;
import com.exapmle.rajan.stormy.R;
import com.exapmle.rajan.stormy.weather.day;

import java.util.Arrays;

public class DailyForcast extends ListActivity {

    public day[] mDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forcast);

        Intent intent =getIntent();
        Parcelable parcaelable[]=intent.getParcelableArrayExtra("abc");
        mDays= Arrays.copyOf(parcaelable,parcaelable.length,day[].class);

       // String days[]={"Sunday","Monday","tuesday","Wednessday","thursday","saturday"};
      //  ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,days);
        //setListAdapter(adapter);

        daysAdapter adapter=new daysAdapter(this,mDays);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String day=mDays[position].getDaysOfWeek();
        String summury=mDays[position].getSummery();
        String temp=mDays[position].getTempMax()+"";
        String message="on "+day+" condition will b "+summury+" and max tempurature will be "+temp;

        Toast.makeText(DailyForcast.this,message,Toast.LENGTH_LONG).show();
    }
}
