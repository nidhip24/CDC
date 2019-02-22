package com.cm.cdc.admin;

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
import com.cm.cdc.AppController;
import com.cm.cdc.R;
import com.cm.cdc.URL;

import java.util.HashMap;
import java.util.Map;

public class AdminEvent extends AppCompatActivity {

    EditText ename,edes,elink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_event);
        ename = findViewById(R.id.eveName);
        edes = findViewById(R.id.description);
        elink = findViewById(R.id.mlink);

        Button b = findViewById(R.id.submit);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ename.getText().toString().equals("") && !edes.getText().toString().equals("") && !elink.getText().toString().equals("")){
                    makerequest(ename.getText().toString().trim(),edes.getText().toString().trim(),elink.getText().toString().trim());
                }else{
                    Toast.makeText(getApplicationContext(),"One of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void makerequest(final String name, final String det,final String link){
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"event.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("done")){
                    Toast.makeText(getApplicationContext(),"Event details added",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),AdminC.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Try again after some time...",Toast.LENGTH_SHORT).show();
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
                params.put("companyname", name);
                params.put("data",det);
                params.put("link",link);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }
}
