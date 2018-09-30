package com.cm.cdc.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cm.cdc.Placement_company_list;
import com.cm.cdc.R;

public class AdminPlacement extends AppCompatActivity {

    Button add,del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_placement);

        add = findViewById(R.id.maddplacement);
        del = findViewById(R.id.mdelplacement);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddPlacement.class);
                startActivity(i);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Placement_company_list.class);
                i.putExtra("mode",2);
                startActivity(i);
            }
        });
    }
}
