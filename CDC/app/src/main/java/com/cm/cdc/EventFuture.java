package com.cm.cdc;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventFuture.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventFuture#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventFuture extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Custom list
    private List<EventData> eventList = new ArrayList<EventData>();

    //customlist adapter
    CustomListEventAdapter adapter;

    // Progress dialog
    private ProgressDialog pDialog;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EventFuture() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventFuture.
     */
    // TODO: Rename and change types and number of parameters
    public static EventFuture newInstance(String param1, String param2) {
        EventFuture fragment = new EventFuture();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_future, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView listView =  getView().findViewById(R.id.eventlist);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        adapter = new CustomListEventAdapter(getActivity(), eventList);
        listView.setAdapter(adapter);

        makeJsonArrayRequest();

//        String name[] = {"Prof. Preethi Rao","Mr. Ashish Modi","Ms. Ninoshka D'silva","Ms. Manila","Rushabh Shah","Vaibhavi Oza","Ajeet Singh Bajwa","Vir Thaker","Sheetal Shetty","Libinsa Nadar","Preet shah","Kaiwal Patwa","Vaibhavi Pawar","Mayur Pandey","Shreyansh Shanghanvi"};
//        String post[] ={"Coordinator","Teacher In-charge","Teacher In-charge","Teacher In-charge","Chairperson","Chairperson","Chairperson","Vice Chairperson","Vice Chairperson","Registration HOD","Business Dev HOD","Business Dev HOD","Placement HOD","Internship HOS","Photography head"};
//        //URL u = new URL();
//        for(int i = 0;i<name.length;i++){
//            EventData t = new EventData(name[i],post[i],"google.com/");
//            eventList.add(t);
//        }
//        adapter = new CustomListEventAdapter(getActivity(), eventList);
//        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    private void makeJsonArrayRequest() {

        showpDialog();

        String url = new URL().url;
        url+="eventDetails.php";

        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("EventFuture", response.toString());

                try {
                    // Parsing json array response
                    // loop through each json object
                    for (int i = 0; i < response.length(); i++) {

                        JSONObject com = (JSONObject) response.get(i);

                        String cname = com.getString("cname");
                        String information = com.getString("info").trim();
                        String link = com.getString("link").trim();

                        EventData t = new EventData(cname,information,link);
                        eventList.add(t);
                        //idarray.add(id+"");
                        //array.add(cname);
                    }
                    adapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(),"Error: " + e.getMessage(),Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("EventFuture", "Error: " + error.getMessage());
                //Toast.makeText(getApplicationContext(),error.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),"NO Events available right now", Toast.LENGTH_SHORT).show();
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
