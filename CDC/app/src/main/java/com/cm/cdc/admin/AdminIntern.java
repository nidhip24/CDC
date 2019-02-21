package com.cm.cdc.admin;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cm.cdc.AppController;
import com.cm.cdc.InternshipList;
import com.cm.cdc.Placement_company_list;
import com.cm.cdc.R;
import com.cm.cdc.URL;

public class AdminIntern extends AppCompatActivity {

    Button madd,mdel,mdownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_intern);

        madd = findViewById(R.id.minternadd);
        mdel = findViewById(R.id.mdelete);
        mdownload = findViewById(R.id.mdownload);

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add internship button
                Intent i = new Intent(getApplicationContext(),AddInternship.class);
                startActivity(i);
            }
        });

        mdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete internship button
                Intent i = new Intent(getApplicationContext(), InternshipList.class);
                i.putExtra("mode",2);
                startActivity(i);
            }
        });

        mdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //download internship data
                makerequest();
            }
        });
    }

    void downloadfile(String filename){

        //checking for permission
        requestStoragePermission();

        String url = new URL().url+"doc/"+filename;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("placement data");
        request.setTitle(filename);
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS.toString(), filename);

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    void makerequest(){
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"internexcel.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(!response.trim().equals("No result")){
                    Toast.makeText(getApplicationContext(),"Downloading...",Toast.LENGTH_SHORT).show();
                    downloadfile(response.trim());
                }else{
                    Toast.makeText(getApplicationContext(),"No result found",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
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
