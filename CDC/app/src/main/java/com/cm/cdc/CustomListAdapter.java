package com.cm.cdc;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<TeamData> temaItems;

    public CustomListAdapter(Activity activity, List<TeamData> team) {
        this.activity = activity;
        this.temaItems = team;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        //Log.d("CustomListAdapter",position+"");
        if(position%2==0){
            return 0;
        }
        return 1;
    }

    @Override
    public int getCount() {
        return temaItems.size();
    }

    @Override
    public Object getItem(int location) {
        return temaItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // getting team data for the row
        TeamData m = temaItems.get(position);
        int pos = getItemViewType(position);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null){

            if(m.getPos()==0){
                convertView = inflater.inflate(R.layout.list_row, null);
            }else if(m.getPos()==1){
                convertView = inflater.inflate(R.layout.list_row_right, null);
            }

        }

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);


        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView post = (TextView) convertView.findViewById(R.id.post);
        ImageView img = (ImageView)  convertView.findViewById(R.id.thumbnail);

        // title
        title.setText(m.getTitle());

        // post
        post.setText(m.getPost());

        //image
        thumbNail.setImageUrl(m.getImg(), imageLoader);

        return convertView;
    }

}
