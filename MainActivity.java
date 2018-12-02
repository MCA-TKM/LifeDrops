package com.example.faris.lifedrops;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    SharedPreferences sharedpreferences;
    public static int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences = getSharedPreferences("lifedrop", Context.MODE_PRIVATE);


        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(sharedpreferences.contains("phone"))
                    {
                        Intent homeIntent = new Intent(MainActivity.this,dashboard.class);
                        startActivity(homeIntent);
                    }
                    else
                    {
                        Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(homeIntent);
                    }
                    finish();
                }
            },SPLASH_TIME_OUT);
        }





    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode==MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION) {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(sharedpreferences.contains("phone"))
                            {
                                Intent homeIntent = new Intent(MainActivity.this,dashboard.class);
                                startActivity(homeIntent);
                            }
                            else
                            {
                                Intent homeIntent = new Intent(MainActivity.this,HomeActivity.class);
                                startActivity(homeIntent);
                            }
                            finish();
                        }
                    },SPLASH_TIME_OUT);

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            // other 'case' lines to check for other
            //   permissions this app might request
        }
    }


