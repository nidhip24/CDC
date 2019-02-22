package com.cm.cdc;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowEventDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_event_details);

        Intent i = getIntent();
        String en = i.getStringExtra("ename");
        String dess = i.getStringExtra("des");
        final String link = i.getStringExtra("ll");

        TextView n = findViewById(R.id.ename);
        TextView d = findViewById(R.id.info);
        Button b = findViewById(R.id.button);

        if(en!=null)
            n.setText(en);
        if(dess!=null)
            d.setText(dess);
        if(link!=null) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                        startActivity(browserIntent);
                    }catch (Exception e){
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Link is broken",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
