package com.bigmacdev.clinicapp;

import android.support.annotation.NonNull;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;
import net.maritimecloud.internal.core.javax.json.JsonObjectBuilder;

import java.io.Serializable;
import java.io.StringReader;

public class Appointment implements Comparable<Appointment>, Serializable{
    private static final long serialVersionUID = 45687524L;

    private String first, last, reason, path, location;
    private int hour, minute, day, month, year;

    public Appointment(String first, String last, String reason, int hour, int minute, int day, int month, int year){
        this.day=day;
        this.first=first;
        this.last=last;
        this.month=month;
        this.minute=minute;
        this.reason=reason;
        this.year=year;
        this.hour=hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Appointment(){}

    public void setPath(String s){this.path=s;}

    public String getPath(){return path;}

    public String getName(){
        return first+" "+last;
    }

    public String getTime(){
        int h;
        if (hour==12){
            h=12;
        }else{
            h=hour%12;
        }
        return h+":"+minute;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(Appointment other) {
        int h,oh,m,om;
        h=this.hour*60;
        oh=other.getHour()*60;
        m=h+this.minute;
        om=oh+other.getMinute();
        return m-om;
    }

    public String toJsonString(){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("first", first);
        job.add("last", last);
        job.add("day", day);
        job.add("month", month);
        job.add("year", year);
        job.add("hour", hour);
        job.add("minute", minute);
        job.add("path", path);
        job.add("clinicPath",location);
        return  job.build().toString();
    }

    public void loadData(String s){
        JsonObject jo = Json.createReader(new StringReader(s)).readObject();
        this.first=jo.getString("first");
        this.last=jo.getString("last");
        this.day=jo.getInt("day");
        this.month=jo.getInt("month");
        this.year=jo.getInt("year");
        this.minute=jo.getInt("minute");
        this.hour=jo.getInt("hour");
    }
}

