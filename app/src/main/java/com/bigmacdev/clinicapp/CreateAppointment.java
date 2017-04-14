package com.bigmacdev.clinicapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAppointment extends AppCompatActivity {

    private EditText first, last, day, month, year, minute, hour, reason;
    private Button submit;
    private Staff staff;
    private Practice clinic;
    private MicroPatient patient;
    private Appointment appointment;
    private Boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        appointment = new Appointment();

        first = (EditText)findViewById(R.id.firstAddApp);
        last = (EditText)findViewById(R.id.lastAddApp);
        day = (EditText)findViewById(R.id.dayAddApp);
        month = (EditText)findViewById(R.id.monthAddApp);
        year = (EditText)findViewById(R.id.yearAddApp);
        minute = (EditText)findViewById(R.id.minuteAddApp);
        hour = (EditText)findViewById(R.id.hourAddApp);
        reason = (EditText)findViewById(R.id.reasonAddApp);
        submit = (Button)findViewById(R.id.submitAddApp);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        clinic = (Practice)bundle.getSerializable("clinic");
        staff = (Staff)bundle.getSerializable("staff");
        if (bundle.containsKey("patient")){
            patient = (MicroPatient)bundle.getSerializable("patient");
            first.setText(patient.getFirst());
            last.setText(patient.getLast());
            appointment.setPath(patient.getLocation());
        } else{
            new AlertDialog.Builder(CreateAppointment.this)
                    .setTitle("Patient Information Alert")
                    .setMessage("Do you want to enter a non-All-Med patient?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do nothing
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            Bundle b = new Bundle();
                            b.putSerializable("clinic", clinic);
                            b.putSerializable("staff",staff);
                            intent.putExtras(b);
                            intent.setClass(CreateAppointment.this, PatientList.class);
                            startActivity(intent);
                        }
                    })
                    .show();
        }



        submit.setOnClickListener(submitClick);


    }

    private View.OnClickListener submitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if((!first.getText().toString().equals(""))&&(!last.getText().toString().equals(""))&&(!day.getText().toString().equals(""))&&
                    (!month.getText().toString().equals("")) && (!year.getText().toString().equals("")) && (!hour.getText().toString().equals("")) &&
                    (!minute.getText().toString().equals(""))){
                appointment.setFirst(first.getText().toString());
                appointment.setLast(last.getText().toString());
                appointment.setDay(Integer.parseInt(day.getText().toString()));
                appointment.setMonth(Integer.parseInt(month.getText().toString()));
                appointment.setYear(Integer.parseInt(year.getText().toString()));
                appointment.setHour(Integer.parseInt(hour.getText().toString()));
                appointment.setMinute(Integer.parseInt(minute.getText().toString()));
                appointment.setLocation(clinic.getPath());
                createApp(appointment);
                Intent intent = new Intent();
                Bundle b = new Bundle();
                b.putSerializable("clinic", clinic);
                b.putSerializable("staff", staff);
                intent.putExtras(b);
                intent.setClass(CreateAppointment.this, PatientSchedule.class);
                startActivity(intent);

            } else {
                Toast.makeText(CreateAppointment.this, "Missing Fields!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private boolean createApp(Appointment app){
        done = null;
        final String s = app.toJsonString();
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                String l = client.runRequest("makeApp:"+client.encryptData(s));
                done = Boolean.parseBoolean(l);
            }
        }.start();
        while(done==null){}
        return done;
    }
}
