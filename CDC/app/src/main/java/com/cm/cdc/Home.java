package com.cm.cdc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int navItem = 0;

    int navidd=0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_TEAM = "team";
    private static final String TAG_P = "placement";
    private static final String TAG_EVENT= "event";
    private static final String TAG_FUTURE= "eventFuture";
    private static final String TAG_INTERN= "intern";
    private static final String TAG_FEEDBACK= "feedback";
    private static final String TAG_HISTORY= "history";
    public static String CURRENT_TAG = TAG_HOME;

    DrawerLayout drawer;
    private Handler mHandler;

    NavigationView navigationView;

    String activityTitles[] = {"Home","History","Team","Internship","Placement","Memory Lane","Future Events","Feedback"};
    String username;

    View navHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        makerequest();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mHandler = new Handler();

//        Intent ii = getIntent();
//        String na = ii.getStringExtra("open");

        //Toast.makeText(getApplicationContext(),"what i got "+na,Toast.LENGTH_SHORT).show();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navHeader = navigationView.getHeaderView(0);

        UserData u = new UserData();
        username = u.getUsername(getApplicationContext());
        if(username.equals("no")){
            Toast.makeText(getApplicationContext(),"Login again to continue...",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.addCategory( Intent.CATEGORY_HOME );
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }else{
//            TextView ut = navHeader.findViewById(R.id.usernamea);
//            ut.setText(username);
            makerequestforname();
        }


        if (savedInstanceState == null) {
            navItem = 0;
            CURRENT_TAG = TAG_HOME;
            loadFragment();
        }


    }


    private void hideItem()
    {
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_placement).setVisible(false);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItem]);
    }

    private void selectNavMenu() {
        try{
            navigationView.getMenu().getItem(navItem).setChecked(true);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.  onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        UserData u = new UserData();
        username = u.getUsername(getApplicationContext());
        if(username.equals("no")){
            //Toast.makeText(getApplicationContext(),"Login again to continue...",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.addCategory( Intent.CATEGORY_HOME );
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }
        makerequest();
        Intent ii = getIntent();
        String na = ii.getStringExtra("open");

        if(na!=null){
            if (na.equalsIgnoreCase("internship")){
                navItem = 3;
                CURRENT_TAG = TAG_INTERN;
            }else if (na.equalsIgnoreCase("event")){
                CURRENT_TAG = TAG_FUTURE;
                navItem = 6;
            }else if (na.equalsIgnoreCase("placement")){
                navItem = 4;
                CURRENT_TAG = TAG_P;
            }else{
                Intent ua = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(ua);
            }
            loadFragment();
        }

        //Toast.makeText(getApplicationContext(),"what i got "+na,Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        navidd = id;
        if (id == R.id.nav_home) {
            CURRENT_TAG = TAG_HOME;
            navItem = 0;
        }else if (id == R.id.nav_history) {
            CURRENT_TAG = TAG_HISTORY;
            navItem = 1;
        }else if (id == R.id.nav_team) {
            CURRENT_TAG = TAG_TEAM;
            navItem = 2;
        } else if (id == R.id.nav_intern) {
            navItem = 3;
            CURRENT_TAG = TAG_INTERN;
        } else if (id == R.id.nav_placement) {
            navItem = 4;
            CURRENT_TAG = TAG_P;
        } else if (id == R.id.nav_mem_lane) {
            CURRENT_TAG = TAG_EVENT;
            navItem = 5;
        } else if (id == R.id.nav_future) {
            CURRENT_TAG = TAG_FUTURE;
            navItem = 6;
        }  else if (id == R.id.nav_feedback) {
            navItem = 7;
            CURRENT_TAG = TAG_FEEDBACK;
        } else if (id == R.id.nav_logout) {
            Toast.makeText(getApplicationContext(),"Logout",Toast.LENGTH_SHORT).show();
            UserData u = new UserData();
            u.deleteUser(getApplication());
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        //Toast.makeText(getApplicationContext(),"no "+navItem,Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if(id != R.id.nav_logout)
            loadFragment();
        return true;
    }

    private void loadFragment(){
        // selecting appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        //Toast.makeText(getApplicationContext(),"ys"+navItem,Toast.LENGTH_SHORT).show();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }
        //Toast.makeText(getApplicationContext(),"yes",Toast.LENGTH_SHORT).show();
    }

    private Fragment getHomeFragment(){
        //Toast.makeText(getApplicationContext(),"future is here" + navItem,Toast.LENGTH_SHORT).show();
        switch (navItem) {

            case 0:
                // home
                return new HomeFragment();
            case 1:
                // placement
                return new History();
            case 2:
                // team
                return new Team();
            case 3:
                // home
                return new Internship();
            case 4:
                // home
                return new Placement();
            case 5:
                // home
                return new Event();
            case 6:
                // home
                //Toast.makeText(getApplicationContext(),"future is here",Toast.LENGTH_SHORT).show();
                return new EventFuture();
            case 7:
                // home
                return new Feedback();
            default:
                return new HomeFragment();
        }
    }

    void makerequest(){
        URL u = new URL();

        UserData userf = new UserData();
        username = userf.getUsername(getApplicationContext());
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"checkEligibleForPlacement.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(response.trim().equals("no")){
                    hideItem();
                }else{
                    //Toast.makeText(getApplicationContext(),"no 2 time",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uname", username);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }

    void makerequestforname(){
        URL u = new URL();

        UserData userf = new UserData();
        username = userf.getUsername(getApplicationContext());
        StringRequest s = new StringRequest(Request.Method.POST, u.url+"getname.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                if(!response.trim().equals("error")){
                    //hideItem();
                    TextView ut = navHeader.findViewById(R.id.usernamea);
                    ut.setText(response.trim());
                }else{
                    Toast.makeText(getApplicationContext(),"Login again to continue...",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    i.addCategory( Intent.CATEGORY_HOME );
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uname", username);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(s);
    }
}
