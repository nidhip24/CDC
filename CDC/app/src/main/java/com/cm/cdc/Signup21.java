package com.cm.cdc;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Signup21 extends AppCompatActivity {

    // Pdf request code.
    public int PDF_REQ_CODE = 1;

    Button ssc,hsc,s1,s2,s3,s4,s5,next;
    int pSsc,pHsc,pS1,pS2,pS3,pS4,pS5;

    //URI
    Uri uri;

    protected String s;

    String fname,rollno,grno,phno,eid,clas,Mssc,Mhsc,sem1,sem2,sem3,sem4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup21);

        //request permission for storage access
        requestStoragePermission();

        pSsc = pHsc = pS1 = pS2 = pS3 = pS4 = pS5 = -1;

        ssc = findViewById(R.id.mSscbtn);
        hsc = findViewById(R.id.mHscbtn);
        s1 = findViewById(R.id.mSem1);
        s2 = findViewById(R.id.mSem2);
        s3 = findViewById(R.id.mSem3);
        s4 = findViewById(R.id.mSem4);
        s5 = findViewById(R.id.mSem5);
        next = findViewById(R.id.mNext);

        Intent i = getIntent();
        fname = i.getStringExtra("fname");
        rollno = i.getStringExtra("rollno");
        grno = i.getStringExtra("grno");
        phno = i.getStringExtra("phno");
        eid = i.getStringExtra("eid");
        clas  = i.getStringExtra("class");
        Mssc = i.getStringExtra("ssc");
        Mhsc = i.getStringExtra("hsc");
        sem1 = i.getStringExtra("sem1");
        sem2 = i.getStringExtra("sem2");
        sem3 = i.getStringExtra("sem3");
        sem4 = i.getStringExtra("sem4");

        //generating Unique ID
        s = createID();
        s = (String) s.subSequence(0,10);
        //Toast.makeText(getApplicationContext(),+" ." + s.length(),Toast.LENGTH_SHORT).show();

        ssc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pSsc = 0;
            }
        });

        hsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pHsc = 0;
            }
        });

        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pS1 = 0;
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pS2 = 0;
            }
        });
        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pS3 = 0;
            }
        });
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pS4 = 0;
            }
        });
        s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diableBUutton();

                Intent intent = new Intent();

                // Setting up default file pickup time as PDF.
                intent.setType("application/pdf");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                //uri = FileProvider.getUriForFile(mActivity, "my.authority.fileprovider", file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                pS5 = 0;
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pSsc == 1 && pHsc == 1 && pS1 == 1 && pS2 == 1 && pS3 == 1 && pS4 == 1 && pS5==1){
                    Toast.makeText(getApplicationContext(),"gooo",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),Signup3.class);
                    i.putExtra("class",clas);
                    i.putExtra("ssc",Mssc);
                    i.putExtra("hsc",Mhsc);
                    i.putExtra("sem1",sem1);
                    i.putExtra("sem2",sem2);
                    i.putExtra("sem3",sem3);
                    i.putExtra("sem4",sem4);
                    i.putExtra("fname",fname);
                    i.putExtra("rollno",rollno);
                    i.putExtra("grno",grno);
                    i.putExtra("phno",phno);
                    i.putExtra("eid",eid);
                    i.putExtra("docid",s);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Looks like some of the documents are not uploaded",Toast.LENGTH_SHORT).show();
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE:
                                    //Yes button clicked
                                    Toast.makeText(getApplicationContext(),"well you can't",Toast.LENGTH_SHORT).show();

                                    break;

                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(Signup21.this);
                    builder.setMessage("Are you sure? You want to continue?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
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

    void diableBUutton(){
        ssc.setEnabled(false);
        hsc.setEnabled(false);
        s1.setEnabled(false);
        s2.setEnabled(false);
        s3.setEnabled(false);
        s4.setEnabled(false);
        s5.setEnabled(false);
    }

    void enableBUutton(){
        ssc.setEnabled(true);
        hsc.setEnabled(true);
        s1.setEnabled(true);
        s2.setEnabled(true);
        s3.setEnabled(true);
        s4.setEnabled(true);
        s5.setEnabled(true);
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
            makerequest(Base64.encodeToString(data, Base64.DEFAULT));

            //makerequest(bytes);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
        }
    }

    void makerequest(final String b){
        //Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_SHORT).show();
        String filename = null;
        String wpdf= null;
        if(pSsc==0){
            filename = s+"ssc";
            wpdf = "ssc";
        }else if (pHsc==0){
            filename = s+"hsc";
            wpdf = "hsc";
        }else if (pS1==0){
            filename = s+"sem1";
            wpdf = "s1";
        }else if (pS2==0){
            filename = s+"sem2";
            wpdf = "s2";
        }else if (pS3==0){
            filename = s+"sem3";
            wpdf = "s3";
        }else if (pS4==0){
            filename = s+"sem4";
            wpdf = "s4";
        }else{
            filename = s+"sem5";
            wpdf = "s5";
        }
        final String finalFilename = filename;
        final String temp = s;
        final String finalWpdf = wpdf;
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"up.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.trim().equals("done")){
                    if(pSsc==0){
                        pSsc = 1;
                        ssc.setText("uploaded");
                        Toast.makeText(getApplicationContext(),"SSC pdf uploaded",Toast.LENGTH_SHORT).show();
                    }else if (pHsc==0){
                        pHsc = 1;
                        Toast.makeText(getApplicationContext(),"HSC pdf uploaded",Toast.LENGTH_SHORT).show();
                        hsc.setText("uploaded");
                    }else if (pS1==0){
                        pS1 = 1;
                        Toast.makeText(getApplicationContext(),"SEM1 pdf uploaded",Toast.LENGTH_SHORT).show();
                        s1.setText("uploaded");
                    }else if (pS2==0){
                        pS2 = 1;
                        Toast.makeText(getApplicationContext(),"SEM2 pdf uploaded",Toast.LENGTH_SHORT).show();
                        s2.setText("uploaded");
                    }else if (pS3==0){
                        pS3 = 1;
                        Toast.makeText(getApplicationContext(),"SEM3 pdf uploaded",Toast.LENGTH_SHORT).show();
                        s3.setText("uploaded");
                    }else if (pS4==0){
                        pS4 = 1;
                        Toast.makeText(getApplicationContext(),"SEM4 pdf uploaded",Toast.LENGTH_SHORT).show();
                        s4.setText("uploaded");
                    }else{
                        pS5 = 1;
                        Toast.makeText(getApplicationContext(),"SEM5 pdf uploaded",Toast.LENGTH_SHORT).show();
                        s5.setText("uploaded");
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Upload failed",Toast.LENGTH_SHORT).show();
                }
                enableBUutton();
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
                params.put("wpdf", finalWpdf);
                return params;

            }
        };
        AppController.getInstance().addToRequestQueue(s);
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
                requestStoragePermission();
            }
        }
    }
}
