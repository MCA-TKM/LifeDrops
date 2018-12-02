package com.example.faris.lifedrops;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class HomeActivity extends AppCompatActivity {

    ImageButton myImageButton;
    EditText EditName,EditAge,EditMail,EditPhone,EditPlace,EditState;
    Spinner spinnerGroup;
    DatabaseReference mDatabase;
    Button b;
    private FusedLocationProviderClient mFusedLocationClient;
    SharedPreferences sharedpreferences;
    User user;
    GeoFire geoFire;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedpreferences = getSharedPreferences("lifedrop", Context.MODE_PRIVATE);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("geofire");

        geoFire = new GeoFire(ref);

        myImageButton=findViewById(R.id.signin);
        EditName=findViewById(R.id.name);
        EditAge=findViewById(R.id.age);
        spinnerGroup=findViewById(R.id.group);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGroup.setAdapter(adapter);

        EditMail=findViewById(R.id.email);
        EditPhone=findViewById(R.id.phone);

        mDatabase=FirebaseDatabase.getInstance().getReference();
        myImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = EditName.getText().toString();
                String group = spinnerGroup.getSelectedItem().toString();
                String age=EditAge.getText().toString();
                Long Age=Long.parseLong(age);
                String email = EditMail.getText().toString();
                phone = EditPhone.getText().toString();

                user=new User(name,group,email,phone,Age);
                mDatabase.child("Users"+"/"+phone).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("phone", user.Phone);
                            editor.commit();
                            Intent loadNewActivity = new Intent(HomeActivity.this, dashboard.class);
                            startActivity(loadNewActivity);
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this, "Errokjhlr!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    Toast.makeText(HomeActivity.this, "Try Again", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(HomeActivity.this, " Registered Successfully", Toast.LENGTH_SHORT).show();

                    mFusedLocationClient.getLastLocation()
                            .addOnSuccessListener(HomeActivity.this, new OnSuccessListener<Location>() {
                                @Override
                                public void onSuccess(Location location) {
                                    // Got last known location. In some rare situations this can be null.
                                    if (location != null) {
                                        // Logic to handle location object
                                        geoFire.setLocation(phone, new GeoLocation(location.getLatitude(), location.getLongitude()));

                                        Log.d("locas", Double.toString(location.getLatitude())+","+Double.toString(location.getLongitude()));
                                    }
                                }
                            });
                }


            }
        });


    }
}
