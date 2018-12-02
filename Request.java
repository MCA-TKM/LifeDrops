package com.example.faris.lifedrops;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Request extends AppCompatActivity {
    Spinner spinner;
    int radius;
    EditText e;
Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request2);
b=findViewById(R.id.findb);
e=findViewById(R.id.kmet);

        spinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.blood_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner layout
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String group=spinner.getSelectedItem().toString();
                radius=Integer.valueOf(e.getText().toString());
                Intent intent = new Intent(Request.this, Result.class);
                intent.putExtra("group", group);
                intent.putExtra("radius", radius);
                startActivityForResult(intent, 1);


            }

        });

    }





}