package com.cm.cdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button reg2;
    String fname,rollno,grno,phno,eid;
    EditText clas , ssc ,hsc,sem1,sem2,sem3,sem4,sem5;
    int semNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sem, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        clas  = (EditText)findViewById(R.id.clas);
        ssc = (EditText)findViewById(R.id.ssc);
        hsc = (EditText)findViewById(R.id.hsc);
        sem1 = (EditText)findViewById(R.id.sem1);
        sem2 = (EditText)findViewById(R.id.sem2);
        sem3 = (EditText)findViewById(R.id.sem3);
        sem4 = (EditText)findViewById(R.id.sem4);
        sem5 = (EditText)findViewById(R.id.sem5);

        sem1.setVisibility(View.GONE);
        sem2.setVisibility(View.GONE);
        sem3.setVisibility(View.GONE);
        sem4.setVisibility(View.GONE);
        sem5.setVisibility(View.GONE);

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
                if (!clas.getText().toString().equals("") && !ssc.getText().toString().equals("") && !hsc.getText().toString().equals("") &&!sem1.getText().toString().equals("")){
                    int iflag=-1;
                    if (semNumber==1){
                        if(!sem1.getText().toString().equals("")){
                            if(checkPercentage(sem1.getText().toString())){
                                iflag=1;
                            }
                        }
                    }else if(semNumber==2){
                        if(!sem1.getText().toString().equals("") && !sem2.getText().toString().equals("")){
                            if(checkPercentage(sem1.getText().toString()) && checkPercentage(sem2.getText().toString())){
                                iflag=1;
                            }
                        }
                    }else if(semNumber==3){
                        if(!sem1.getText().toString().equals("") && !sem2.getText().toString().equals("") &&!sem3.getText().toString().equals("")){
                            if(checkPercentage(sem1.getText().toString()) && checkPercentage(sem2.getText().toString()) && checkPercentage(sem3.getText().toString())){
                                iflag=1;
                            }
                        }
                    }else if(semNumber==4){
                        if (!sem1.getText().toString().equals("") && !sem2.getText().toString().equals("") &&!sem3.getText().toString().equals("") && !sem4.getText().toString().equals("")){
                            if(checkPercentage(sem1.getText().toString()) && checkPercentage(sem2.getText().toString()) && checkPercentage(sem3.getText().toString()) && checkPercentage(sem4.getText().toString())){
                                iflag=1;
                            }
                        }
                    }else if(semNumber==5){
                        if (!sem1.getText().toString().equals("") && !sem2.getText().toString().equals("") &&!sem3.getText().toString().equals("") && !sem4.getText().toString().equals("") && !sem4.getText().toString().equals("")){
                            if(checkPercentage(sem1.getText().toString()) && checkPercentage(sem2.getText().toString()) && checkPercentage(sem3.getText().toString()) && checkPercentage(sem4.getText().toString()) && checkPercentage(sem5.getText().toString())){
                                iflag=1;
                            }
                        }
                    }
                    if(iflag==1 && checkPercentage(ssc.getText().toString()) && checkPercentage(hsc.getText().toString()) ){
                        Intent i = new Intent(getApplicationContext(),Signup21.class);
                        i.putExtra("class",clas.getText().toString());
                        i.putExtra("ssc",ssc.getText().toString());
                        i.putExtra("hsc",hsc.getText().toString());

                        switch (semNumber) {
                            case 1:
                                i.putExtra("sem1",sem1.getText().toString());
                                i.putExtra("sem2","-1");
                                i.putExtra("sem3","-1");
                                i.putExtra("sem4","-1");
                                i.putExtra("sem5","-1");
                                break;
                            case 2:
                                i.putExtra("sem1",sem1.getText().toString());
                                i.putExtra("sem2",sem2.getText().toString());
                                i.putExtra("sem3","-1");
                                i.putExtra("sem4","-1");
                                i.putExtra("sem5","-1");
                                break;
                            case 3:
                                i.putExtra("sem1",sem1.getText().toString());
                                i.putExtra("sem2",sem2.getText().toString());
                                i.putExtra("sem3",sem3.getText().toString());
                                i.putExtra("sem4","-1");
                                i.putExtra("sem5","-1");
                                break;
                            case 4:
                                i.putExtra("sem1",sem1.getText().toString());
                                i.putExtra("sem2",sem2.getText().toString());
                                i.putExtra("sem3",sem3.getText().toString());
                                i.putExtra("sem4",sem4.getText().toString());
                                i.putExtra("sem5","-1");
                                Toast.makeText(getApplicationContext(),"sem 4 ",Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                i.putExtra("sem1",sem1.getText().toString());
                                i.putExtra("sem2",sem2.getText().toString());
                                i.putExtra("sem3",sem3.getText().toString());
                                i.putExtra("sem4",sem4.getText().toString());
                                i.putExtra("sem5",sem5.getText().toString());
                                break;
                        }
                        i.putExtra("fname",fname);
                        i.putExtra("rollno",rollno);
                        i.putExtra("grno",grno);
                        i.putExtra("phno",phno);
                        i.putExtra("eid",eid);
                        startActivity(i);
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter Percentage between 35 & 100",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        switch (item){
            case "sem 1":
                sem1.setVisibility(View.VISIBLE);
                sem2.setVisibility(View.GONE);
                sem3.setVisibility(View.GONE);
                sem4.setVisibility(View.GONE);
                sem5.setVisibility(View.GONE);
                semNumber = 1;
                break;
            case "sem 2":
                sem1.setVisibility(View.VISIBLE);
                sem2.setVisibility(View.VISIBLE);
                sem3.setVisibility(View.GONE);
                sem4.setVisibility(View.GONE);
                sem5.setVisibility(View.GONE);
                semNumber = 2;
                break;
            case "sem 3":
                sem1.setVisibility(View.VISIBLE);
                sem2.setVisibility(View.VISIBLE);
                sem3.setVisibility(View.VISIBLE);
                sem4.setVisibility(View.GONE);
                sem5.setVisibility(View.GONE);
                semNumber = 3;
                break;
            case "sem 4":
                sem1.setVisibility(View.VISIBLE);
                sem2.setVisibility(View.VISIBLE);
                sem3.setVisibility(View.VISIBLE);
                sem4.setVisibility(View.VISIBLE);
                sem5.setVisibility(View.GONE);
                semNumber = 4;
                break;
            case "sem 5":
                sem1.setVisibility(View.VISIBLE);
                sem2.setVisibility(View.VISIBLE);
                sem3.setVisibility(View.VISIBLE);
                sem4.setVisibility(View.VISIBLE);
                sem5.setVisibility(View.VISIBLE);
                semNumber = 5;
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public static boolean checkPercentage(String n){
        float nn = Float.parseFloat(n);
        return (nn<=100 && nn>=35);
    }
}
