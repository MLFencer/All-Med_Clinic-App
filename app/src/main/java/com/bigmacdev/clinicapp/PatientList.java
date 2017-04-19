package com.bigmacdev.clinicapp;

import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import net.maritimecloud.internal.core.javax.json.Json;

import java.io.StringReader;
import java.util.ArrayList;

public class PatientList extends AppCompatActivity {

    private ListView patientList;
    private TextView name, age;
    private Spinner menu;
    private Button create;

    private Boolean done = null;
    private Practice clinic;
    private ArrayList<String> pats;
    private ArrayList<String> nicePats;
    private Patient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);



        Bundle bundle = new Bundle();
        bundle = this.getIntent().getExtras();
        clinic = (Practice) bundle.getSerializable("clinic");

        patient = new Patient();
        pats= new ArrayList<String>();
        nicePats = new ArrayList<String>();
        getList();

        age = (TextView)findViewById(R.id.agePList);
        name = (TextView)findViewById(R.id.namePList);
        patientList = (ListView)findViewById(R.id.listPatients);
        menu = (Spinner)findViewById(R.id.menuPList);
        create = (Button)findViewById(R.id.schedulePList);

        age.setText("");
        name.setText("");
        patient=new Patient();
        create.setEnabled(false);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nicePats);
        patientList.setAdapter(adapter);

        patientList.setOnItemClickListener(patSelected);
        create.setOnClickListener(createClick);

    }

    private View.OnClickListener createClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            Bundle b = new Bundle();
            b.putSerializable("clinic", clinic);
            b.putSerializable("patient", patient);
            intent.putExtras(b);
            intent.setClass(PatientList.this, CreateAppointment.class);
            startActivity(intent);
        }
    };

    private AdapterView.OnItemClickListener patSelected = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String loc = pats.get(position);
            patient = getPatient(loc);
            name.setText(patient.getFullName());
            age.setText(patient.getDobM()+"/"+patient.getDobD()+"/"+patient.getDobY());
            create.setEnabled(true);
        }
    };


    private Patient getPatient(final String loc){
        done = null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                String out = client.runRequest("getPatientData:"+client.encryptData(loc));
                out = client.decryptData(out);
                out = client.decryptData(out);
                Log.d("List", "PatData: "+out);
                patient.loadData(Json.createReader(new StringReader(out)).readObject());
                done = true;
            }
        }.start();
        while(done==null){}
        return patient;
    }

    private void getList(){
       done=null;
        new Thread(){
            @Override
            public void run() {
                Client client = new Client();
                String list = client.decryptData(client.runRequest("getPats:"+client.encryptData(clinic.getPath())));
                int x = list.indexOf(";");
                while (list.length()!=x) {
                    x = list.indexOf(";");
                    if (x<=0){
                        x=list.length();
                    }
                    pats.add(list.substring(0, x));
                    if(x!=list.length()){
                        list=list.substring(x+1, list.length());
                    }
                }
                done=true;

            }
        }.start();

        while(done==null){}
        for (int i=0; i<pats.size(); i++){

            String whole= pats.get(i);
            if(whole.length()>3) {
                whole= whole.substring(10+clinic.getPath().length(),whole.length());
                Log.d("List", "Whole: "+whole);
                //Log.d("List", "");
                int x = whole.indexOf("_");
                String name = whole.substring(0, x);
                whole = whole.substring(x + 1, whole.length());
                x = whole.indexOf("_");
                name = whole.substring(0, x) + " " + name;
                nicePats.add(i, name);
            }
        }
    }
}
