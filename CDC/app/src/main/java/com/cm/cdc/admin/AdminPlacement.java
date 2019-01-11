package com.cm.cdc.admin;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
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
import com.cm.cdc.Home;
import com.cm.cdc.Placement_company_list;
import com.cm.cdc.R;
import com.cm.cdc.URL;
import com.cm.cdc.UserData;

import java.util.HashMap;
import java.util.Map;

public class AdminPlacement extends AppCompatActivity {

    Button add,del,mdownload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_placement);

        add = findViewById(R.id.maddplacement);
        del = findViewById(R.id.mdelplacement);
        mdownload = findViewById(R.id.mdownload);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddPlacement.class);
                startActivity(i);
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Placement_company_list.class);
                i.putExtra("mode",2);
                startActivity(i);
            }
        });

        mdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //downloadfile("placementdata.xlsx");
                makerequest();
            }
        });
    }

    void downloadfile(String filename){
        String url = new URL().url+"doc/"+filename;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setDescription("placement data");
        request.setTitle(filename);
        // in order for this if to run, you must use the android 3.2 to compile your app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "name-of-the-file.ext");

        // get download service and enqueue file
        DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    void makerequest(){
        URL u = new URL();
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"excel.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(!response.trim().equals("No result")){
                    Toast.makeText(getApplicationContext(),"downloading",Toast.LENGTH_SHORT).show();
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

}
