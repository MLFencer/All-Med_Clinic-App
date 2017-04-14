package com.bigmacdev.clinicapp;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PatientSchedule extends AppCompatActivity implements AppointmentFragment.OnListFragmentInteractionListener{


    private Button add, look;
    private Spinner menu;
    private EditText day,month,year;
    private AppointmentFragment fragment;
    private Practice clinic;
    private Staff staff;
    private int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_schedule);

        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        staff =(Staff)bundle.getSerializable("staff");
        clinic = (Practice)bundle.getSerializable("clinic");

        fragment = (AppointmentFragment)getSupportFragmentManager().findFragmentById(R.id.scheduleFragment);
        add = (Button)findViewById(R.id.addSchedule);
        menu = (Spinner)findViewById(R.id.menuSchedule);
        look = (Button)findViewById(R.id.submitSchedule);
        day = (EditText)findViewById(R.id.daySchedule);
        month = (EditText)findViewById(R.id.monthSchedule);
        year = (EditText)findViewById(R.id.yearSchedule);

        look.setOnClickListener(lookClick);
        add.setOnClickListener(addClick);

        int y, d, m;
        Date date = new Date();
        DateFormat mFormat = new SimpleDateFormat("MM");
        DateFormat yFormat = new SimpleDateFormat("yyyy");
        DateFormat dFormat = new SimpleDateFormat("dd");
        y = Integer.parseInt(yFormat.format(date));
        m = Integer.parseInt(mFormat.format(date));
        d = Integer.parseInt(dFormat.format(date));

        fragment.updateList(d,m,y,clinic.getPath());


        ArrayList<String> managerList = new ArrayList<String>();
        managerList.add("");
        managerList.add("Add User");
        managerList.add("Schedule");
        managerList.add("Waiting Room");
        managerList.add("Exam Rooms");
        managerList.add("Patient List");

        ArrayList<String> menuList = new ArrayList<String>();
        menuList.add("");
        menuList.add("Schedule");
        menuList.add("Waiting Room");
        menuList.add("Exam Rooms");
        menuList.add("Patient List");

        final ArrayAdapter<String> managerMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, managerList);
        final ArrayAdapter<String> menuItems = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menuList);

        managerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        x = Integer.parseInt(staff.getLocation(2, 0).substring(0,1));

        if(x==1){
            menu.setAdapter(managerMenu);
        }else{
            menu.setAdapter(menuItems);
        }

        menu.setOnItemSelectedListener(menuSelected);



    }

    private AdapterView.OnItemSelectedListener menuSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putSerializable("staff", staff);
            b.putSerializable("clinic", clinic);
            intent.putExtras(b);
            if(x==1) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        intent.setClass(PatientSchedule.this, AddUser1.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(PatientSchedule.this, "Already Here", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        intent.setClass(PatientSchedule.this, WaitingRoom.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(PatientSchedule.this, ExamRooms.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(PatientSchedule.this, PatientList.class);
                        startActivity(intent);
                        break;
                }
            }else{
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        Toast.makeText(PatientSchedule.this, "Already Here", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        intent.setClass(PatientSchedule.this, WaitingRoom.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(PatientSchedule.this, ExamRooms.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(PatientSchedule.this, PatientList.class);
                        startActivity(intent);
                        break;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

    private View.OnClickListener lookClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int yy, mm, dd;
            if((!year.getText().toString().equals("")) && (!month.getText().toString().equals("")) && (!day.getText().toString().equals(""))) {
                yy = Integer.parseInt(year.getText().toString());
                mm = Integer.parseInt(month.getText().toString());
                dd = Integer.parseInt(day.getText().toString());
                fragment.updateList(dd, mm, yy, clinic.getPath());
            }
        }
    };

    private View.OnClickListener addClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putSerializable("clinic", clinic);
            b.putSerializable("staff", staff);
            intent.putExtras(b);
            intent.setClass(PatientSchedule.this, CreateAppointment.class);
            startActivity(intent);
        }
    };


    @Override
    public void onListFragmentInteraction(Appointment item) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("app", item);
        bundle.putSerializable("clinic",clinic);
        bundle.putSerializable("staff",staff);
        intent.putExtras(bundle);
        intent.setClass(PatientSchedule.this, AppointmentView.class);
        startActivity(intent);
    }
}
