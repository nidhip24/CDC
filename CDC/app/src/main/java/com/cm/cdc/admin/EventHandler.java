package com.cm.cdc.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cm.cdc.R;

public class EventHandler extends AppCompatActivity {

    Button add,del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_handler);

        add = findViewById(R.id.addEvent);
        del = findViewById(R.id.deleteEvent);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AdminEvent.class);
                startActivity(i);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),DeleteEvent.class);
                startActivity(i);
            }
        });
    }
}
