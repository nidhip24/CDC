package com.cm.cdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup2 extends AppCompatActivity {

    Button reg2;
    String fname,rollno,grno,phno,eid;
    EditText clas , ssc ,hsc,sem1,sem2,sem3,sem4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        clas  = (EditText)findViewById(R.id.clas);
        ssc = (EditText)findViewById(R.id.ssc);
        hsc = (EditText)findViewById(R.id.hsc);
        sem1 = (EditText)findViewById(R.id.sem1);
        sem2 = (EditText)findViewById(R.id.sem2);
        sem3 = (EditText)findViewById(R.id.sem3);
        sem4 = (EditText)findViewById(R.id.sem4);
        reg2 = (Button)findViewById(R.id.reg2);

        Intent i = getIntent();
        fname = i.getStringExtra("fname");
        rollno = i.getStringExtra("roll");
        grno = i.getStringExtra("grno");
        phno = i.getStringExtra("phno");
        eid = i.getStringExtra("eid");

        reg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!clas.getText().toString().equals("") && !ssc.getText().toString().equals("") && !hsc.getText().toString().equals("") &&!sem1.getText().toString().equals("") && !sem2.getText().toString().equals("") &&!sem3.getText().toString().equals("") && !sem4.getText().toString().equals("")){
                    if(checkPercentage(ssc.getText().toString()) && checkPercentage(hsc.getText().toString()) && checkPercentage(sem1.getText().toString()) && checkPercentage(sem2.getText().toString()) && checkPercentage(sem3.getText().toString()) && checkPercentage(sem4.getText().toString()) ){
                        Intent i = new Intent(getApplicationContext(),Signup3.class);
                        i.putExtra("class",clas.getText().toString());
                        i.putExtra("ssc",ssc.getText().toString());
                        i.putExtra("hsc",hsc.getText().toString());
                        i.putExtra("sem1",sem1.getText().toString());
                        i.putExtra("sem2",sem2.getText().toString());
                        i.putExtra("sem3",sem3.getText().toString());
                        i.putExtra("sem4",sem4.getText().toString());
                        i.putExtra("fname",fname);
                        i.putExtra("rollno",rollno);
                        i.putExtra("grno",grno);
                        i.putExtra("phno",phno);
                        i.putExtra("eid",eid);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(),"Percentage cannot be greater than 100",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static boolean checkPercentage(String n){
        float nn = Float.parseFloat(n);
        return (nn<=100 && nn>=0);
    }
}
