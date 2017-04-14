package com.bigmacdev.clinicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button submit;

    private String temp;
    private Staff staff;
    private Boolean done;
    private Practice clinic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        staff = new Staff();

        username = (EditText)findViewById(R.id.usernameLogin);
        password = (EditText)findViewById(R.id.passwordLogin);
        submit = (Button)findViewById(R.id.submitLogin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this, "Missing Username!", Toast.LENGTH_SHORT).show();
                } else{
                    if(password.getText().toString().length()<1){
                        Toast.makeText(MainActivity.this, "Missing Password!", Toast.LENGTH_SHORT).show();
                    } else{
                        if (login()){
                            Bundle bundle = new Bundle();
                            getClinicData();
                            bundle.putSerializable("clinic", clinic);
                            bundle.putSerializable("staff", staff);
                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            temp = staff.getLocation(2, 0);
                            int access = Integer.parseInt(temp.substring(0,1));
                            intent.putExtra("access",access);
                            switch (access){
                                case 1:
                                    intent.setClass(MainActivity.this, PatientSchedule.class);
                                    break;
                                case 2:
                                    intent.setClass(MainActivity.this, PatientSchedule.class);
                                    break;
                                case 3:
                                    intent.setClass(MainActivity.this, WaitingRoom.class);
                                    break;
                                case 4:
                                    intent.setClass(MainActivity.this, WaitingRoom.class);
                                    break;
                            }
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Username or Password is Incorrect!", Toast.LENGTH_SHORT).show();
                            username.setText("");
                            password.setText("");
                        }
                    }
                }
            }
        });
    }

    private boolean login(){
        done = null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                Log.d("Main","Encryption Started");
                temp=client.runRequest("login:"+client.encryptData(username.getText().toString()));
                temp = client.decryptData(temp);
                Log.d("Main", temp);
                if(temp.equals("false")){
                    done = false;
                }else {
                    staff.loadData(temp);
                    try{
                        done = client.deHashPassword(staff.getPassword(), password.getText().toString()).equalsIgnoreCase(password.getText().toString());
                    }catch (Exception e){
                        done = false;
                    }

                }
            }
        }.start();
        while (done==null){}
        return done;
    }

    private void getClinicData(){
        done = null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                temp = client.runRequest("getClinic:"+client.encryptData(staff.getLocation(2,0).substring(1,staff.getLocation(2,0).length())));
                temp = client.decryptData(temp);
                clinic = new Practice();
                clinic.loadData(temp);
                done = true;
            }
        }.start();
        while(done==null){}
    }
}
