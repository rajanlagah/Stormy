package com.exapmle.rajan.stormy.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.exapmle.rajan.stormy.Location1;
import com.exapmle.rajan.stormy.R;
import com.exapmle.rajan.stormy.weather.CurrentWeather;
import com.exapmle.rajan.stormy.weather.Hour;
import com.exapmle.rajan.stormy.weather.day;
import com.exapmle.rajan.stormy.weather.forcast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {
    private static String url = "https://api.darksky.net/forecast/74ab2eb9b86a17040f370dfc78eee890/";//37.8267,-122.4233";
    private final OkHttpClient client = new OkHttpClient();
    private forcast mForcast;
    private TextView time;
    private TextView cloud;
    private TextView humidity;
    private TextView presure;
    private TextView temp;
    private TextView sumury;
    private TextView present;
    private ImageView mImageView;
    private ImageView mRefresh;
    private ProgressBar mProgressBar;
    private Button daysForcast;
    private Button HourForcast;
    public static final String DAILY_FORCAST = "DAILY_FORCAST";
    public static final String Hourly_DATA = "Hourly_DATA";
    public Location1 mLocation;
    public double lat=0;
    public double lon=0;
    private FusedLocationProviderClient mFusedLocationClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //for location
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if(checkLocationPermission())
                Toast.makeText(MainActivity.this,"null123", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Toast.makeText(MainActivity.this,location.toString(), Toast.LENGTH_LONG).show();
                            lat=location.getLatitude();
                            lon=location.getLongitude();
                            url += Double.toString(lat) + "," + Double.toString(lon);
                            System.out.println(url+" url is");
                            try {
                                if (connectionpresent()) {
                                    run();
                                } else
                                    alertDialouge();
                            } catch (Exception e) {
                                alertDialouge();
                                e.printStackTrace();
                            }


                        }
                        else{
                            //  mTextView.setText(location.toString());
                        }
                    }
                });


        LocationManager locationManager;
       // LocationApi l1=new LocationApi((Context)this);
        //Toast.makeText(this,Double.toString(lat) + "," + Double.toString(lon),Toast.LENGTH_LONG).show();
       // Log.e(String.valueOf(MainActivity.this),url);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRefresh = (ImageView) findViewById(R.id.refresh);
        mRefresh.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        daysForcast = (Button) findViewById(R.id.forcast);
        HourForcast = (Button) findViewById(R.id.hourly);

        daysForcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DailyForcast.class);
                intent.putExtra("abc", mForcast.getDays());
                startActivity(intent);
            }
        });
        HourForcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HourlyForcast1.class);
                intent.putExtra(Hourly_DATA, mForcast.getHours());
                startActivity(intent);

            }
        });


        mRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (connectionpresent()) {
                        togelVisibality();
                        run();
                    } else
                        alertDialouge();
                } catch (Exception e) {
                    // Toast.makeText(MainActivity.this,"dsad",Toast.LENGTH_LONG).show();
                    alertDialouge();
                    e.printStackTrace();
                }
            }
        });


    }
    protected void createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    private boolean connectionpresent() {
        ConnectivityManager manager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo connection=manager.getActiveNetworkInfo();
        boolean isConnected=false;
        if(connection != null){
            isConnected=true;
        }
        return isConnected;
    }

    public void run() throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        togelVisibality();
                    }
                });

            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    if (response.isSuccessful()) {
                    final String jsonData=response.body().string();
                        try {
                            mForcast= getForcastDetails(jsonData);
                            mForcast.setHours(getHoursData(jsonData));
                            mForcast.setDays(getDaysData(jsonData));
                            //can not set values here we have to go to main thread
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        displayData(jsonData);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    togelVisibality();
                                }
                            });
                        } catch (JSONException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    togelVisibality();
                                }
                            });
                            e.printStackTrace();
                        }

                    }
                else{
                }
            }
        });
    }

    private day[] getDaysData(String jsonData) throws JSONException{
        JSONObject data=new JSONObject(jsonData);
        JSONObject daily=data.getJSONObject("daily");
        JSONArray days=daily.getJSONArray("data");

        day day[]=new day[days.length()];

        for(int i=0;i<day.length;i++){
          JSONObject today=days.getJSONObject(i);
            day[i]=new day();
            day[i].setIcon(today.getString("icon"));
            day[i].setSummery(today.getString("summary"));
            day[i].setTimeZone(data.getString("timezone"));
            day[i].setTempMax(today.getDouble("temperatureMax"));
            day[i].setTime(today.getLong("time"));
        }
        return day;
    }

    private void displayData(String jsonData)throws JSONException {
        CurrentWeather currentWeather=mForcast.getCurrentWeather();
        temp=(TextView)findViewById(R.id.temp);
        temp.setText(Math.round(currentWeather.getTemperature())+"");
        time=(TextView)findViewById(R.id.time);
        time.setText(currentWeather.getFormatedTime());
        humidity=(TextView)findViewById(R.id.humidity);
        humidity.setText(currentWeather.getHumidity()+"");
        presure=(TextView)findViewById(R.id.pressure);
        presure.setText(Math.round(currentWeather.getPresure())+"");
        cloud=(TextView)findViewById(R.id.clouds);
        cloud.setText(Double.toString(currentWeather.getPreipChance()));
        sumury=(TextView)findViewById(R.id.summury);
        sumury.setText(currentWeather.getSummary());
        present=(TextView)findViewById(R.id.present);
        present.setText(currentWeather.getTimeZone());
        mImageView=(ImageView)findViewById(R.id.imageView);
        Drawable drawable= ContextCompat.getDrawable(this,currentWeather.getIconId());
        mImageView.setImageDrawable(drawable);

    }

    public Hour[] getHoursData(String jsonData) throws JSONException{

        JSONObject data=new JSONObject(jsonData);
        JSONObject hours=data.getJSONObject("hourly");
        JSONArray hourArray=hours.getJSONArray("data");

        Hour hour[]=new Hour[hourArray.length()];

        for(int i=0;i<hour.length;i++){
            JSONObject current=hourArray.getJSONObject(i);
            hour[i]=new Hour();
            hour[i].setIcon(current.getString("icon"));
            hour[i].setTimeZone(data.getString("timezone"));
            hour[i].setMtime(current.getLong("time"));
            hour[i].setTempurature(current.getDouble("temperature"));
            hour[i].setSummery(current.getString("summary"));
        }
        return hour;
    }


    private forcast getForcastDetails(String jsonData) throws JSONException{
        forcast fcast=new forcast();
        fcast.setCurrentWeather(getCurrentDetails(jsonData));
        return fcast;
    }



    //throw json exception as we want to handel exception from where it is called
    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException{
        JSONObject jsonObject=new JSONObject(jsonData);
        JSONObject current=jsonObject.getJSONObject("currently");
        CurrentWeather currentWeather=new CurrentWeather();
        currentWeather.setIcon(current.getString("icon"));
        currentWeather.setHumidity(current.getDouble("humidity"));
        currentWeather.setPreipChance(current.getInt("precipProbability"));
        currentWeather.setSummary(current.getString("summary"));
        currentWeather.setTime(current.getLong("time"));
        currentWeather.setTemperature(current.getDouble("temperature"));
        currentWeather.setTimeZone(jsonObject.getString("timezone"));
        String place=jsonObject.getString("timezone");
        currentWeather.setPresure(current.getDouble("pressure"));
        System.out.println(place);
        return currentWeather;
    }

    private void alertDialouge() {
        AlertDialouge dialog=new AlertDialouge();
        dialog.show(getFragmentManager(),"error");
    }
    public void togelVisibality(){
        if(mProgressBar.getVisibility() ==View.VISIBLE){
            mProgressBar.setVisibility(View.INVISIBLE);
            mRefresh.setVisibility(View.VISIBLE);
        }
        else{
            mProgressBar.setVisibility(View.VISIBLE);
            mRefresh.setVisibility(View.INVISIBLE);
        }
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission. ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission. ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location permission")
                        .setMessage("for weather of ur city. location is important")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission. ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mFusedLocationProviderClient.getLastLocation()
                            .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    if (location != null) {
                                        Toast.makeText(MainActivity.this,"granted", Toast.LENGTH_LONG).show();
                                      //  mTextView.setText(location.toString());

                                    }
                                    else{
                                        //mTextView.setText(location.toString());
                                    }
                                }
                            });
                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission. ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        Toast.makeText(this,"done",Toast.LENGTH_LONG).show();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

}
