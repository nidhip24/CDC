package com.cm.cdc;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
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

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UpdateInternshipData extends AppCompatActivity {

    private String comapanyID,companyName;

    // Pdf request code.
    public int PDF_REQ_CODE = 1;

    //URI
    Uri uri;

    // Progress dialog
    private ProgressDialog pDialog;

    TextView sname,roll,gr,cname,cls;
    EditText feedbk;
    String uid;
    Button btn,uploadletter;

    private String s="";

    int upFlag = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_internship_data);

        s = createID();
        s = (String) s.subSequence(0,10);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        requestStoragePermission();

        sname = findViewById(R.id.mfname);
        roll = findViewById(R.id.mrollno);
        cls = findViewById(R.id.mclass);
        gr = findViewById(R.id.mgrno);
        cname = findViewById(R.id.mcname);
        btn = findViewById(R.id.mupdate);

        feedbk = findViewById(R.id.mfeedback);
        uploadletter = findViewById(R.id.mofferletter);

        makeJsonArrayRequestForUserInfo();

        Intent i = getIntent();
        comapanyID = i .getStringExtra("company-id");
        companyName = i .getStringExtra("company-name");
        cname.setText(companyName);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!feedbk.getText().toString().equals("") && upFlag==1){
                    makerequest();
                }else{
                    Toast.makeText(getApplicationContext(),"Fill all the details",Toast.LENGTH_SHORT).show();
                }
            }
        });

        uploadletter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableButton();
                requestStoragePermission();
                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);
            }
        });
    }

    //generate uniquecode
    public String createID(){
        try{
            return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
        }catch (Exception e){
            return "error";
        }
    }

    //enable buttons
    void enableBUutton(){
        btn.setEnabled(true);
        uploadletter.setEnabled(true);
    }

    //disable buttons
    void disableButton(){
        btn.setEnabled(false);
        uploadletter.setEnabled(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            uri = data.getData();

            // After selecting the PDF set PDF is Selected text inside Button.
            //tv.setText("PDF is Selected");

            convertintobytearray();
        }else{
            enableBUutton();
        }
    }

    //converts pdf into bytearray
    void convertintobytearray(){
        try{

            byte[] data = null;
            String path = FilePath.getPath(this, uri);
            File filessss = new File(path);

            try {

                data = FileUtils.readFileToByteArray(filessss);

            } catch (IOException e) {
                Toast.makeText(getApplicationContext(),"error 1 "+e,Toast.LENGTH_SHORT).show();
                //e.printStackTrace();

            }
            //Toast.makeText(getApplicationContext(),"yyyy",Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(),Base64.encodeToString(data, Base64.DEFAULT),Toast.LENGTH_SHORT).show();
            uploadfile(Base64.encodeToString(data, Base64.DEFAULT));

            //makerequest(bytes);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
        }
    }

    //uploading file
    void uploadfile(final String b){
        //Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_SHORT).show();

        final String finalFilename = s + "letter";
        final String temp = s;

        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"offerletterupload.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("done")){
                    Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                    enableBUutton();
                    uploadletter.setEnabled(false);
                    uploadletter.setText("uploaded");
                    upFlag=1;
                }else{
                    Toast.makeText(getApplicationContext(),"Upload failed",Toast.LENGTH_SHORT).show();
                    enableBUutton();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                enableBUutton();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("file", b);
                params.put("filename", finalFilename);
                params.put("id", temp);
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }

    void makerequest(){
        showpDialog();
        final String ttt = s;
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"updateInternshipData.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("done")){
                    Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Seems like you may have already applied for it",Toast.LENGTH_SHORT).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error"+error,Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("userID", uid);
                params.put("iid",comapanyID);
                params.put("feedback",feedbk.getText().toString().trim());
                params.put("letter", ttt + "letter.pdf");
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }

    //getting user details
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

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == 123) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
                //requestStoragePermission();
            }
        }
    }
}
