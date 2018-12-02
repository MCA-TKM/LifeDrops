package com.example.faris.lifedrops;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    DatabaseReference mPostReference;
    TextView e1;
    TextView e2;
    TextView e3;
    TextView e4;
    TextView e5;
    TextView e6;
    TextView e7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        SharedPreferences sharedpreferences;
        sharedpreferences = getSharedPreferences("lifedrop", Context.MODE_PRIVATE);
        SharedPreferences sharedPref = profile.this.getPreferences(Context.MODE_PRIVATE);
       // int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
        String phon = sharedpreferences.getString("phone","null");
        //Toast.makeText(this, phon, Toast.LENGTH_SHORT).show();

e1=findViewById(R.id.name);
e2=findViewById(R.id.age);
e3=findViewById(R.id.blood);
e4=findViewById(R.id.email);
e5=findViewById(R.id.phone);

        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("Users").child(phon);
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
                e1.setText(st1);
                e2.setText(st2);
                e3.setText(st3);
                e4.setText(st4);
                e5.setText(st5);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

}

