package com.example.user.lifedrops;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class searchuser extends AppCompatActivity {
ImageButton imge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchuser);
        imge=(ImageButton)findViewById(R.id.searchbn);
        imge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadNewActivity = new Intent(searchuser.this,searching.class);
                startActivity(loadNewActivity);
            }
        });

    }
}
