package com.cm.cdc.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cm.cdc.MainActivity;
import com.cm.cdc.R;
import com.cm.cdc.UserData;

public class AdminC extends AppCompatActivity {

    Button placement,logout,intern,event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        placement = findViewById(R.id.mplacement);
        logout = findViewById(R.id.logout);
        intern = findViewById(R.id.mintern);
        event = findViewById(R.id.mevent);

        placement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AdminPlacement.class);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserData u = new UserData();
                u.deleteUser(getApplication());
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        intern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminIntern.class);
                startActivity(i);
            }
        });

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EventHandler.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
