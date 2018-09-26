package com.cm.cdc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.cm.cdc.admin.AdminC;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateData extends AppCompatActivity {

    private String comapanyID,companyName;

    // Progress dialog
    private ProgressDialog pDialog;

    TextView sname,roll,gr,cname,cls;
    EditText packsal,des;
    String uid;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        sname = findViewById(R.id.mfname);
        roll = findViewById(R.id.mrollno);
        cls = findViewById(R.id.mclass);
        gr = findViewById(R.id.mgrno);
        cname = findViewById(R.id.mcname);
        btn = findViewById(R.id.mupdate);
        packsal = findViewById(R.id.mpackage);
        des = findViewById(R.id.mdesignation);

        makeJsonArrayRequestForUserInfo();

        Intent i = getIntent();
        comapanyID = i .getStringExtra("company-id");
        companyName = i .getStringExtra("company-name");
        cname.setText(companyName);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!packsal.getText().toString().equals("") && !des.getText().toString().equals("")){
                    makerequest();
                }
            }
        });
    }

    private void makeJsonArrayRequestForUserInfo() {

        showpDialog();

        String url = new URL().url;
        UserData u = new UserData();
        String username = u.getUsername(getApplicationContext());
        url+="getUserDetails.php?user="+username;

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
                        uid = com.getString("id");
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

    void makerequest(){
        showpDialog();
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"updateData.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("done")){
                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Something went wrong try again later",Toast.LENGTH_SHORT).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", uid);
                params.put("pid",comapanyID);
                params.put("designation",des.getText().toString().trim());
                params.put("package",packsal.getText().toString().trim());
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(s);
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
