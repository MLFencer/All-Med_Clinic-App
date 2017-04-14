package com.bigmacdev.clinicapp;

import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class PatientList extends AppCompatActivity {

    private ListView patientList;
    private TextView name, age;
    private Spinner menu;
    private Button create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

        age = (TextView)findViewById(R.id.agePList);
        name = (TextView)findViewById(R.id.namePList);
        patientList = (ListView)findViewById(R.id.listPatients);
        menu = (Spinner)findViewById(R.id.menuPList);
        create = (Button)findViewById(R.id.schedulePList);



    }
}
