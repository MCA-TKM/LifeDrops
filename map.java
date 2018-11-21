package com.example.user.lifedrops;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class map extends AppCompatActivity {
    Button imgz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        imgz=(Button)findViewById(R.id.bn);
        imgz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity = new Intent(map.this,searchuser.class);
                startActivity(loadNewActivity);
            }
        });
    }
}
