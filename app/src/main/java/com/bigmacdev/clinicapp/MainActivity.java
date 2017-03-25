package com.bigmacdev.clinicapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button submit;

    private String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Client client = new Client("10.0.0.16",8088);

        //---------Load Balancer-----

        //----------------------------

        username = (EditText)findViewById(R.id.mainUserNameET);
        password = (EditText)findViewById(R.id.mainPasswordET);
        submit = (Button)findViewById(R.id.mainSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().length()<1){
                    Toast.makeText(MainActivity.this, "Missing Username!", Toast.LENGTH_SHORT).show();
                } else{
                    if(password.getText().toString().length()<1){
                        Toast.makeText(MainActivity.this, "Missing Password!", Toast.LENGTH_SHORT).show();
                    } else{
                        if (login(username.getText().toString(), password.getText().toString())){
                            Bundle bundle = new Bundle();
                            //bundle.putSerializable();
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

    private boolean login(String user, String pass){
        return true;
    }
}
