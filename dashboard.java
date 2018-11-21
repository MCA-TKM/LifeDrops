package com.example.user.lifedrops;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class dashboard extends AppCompatActivity {
    ImageButton myImageButton2;
    ImageButton myImageButton3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myImageButton2=(ImageButton)findViewById(R.id.profile);
        myImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity = new Intent(dashboard.this,profile.class);
                startActivity(loadNewActivity);
            }
        });
        myImageButton3=(ImageButton)findViewById(R.id.search);
        myImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity2 = new Intent(dashboard.this,map.class);
                startActivity(loadNewActivity2);
            }
        });
            }
    }

