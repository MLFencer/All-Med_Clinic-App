package com.bigmacdev.clinicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import net.maritimecloud.internal.core.javax.json.Json;
import net.maritimecloud.internal.core.javax.json.JsonObject;

import java.io.StringReader;

public class PatientView extends AppCompatActivity {

    private Appointment app;
    private Practice clinic;
    private Patient patient;

    private Boolean done;

    private Button back, hist, script;
    private TextView name, email, cell, home, street, city, state, zip;

    private String loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        Bundle bundle = this.getIntent().getExtras();
        //app= (Appointment)bundle.getSerializable("appp");
        clinic = (Practice)bundle.getSerializable("clinic");
        loc = getIntent().getStringExtra("path");

        hist = (Button)findViewById(R.id.medHistPatView);
        back = (Button)findViewById(R.id.backPatView);
        script = (Button)findViewById(R.id.perscribePatView);
        name = (TextView)findViewById(R.id.namePatView);
        email = (TextView)findViewById(R.id.emailPatView);
        cell = (TextView)findViewById(R.id.cellPatView);
        home = (TextView)findViewById(R.id.homePatView);
        street = (TextView)findViewById(R.id.streetPatView);
        city = (TextView)findViewById(R.id.cityPatView);
        state = (TextView)findViewById(R.id.statePatView);
        zip = (TextView)findViewById(R.id.zipPatView);

        getPatient();

    }

    private View.OnClickListener scriptClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putSerializable("patient", patient);
            b.putSerializable("clinic", clinic);
            intent.putExtras(b);
            intent.setClass(PatientView.this, CreateScript.class);
            startActivity(intent);
        }
    };


    private void getPatient(){
        done=null;
        Thread x =new Thread(){
            @Override
            public void run() {
                try {
                    Client client = new Client();
                    String out = client.runRequest("getPatientData:" + client.encryptData(loc));
                    out = client.decryptData(out);
                    out = client.decryptData(out);
                    Log.d("PatientView", "PatData: " + out);
                    JsonObject jo = Json.createReader(new StringReader(out)).readObject();
                    Log.d("PatView", jo.containsKey("path")+"");
                    patient = new Patient();
                    patient.loadData(jo);
                    done = true;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        x.start();
        while(done==null){}
        name.setText(patient.getFullName());
        email.setText(patient.getEmail());
        cell.setText(patient.getCellNumber());
        home.setText(patient.getHomeNumber());
        street.setText(patient.getAddress());
        city.setText(patient.getCity());
        state.setText(patient.getState());
        zip.setText(patient.getZip());

    }
}
