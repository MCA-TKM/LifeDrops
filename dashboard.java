package com.example.faris.lifedrops;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class dashboard extends AppCompatActivity {
    ImageButton myImageButton2;
    ImageButton myImageButton3;
    ImageButton myImageButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        myImageButton2=(ImageButton)findViewById(R.id.profile);
        myImageButton3=(ImageButton)findViewById(R.id.request);
        myImageButton4=(ImageButton)findViewById(R.id.emer);
        myImageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity = new Intent(dashboard.this,profile.class);
                startActivity(loadNewActivity);
            }
        });
        myImageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity2 = new Intent(dashboard.this,Request.class);
                startActivity(loadNewActivity2);
            }
        });
        myImageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity3 = new Intent(dashboard.this,emerg.class);
                startActivity(loadNewActivity3);
            }
        });


    }
}
