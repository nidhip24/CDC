package com.cm.cdc;

public class TeamData {

    String title,post,img;
    //0 for left and 1 for right
    int pos;

    TeamData(String title,String post,int pos,String img){
        this.title = title;
        this.post = post;
        this.pos = pos;
        this.img = img;
    }

    public String getTitle(){
        return title;
    }

    public int getPos(){
        return pos;
    }

    public String getImg(){
        return img;
    }

    public String getPost(){
        return post;
    }
}
