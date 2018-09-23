package com.cm.cdc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Signup3 extends AppCompatActivity {

    EditText uid,pass, cnfpass ,memno ;
    Button reg3;
    String fname,rollno,grno,phno,eid,clas,ssc,hsc,sem1,sem2,sem3,sem4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        uid = (EditText)findViewById(R.id.uid);
        pass = (EditText)findViewById(R.id.pass);
        cnfpass = (EditText)findViewById(R.id.cnfpass);
        memno = (EditText)findViewById(R.id.memno);
        reg3=(Button)findViewById(R.id.reg3);

        Intent i = getIntent();
        fname = i.getStringExtra("fname");
        rollno = i.getStringExtra("rollno");
        grno = i.getStringExtra("grno");
        phno = i.getStringExtra("phno");
        eid = i.getStringExtra("eid");
        clas  = i.getStringExtra("class");
        ssc = i.getStringExtra("ssc");
        hsc = i.getStringExtra("hsc");
        sem1 = i.getStringExtra("sem1");
        sem2 = i.getStringExtra("sem2");
        sem3 = i.getStringExtra("sem3");
        sem4 = i.getStringExtra("sem4");

        reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!uid.getText().toString().equals("") && !pass.getText().toString().equals("") && !cnfpass.getText().toString().equals("") &&!memno.getText().toString().equals("")){
                    makeRequest();
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    void makeRequest(){
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"registercdc.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("Done")){
                    Intent intent=new Intent(Signup3.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", uid.getText().toString());
                params.put("pass",pass.getText().toString());
                params.put("cnfpass",cnfpass.getText().toString());
                params.put("membership",memno.getText().toString());
                params.put("fname", fname);
                params.put("rollno", rollno);
                params.put("grno", grno);
                params.put("phno", phno);
                params.put("eid", eid);
                params.put("class", clas);
                params.put("ssc", ssc);
                params.put("hsc", hsc);
                params.put("sem1", sem1);
                params.put("sem2", sem2);
                params.put("sem3", sem3);
                params.put("sem4", sem4);

                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }
}
