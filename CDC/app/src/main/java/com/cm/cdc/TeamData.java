package com.cm.cdc;

public class TeamData {
    String title,post;

    //0 for left and 1 for right
    int pos;

    TeamData(String title,String post,int pos){
        this.title = title;
        this.post = post;
        this.pos = pos;
    }

    public String getTitle(){
        return title;
    }

    public int getPos(){
        return pos;
    }

    public String getPost(){
        return post;
    }
}
