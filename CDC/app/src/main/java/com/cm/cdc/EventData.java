package com.cm.cdc;

public class EventData {

    String ename,edes,elink;

    public EventData(String en,String ed,String el){
        ename = en;
        edes = ed;
        elink = el;
    }

    public String getEname(){
        return ename;
    }

    public String getEdes(){
        return edes;
    }

    public String getElink(){
        return elink;
    }
}
