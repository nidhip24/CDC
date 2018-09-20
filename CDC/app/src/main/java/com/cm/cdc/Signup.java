package com.cm.cdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText fname,rollno,grno,phno,eid;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fname = (EditText) findViewById(R.id.fname);
        rollno = (EditText) findViewById(R.id.rollno);
        grno = (EditText) findViewById(R.id.grno);
        phno = (EditText) findViewById(R.id.phno);
        eid = (EditText) findViewById(R.id.eid);
        btn = (Button)findViewById(R.id.next);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!fname.getText().toString().equals("") && !rollno.getText().toString().equals("") && !grno.getText().toString().equals("") &&!phno.getText().toString().equals("") && !eid.getText().toString().equals("")){
                    Intent i = new Intent(getApplicationContext(),Signup2.class);
                    i.putExtra("fname",fname.getText().toString());
                    i.putExtra("roll",rollno.getText().toString());
                    i.putExtra("grno",grno.getText().toString());
                    i.putExtra("phno",phno.getText().toString());
                    i.putExtra("eid",eid.getText().toString());
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
