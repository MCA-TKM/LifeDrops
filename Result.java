package com.example.faris.lifedrops;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Result extends AppCompatActivity {
    private FusedLocationProviderClient mFusedLocationClient;
    GeoQuery geoQuery;
    GeoFire geoFire;
    int radius;
    ListView lv;
           String group;

    DatabaseReference mPostReference;

    ArrayList<DataModel> dataModels;
    private static CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(Result.this);
        lv=findViewById(R.id.list);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("geofire");
        geoFire = new GeoFire(ref);
        Intent intent = getIntent();
         radius = intent.getIntExtra("radius",2);
         group = intent.getStringExtra("group");

        dataModels= new ArrayList<>();
        adapter= new CustomAdapter(dataModels,getApplicationContext());

        lv.setAdapter(adapter);
        Log.d("chk",Integer.valueOf(radius).toString() );
        if (ContextCompat.checkSelfPermission(Result.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            Toast.makeText(Result.this, "not grnt", Toast.LENGTH_SHORT).show();

        }
        else {
            Log.d("chk",Integer.valueOf(radius).toString()+"tt" );
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(Result.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                Log.d("chk",Integer.valueOf(radius).toString()+"pp" );
                                // Logic to handle location object
                                geoQuery = geoFire.queryAtLocation(new GeoLocation(location.getLatitude(), location.getLongitude()), Integer.valueOf(radius));
                                Log.d("chk",Integer.valueOf(radius).toString() );


                                geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
                                    @Override
                                    public void onKeyEntered(String key, GeoLocation location) {

                                        System.out.println(String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude));
                                    //    Toast.makeText(Result.this, String.format("Key %s entered the search area at [%f,%f]", key, location.latitude, location.longitude), Toast.LENGTH_SHORT).show();
                                        updatelist(key);


                                    }

                                    @Override
                                    public void onKeyExited(String key) {
                                        System.out.println(String.format("Key %s is no longer in the search area", key));
                                    }

                                    @Override
                                    public void onKeyMoved(String key, GeoLocation location) {
                                        System.out.println(String.format("Key %s moved within the search area to [%f,%f]", key, location.latitude, location.longitude));
                                    }

                                    @Override
                                    public void onGeoQueryReady() {
                                        System.out.println("All initial data has been loaded and events have been fired!");
                                    }

                                    @Override
                                    public void onGeoQueryError(DatabaseError error) {
                                        System.err.println("There was an error with this query: " + error);
                                    }
                                });



                                Log.d("locas", Double.toString(location.getLatitude())+","+Double.toString(location.getLongitude()));
                            }
                            Log.d("chk",Integer.valueOf(radius).toString()+"uu" );

                        }
                    });
        }
    }
    void updatelist(String k)
    {

        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(k);
        //  Toast.makeText(profile.this, mail, Toast.LENGTH_SHORT).show();
        mPostReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String st1=dataSnapshot.child("Name").getValue().toString();
                String st2=dataSnapshot.child("Age").getValue().toString();
                String st3=dataSnapshot.child("Blood").getValue().toString();
                String st4=dataSnapshot.child("Email").getValue().toString();
                String st5=dataSnapshot.child("Phone").getValue().toString();

                // Post post = dataSnapshot.getValue(Post.class);
                // System.out.println(post);
                // Toast.makeText(profile.this, st, Toast.LENGTH_SHORT).show();
                if(group.compareTo(st3)==0)
                { dataModels.add(new DataModel(st1,st4, st2,st5));
adapter.notifyDataSetChanged();}


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}
