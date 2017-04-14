package com.bigmacdev.clinicapp;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Appointment implements Comparable<Appointment>, Serializable{
    private static final long serialVersionUID = 45687524L;

    private String first, last, reason;
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

    public Appointment(){}

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
}

