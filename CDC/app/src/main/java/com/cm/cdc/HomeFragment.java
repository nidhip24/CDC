package com.cm.cdc;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    SliderLayout sliderLayout;

    TextView info;
    VideoView v1,v2;

    Uri uri,uri2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final ImageButton imgbtn = getView().findViewById(R.id.img_v2);
        final ImageButton imgbtn2 = getView().findViewById(R.id.img_v1);

        sliderLayout = getView().findViewById(R.id.a);
        info = getView().findViewById(R.id.homeinfo);
//        v1 = getView().findViewById(R.id.vid1);
//        v2 = getView().findViewById(R.id.vid2);

        ScrollView ss = getView().findViewById(R.id.scrollview);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),VideoPlay.class);
                i.putExtra("code",0);
                startActivity(i);
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(),VideoPlay.class);
                i.putExtra("code",1);
                startActivity(i);
            }
        });
        info.setText(Html.fromHtml(getString(R.string.home_data)));

//        uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.vidone);
        uri2 = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.vidtwo);
//
//        v2.setVideoURI(uri2);
//        v1.setVideoURI(uri);
//
//        MediaController mediaController = new MediaController(getContext());
//        mediaController.setAnchorView(v2);
//        v2.setMediaController(mediaController);
//        //v2.start();
//
//        MediaController mediaController2 = new MediaController(getContext());
//        mediaController2.setAnchorView(v1);
//        v1.setMediaController(mediaController2);
        //v1.start();
//        imgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!v2.isPlaying()){
//                    //Toast.makeText(getActivity(),"aa3a2",Toast.LENGTH_SHORT).show();
//                    v2.start();
//                    imgbtn.setImageResource(R.drawable.ic_media_pause);
//                }else{
//                    //Toast.makeText(getActivity(),"aa4a",Toast.LENGTH_SHORT).show();
//                    v2.pause();
//                    imgbtn.setImageResource(R.drawable.ic_media_play);
//                }
//            }
//        });
//
//        imgbtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!v1.isPlaying()){
//                    Toast.makeText(getActivity(),"aa3a1",Toast.LENGTH_SHORT).show();
//                    v1.start();
//                    imgbtn2.setImageResource(R.drawable.ic_media_pause);
//                }else{
//                    Toast.makeText(getActivity(),"aa4a",Toast.LENGTH_SHORT).show();
//                    v1.pause();
//                    imgbtn2.setImageResource(R.drawable.ic_media_play);
//                }
//            }
//        });

        sliderLayout.setIndicatorAnimation(SliderLayout.Animations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();
    }

    private void setSliderViews() {

        URL u = new URL();
        String url = u.url+"img/";
        for (int i = 0; i <= 27; i++) {

            SliderView sliderView = new SliderView(getActivity());

            switch (i) {
                case 0:
                    sliderView.setImageUrl(url+"s1.JPG");
                    break;
                case 1:
                    sliderView.setImageUrl(url+"s2.JPG");
                    break;
                case 2:
                    sliderView.setImageUrl(url+"s3.JPG");
                    break;
                case 3:
                    sliderView.setImageUrl(url+"s4.JPG");
                    break;
                case 4:
                    sliderView.setImageUrl(url+"s5.JPG");
                    break;
                case 5:
                    sliderView.setImageUrl(url+"s6.JPG");
                    break;
                case 6:
                    sliderView.setImageUrl(url+"s7.JPG");
                    break;
                case 7:
                    sliderView.setImageUrl(url+"s8.JPG");
                    break;
                case 8:
                    sliderView.setImageUrl(url+"s9.JPG");
                    break;
                case 9:
                    sliderView.setImageUrl(url+"s10.JPG");
                    break;
                case 10:
                    sliderView.setImageUrl(url+"s11.JPG");
                    break;
                case 11:
                    sliderView.setImageUrl(url+"s12.JPG");
                    break;
                case 12:
                    sliderView.setImageUrl(url+"s13.JPG");
                    break;
                case 13:
                    sliderView.setImageUrl(url+"s14.JPG");
                    break;
                case 14:
                    sliderView.setImageUrl(url+"s15.JPG");
                    break;
                case 15:
                    sliderView.setImageUrl(url+"s16.JPG");
                    break;
                case 16:
                    sliderView.setImageUrl(url+"s17.JPG");
                    break;
                case 17:
                    sliderView.setImageUrl(url+"s18.JPG");
                    break;
                case 18:
                    sliderView.setImageUrl(url+"s19.JPG");
                    break;
                case 19:
                    sliderView.setImageUrl(url+"s20.JPG");
                    break;
                case 20:
                    sliderView.setImageUrl(url+"s21.JPG");
                    break;
                case 21:
                    sliderView.setImageUrl(url+"s22.JPG");
                    break;
                case 22:
                    sliderView.setImageUrl(url+"s23.JPG");
                    break;
                case 23:
                    sliderView.setImageUrl(url+"s24.JPG");
                    break;
                case 24:
                    sliderView.setImageUrl(url+"s25.JPG");
                    break;
                case 25:
                    sliderView.setImageUrl(url+"s26.JPG");
                    break;
                case 26:
                    sliderView.setImageUrl(url+"s27.JPG");
                    break;
                case 27:
                    sliderView.setImageUrl(url+"s28.JPG");
                    break;

            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            //sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

}
