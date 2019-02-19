package com.cm.cdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
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

        fname = (EditText) findViewById(R.id.mfname);
        rollno = (EditText) findViewById(R.id.rollno);
        grno = (EditText) findViewById(R.id.grno);
        phno = (EditText) findViewById(R.id.phno);
        eid = (EditText) findViewById(R.id.eid);
        btn = (Button)findViewById(R.id.next);

        fname.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                if (fname.getText().toString().equals("")) {
                    fname.setError("Enter FirstName");
                } else {
                    fname.setError(null);
                }
            }
        });

        rollno.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                if (rollno.getText().toString().length() < 3) {
                    rollno.setError("Enter valid roll number");
                } else {
                    rollno.setError(null);
                }
            }
        });

        grno.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                if (grno.getText().toString().length() < 5) {
                    grno.setError("Enter valid GR number");
                } else {
                    grno.setError(null);
                }
            }
        });

        phno.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                if (phno.getText().toString().length() != 10) {
                    phno.setError("Enter 10 digit phone number");
                } else {
                    phno.setError(null);
                }
            }
        });

        eid.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                if (!isEmailValid(eid.getText().toString())) {
                    eid.setError("Enter valid emali id");
                } else {
                    eid.setError(null);
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !fname.getText().toString().equals("") && !rollno.getText().toString().equals("") && !grno.getText().toString().equals("") &&!phno.getText().toString().equals("") && !eid.getText().toString().equals("")){
                    int flag=-1;
                    if(!isEmailValid(eid.getText().toString())){
                        Toast.makeText(getApplicationContext(),"Invalid email id",Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                    if((phno.getText().toString().length())!=10){
                        Toast.makeText(getApplicationContext(),"Phone number must be of 10 digit",Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                    if(rollno.getText().toString().length() < 3){
                        Toast.makeText(getApplicationContext(),"Enter valid roll number",Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                    if(grno.getText().toString().length() < 5){
                        Toast.makeText(getApplicationContext(),"Enter valid GR number",Toast.LENGTH_SHORT).show();
                        flag=0;
                    }
                    if(flag==-1){
                        Intent i = new Intent(getApplicationContext(),Signup2.class);
                        i.putExtra("fname",fname.getText().toString());
                        i.putExtra("roll",rollno.getText().toString());
                        i.putExtra("grno",grno.getText().toString());
                        i.putExtra("phno",phno.getText().toString());
                        i.putExtra("eid",eid.getText().toString());
                        startActivity(i);
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static boolean isEmailValid(String e){
        return (!TextUtils.isEmpty(e) && Patterns.EMAIL_ADDRESS.matcher(e).matches());
    }
}
