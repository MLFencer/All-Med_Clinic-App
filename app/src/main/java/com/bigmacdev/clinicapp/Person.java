package com.bigmacdev.clinicapp;


import java.io.Serializable;

public class Person implements Serializable{

    private static final long serialVersionUID = 1L;

    protected String fName="", mName="", lName="";
    protected int dobY=0, dobM=0, dobD=0;

    public Person(){}

    public Person(String f, String l, int y, int m, int d){
        fName=f;
        lName=l;
        dobY=y;
        dobM=m;
        dobD=d;
    }
    public Person(String f, String l, String mn, int y, int m, int d){
        fName=f;
        lName=l;
        dobY=y;
        dobM=m;
        dobD=d;
        mName=mn;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
    public String getmName() {return mName;}
    public String getfName(){
        return fName;
    }
    public int getDobY() {return dobY;}
    public int getDobM(){return dobM;}
    public int getDobD() {return dobD;}
    public String getlName() {return lName;}
    public String getName(){return lName+"_"+fName;}

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }


}
