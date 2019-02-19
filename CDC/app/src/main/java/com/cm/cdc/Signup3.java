package com.cm.cdc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

    EditText uid,pass, cnfpass ;//,memno ;
    Button reg3;
    String fname,rollno,grno,phno,eid,clas,ssc,hsc,sem1,sem2,sem3,sem4,sem5,docid;

    // Progress dialog
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        uid = (EditText)findViewById(R.id.uid);
        pass = (EditText)findViewById(R.id.pass);
        cnfpass = (EditText)findViewById(R.id.cnfpass);
        //memno = (EditText)findViewById(R.id.memno);
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
        sem5 = i.getStringExtra("sem5");
        docid = i.getStringExtra("docid");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Toast.makeText(getApplicationContext(),"sem 4 "+sem5,Toast.LENGTH_SHORT).show();

        uid.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                //Toast.makeText(getApplicationContext(),"a " + checkStringFun(uid.getText().toString(),"1234567890"),Toast.LENGTH_SHORT).show();
                if (uid.getText().toString().length()>=10 && uid.getText().toString().length()<=12 && checkStringFun(uid.getText().toString(),"abcdefghijklmnopqrstuvwxyz") == 1 && checkStringFun(uid.getText().toString(),"ABCDEFGHIJKLMNOPQRSTUVWXYZ")==1 && checkStringFun(uid.getText().toString(),"1234567890")==1 && checkStringFun(uid.getText().toString(),"!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")==1 ) {
                    uid.setError(null);
                } else {
                    uid.setError("Username must include 1 uppercase, 1 lowercase, 1 number, special character, minimum length must be 10 and max 12");
                }
            }
        });

        pass.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                //Toast.makeText(getApplicationContext(),"a " + checkStringFun(pass.getText().toString(),"1234567890"),Toast.LENGTH_SHORT).show();
                if (pass.getText().toString().length()>=10 && pass.getText().toString().length()<=12 && checkStringFun(pass.getText().toString(),"abcdefghijklmnopqrstuvwxyz") == 1 && checkStringFun(pass.getText().toString(),"ABCDEFGHIJKLMNOPQRSTUVWXYZ")==1 && checkStringFun(pass.getText().toString(),"1234567890")==1 && checkStringFun(pass.getText().toString(),"!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")==1 ) {
                    //Toast.makeText(getApplicationContext(),"aaa " + checkStringFun(pass.getText().toString(),"1234567890"),Toast.LENGTH_SHORT).show();
                    pass.setError(null);
                } else {
                    pass.setError("Password must include 1 uppercase, 1 lowercase, 1 number, special character, minimum length must be 10 and max 12");
                }
            }
        });

        cnfpass.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void afterTextChanged(Editable s)  {
                if (!cnfpass.getText().toString().equals(pass.getText().toString())) {
                    cnfpass.setError("Password does not match");
                } else {
                    cnfpass.setError(null);
                }
            }
        });

        reg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!uid.getText().toString().equals("") && !pass.getText().toString().equals("") && !cnfpass.getText().toString().equals("") ){
                    if(pass.getText().toString().equals(cnfpass.getText().toString()))
                        if(!uid.getText().toString().contains(" ") ){
                            if (uid.getText().toString().length()>=10 && uid.getText().toString().length()<=12 && checkStringFun(uid.getText().toString(),"abcdefghijklmnopqrstuvwxyz")==1 && checkStringFun(uid.getText().toString(),"ABCDEFGHIJKLMNOPQRSTUVWXYZ")==1 && checkStringFun(uid.getText().toString(),"1234567890")==1 && checkStringFun(uid.getText().toString(),"!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")==1 && pass.getText().toString().length()>=10 && pass.getText().toString().length()<=12 && checkStringFun(pass.getText().toString(),"abcdefghijklmnopqrstuvwxyz") == 1 && checkStringFun(pass.getText().toString(),"ABCDEFGHIJKLMNOPQRSTUVWXYZ")==1 && checkStringFun(pass.getText().toString(),"1234567890")==1 && checkStringFun(pass.getText().toString(),"!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~")==1){
                                makeRequest();
                            }else {
                                Toast.makeText(getApplicationContext(),"Enter correct username",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"Username must not contain any space",Toast.LENGTH_SHORT).show();
                        }
                    else
                        Toast.makeText(getApplicationContext(),"Password doesn't match",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"one of the field is empty",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    void makeRequest(){
        showpDialog();
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"registercdc.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("Done")){
                    Intent intent=new Intent(Signup3.this,MainActivity.class);
                    startActivity(intent);
                }else{

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
                params.put("uid", uid.getText().toString());
                params.put("pass",pass.getText().toString());
                params.put("cnfpass",cnfpass.getText().toString());
                //params.put("membership",memno.getText().toString());
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
                params.put("sem5", sem5);
                params.put("docid", docid);

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

    int checkStringFun(String s,String c){
        char[] ss = s.toCharArray();
        char[] cc = c.toCharArray();
        for(int i = 0;i<s.length();i++){
            for(int j = 0;j<c.length();j++){
                if(ss[i] == cc[j]) {
                    //Toast.makeText(getApplicationContext(),ss[i]+" = "+cc[j],Toast.LENGTH_SHORT).show();
                    return 1;
                }
            }
        }
        return 0;
    }
}
