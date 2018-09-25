package com.cm.cdc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateData extends AppCompatActivity {

    private String comapanyID,companyName;

    // Progress dialog
    private ProgressDialog pDialog;

    TextView sname,roll,gr,cname,cls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Please wait...");
//        pDialog.setCancelable(false);
//
//        sname = findViewById(R.id.mfname);
//        roll = findViewById(R.id.mrollno);
//        cls = findViewById(R.id.mclass);
//        gr = findViewById(R.id.mgrno);
//        cname = findViewById(R.id.mcname);
//
//        makeJsonArrayRequestForUserInfo();
//
//        Intent i = getIntent();
//        comapanyID = i .getStringExtra("company-id");
//        companyName = i .getStringExtra("company-name");
//        cname.setText(companyName);
    }

    private void makeJsonArrayRequestForUserInfo() {

        showpDialog();

        String url = new URL().url;
        url+="getUserDetails.php?id="+comapanyID;

        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("Updatedata", response.toString());

                try {
                    String fname,rol,grn,clsss;
                    fname = rol = grn = clsss = "";
                    // Parsing json array response
                    // loop through each json object
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject com = (JSONObject) response.get(i);

                        fname = com.getString("fname");
                        rol = com.getString("rollno");
                        grn= com.getString("grno");
                        clsss = com.getString("clas");
                        //information = com.getString("info");
                        //link = com.getString("link");
                        //array.add(cname);
                    }
                    if(!fname.equals("-1") && !roll.equals("-1") && !gr.equals("-1") && !cls.equals("-1")){
                        sname.setText(fname);
                        roll.setText(rol);
                        gr.setText(grn);
                        cls.setText(clsss);
                        //set value to textview
                    }else{
                        Toast.makeText(getApplicationContext(),"Login again to continur...",Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(),Home.class);
                        startActivity(i);
                    }
                    //comp.setText(cname);
                    //info.setText(information);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("PlacementACtivity", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
