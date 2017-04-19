package com.bigmacdev.clinicapp;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AppointmentView extends AppCompatActivity {

    private Appointment app;
    private Practice clinic;
    private Button view;
    private TextView name, date, reason;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_view);

        Bundle bundle = this.getIntent().getExtras();
        clinic = (Practice)bundle.getSerializable("clinic");
        app = (Appointment)bundle.getSerializable("app");

        Log.d("AppView", app.getName());

        view = (Button)findViewById(R.id.viewDataAppView);
        name = (TextView)findViewById(R.id.nameAppView);
        date = (TextView)findViewById(R.id.appointmentDateTimeAppView);
        reason = (TextView)findViewById(R.id.reasonAppView);

        name.setText(app.getName());
        date.setText(app.getMonth()+"/"+app.getDay()+"/"+app.getYear()+"  "+app.getHour()+":"+app.getMinute());
        reason.setText(app.getReason());
        app.setPath(clinic.getPath());

        view.setOnClickListener(viewClick);
    }

    private View.OnClickListener viewClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putSerializable("clinic", clinic);
            //b.putSerializable("appp", app);
            //Appointment temp = (Appointment) b.getSerializable("appp");
            //Log.d("AppView", temp.getName());
            intent.putExtra("path", app.getPath()+"/schedule/"+app.getYear()+"/"+app.getMonth()+"/"+app.getDay()+"/"+app.getLocation());
            intent.setClass(AppointmentView.this, PatientView.class);
            startActivity(intent);
        }
    };
}
