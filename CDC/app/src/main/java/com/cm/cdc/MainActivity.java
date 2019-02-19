package com.cm.cdc;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
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
import com.cm.cdc.admin.AdminC;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button signin,signup;
    boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"in signin...",Toast.LENGTH_SHORT).show();
                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if(!user.equals("") && !pass.equals("")){
                    makerequest(user,pass);
                    //saving userdata in sharedPrefrence
//                    UserData u = new UserData();
//                    u.saveUserData(getApplicationContext(),user);
//
//                    Intent intent=new Intent(getApplicationContext(),Home.class);
//                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"in signup...",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Signup.class);
                startActivity(i);
            }
        });
    }

    void makerequest(final String uu, final String pp){
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"logincdc1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("done")){
                    Toast.makeText(getApplicationContext(),"logging in...",Toast.LENGTH_SHORT).show();

                    UserData u = new UserData();
                    u.saveUserData(getApplicationContext(),uu);

                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }else if(response.trim().equals("admin")){
                    UserData u = new UserData();
                    u.saveUserData(getApplicationContext(),uu);

                    Intent intent=new Intent(getApplicationContext(),AdminC.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid username or or password",Toast.LENGTH_SHORT).show();
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
                params.put("uid", uu);
                params.put("pass",pp);
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
