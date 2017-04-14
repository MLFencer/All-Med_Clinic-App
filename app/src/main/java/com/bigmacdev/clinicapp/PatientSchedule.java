package com.bigmacdev.clinicapp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientSchedule extends AppCompatActivity implements AppointmentFragment.OnListFragmentInteractionListener{

    private CalendarView cal;
    private Button add;
    private Spinner menu;
    private AppointmentFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_schedule);

        //fragment = (AppointmentFragment)getFragmentManager().findFragmentById(R.id.scheduleFragment);
        fragment = (AppointmentFragment)getSupportFragmentManager().findFragmentById(R.id.scheduleFragment);
        cal = (CalendarView)findViewById(R.id.calendarView);
        add = (Button)findViewById(R.id.addSchedule);
        menu = (Spinner)findViewById(R.id.menuSchedule);

        cal.setDate(System.currentTimeMillis());
        int y, d, m;
        Date date = new Date();
        DateFormat mFormat = new SimpleDateFormat("MM");
        DateFormat yFormat = new SimpleDateFormat("yyyy");
        DateFormat dFormat = new SimpleDateFormat("dd");
        y = Integer.parseInt(yFormat.format(date));
        m = Integer.parseInt(mFormat.format(date));
        d = Integer.parseInt(dFormat.format(date));

        fragment.updateList(d,m,y);
        cal.setOnDateChangeListener(calListener);

    }

    private CalendarView.OnDateChangeListener calListener = new CalendarView.OnDateChangeListener() {
        @Override
        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
            fragment.updateList(dayOfMonth,month,year);
        }
    };


    @Override
    public void onListFragmentInteraction(Appointment item) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("app", item);
        intent.putExtras(bundle);
        intent.setClass(PatientSchedule.this, AppointmentView.class);
        startActivity(intent);
    }
}
