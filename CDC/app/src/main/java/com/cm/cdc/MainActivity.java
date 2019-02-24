package com.cm.cdc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cm.cdc.admin.AdminC;
import com.cm.cdc.app.Config;
import com.cm.cdc.util.NotificationUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button signin,signup;
    boolean doubleBackToExitPressedOnce = false;

    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;


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

        FirebaseMessaging.getInstance().subscribeToTopic("global")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "subscribed";
                        if (!task.isSuccessful()) {
                            msg = "subscribed failed";
                        }
                        Log.d(TAG, msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    Log.e("aaaaaaaaaaaa", "subscribed global                 qqqqqqqqqqqqqqqqqqqqqqqq");

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    Log.e("aaaaaaaaaaaa", "                 qqqqqqqqqqqqqqqqqqqqqqqq");
                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();

                    Log.e(TAG, "Message: " + message);
                }else {
                    Log.e("aaaaaaaaaaaa", "nonnnnnnnnnnnnnnnnnnnnnnnnn");
                }
            }
        };
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);
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

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        //NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
}
