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
import com.cm.cdc.Home;
import com.cm.cdc.R;
import com.cm.cdc.URL;
import com.cm.cdc.UserData;

import java.util.HashMap;
import java.util.Map;

public class AddPlacement extends AppCompatActivity {


    EditText mcname,mdet,mlink;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_placement);

        mcname = findViewById(R.id.mcname);
        mdet = findViewById(R.id.minfo);
        mlink = findViewById(R.id.mlink);
        btn = findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mcname.getText().toString().trim();
                String det = mdet.getText().toString().trim();
                String link = mlink.getText().toString().trim();

                if(!name.equals("") && !det.equals("") && !link.equals("") ){
                    makerequest(name,det,link);
                }else{
                    Toast.makeText(getApplicationContext(),"One of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void makerequest(final String name, final String det,final String link){
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"placement.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("done")){
                    Toast.makeText(getApplicationContext(),"Placement details added",Toast.LENGTH_SHORT).show();
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
