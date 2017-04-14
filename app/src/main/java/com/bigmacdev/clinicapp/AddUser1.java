package com.bigmacdev.clinicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddUser1 extends AppCompatActivity {

    private Button submit, register;
    private EditText username, password;
    private Spinner menu, level;
    private Boolean done = null;
    private Boolean done2 = null;
    private String temp;
    private Staff newGuy, staff;
    private int x, lev;
    private Practice clinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user1);
        newGuy = new Staff();
        Bundle bundle = getIntent().getExtras();
        staff = (Staff)bundle.getSerializable("staff");
        clinic = (Practice)bundle.getSerializable("clinic");

        menu = (Spinner)findViewById(R.id.menuAddUser);
        submit = (Button)findViewById(R.id.submitAddUser);
        register = (Button)findViewById(R.id.newStaffAddUser);
        username = (EditText)findViewById(R.id.usernameAddUser);
        password = (EditText)findViewById(R.id.passwordAddUser);
        level = (Spinner)findViewById(R.id.levelAddUser);

        ArrayList<String> access = new ArrayList<String>();
        access.add("Manager");
        access.add("Receptionist");
        access.add("Doctor");
        access.add("Nurse");


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

        final ArrayAdapter<String> accessMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, access);
        final ArrayAdapter<String> managerMenu = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, managerList);
        final ArrayAdapter<String> menuItems = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, menuList);

        managerMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        menuItems.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accessMenu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        level.setAdapter(accessMenu);
        level.setOnItemSelectedListener(levelSelected);

        x = Integer.parseInt(staff.getLocation(2, 0).substring(0,1));

        if(x==1){
            menu.setAdapter(managerMenu);
        }else{
            menu.setAdapter(menuItems);
        }

        menu.setOnItemSelectedListener(menuSelected);

        submit.setOnClickListener(submitClick);
        register.setOnClickListener(registerClick);
    }

    private AdapterView.OnItemSelectedListener levelSelected = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            lev=position+1;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

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
                        Toast.makeText(AddUser1.this, "Already Here", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        intent.setClass(AddUser1.this, PatientSchedule.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(AddUser1.this, WaitingRoom.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(AddUser1.this, ExamRooms.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(AddUser1.this, PatientList.class);
                        startActivity(intent);
                        break;
                }
            }else{
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        intent.setClass(AddUser1.this, PatientSchedule.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(AddUser1.this, WaitingRoom.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(AddUser1.this, ExamRooms.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(AddUser1.this, PatientList.class);
                        startActivity(intent);
                        break;
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };

    private View.OnClickListener submitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(login()){
                newGuy.addLocation(lev+clinic.getPath(),2);
                clinic.addStaff(newGuy.getUsername());
                updateRecords();

            }
        }
    };

    private void updateRecords(){
        done=null;
        done2=null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                client.runRequest("updateClinic:"+client.encryptData(clinic.jsonToString(clinic.createJson())));
                done=true;
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                client.runRequest("updateLogin:"+client.encryptData(newGuy.jsonToString(newGuy.createJson())));
                done2=true;
            }
        }.start();
        while (done==null && done2==null){}
    }

    private View.OnClickListener registerClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(AddUser1.this, AddUser2.class);
            startActivity(intent);
        }
    };


    private boolean login(){
        done = null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                temp=client.runRequest("login:"+client.encryptData(username.getText().toString()));
                temp = client.decryptData(temp);
                if(temp.equals("false")){
                    done = false;
                }else {
                    newGuy.loadData(temp);
                    try{
                        done = client.deHashPassword(newGuy.getPassword(), password.getText().toString()).equalsIgnoreCase(password.getText().toString());
                    }catch (Exception e){
                        done = false;
                    }

                }
            }
        }.start();
        while (done==null){}
        return done;
    }
}
