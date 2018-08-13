package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class Home extends AppCompatActivity {
    private TextView nama, email;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager= new SessionManager(this);
        sessionManager.cekLogin();
        //nama.findViewById(R.id.namekeren);
        nama = findViewById(R.id.namekeren);
        email= findViewById(R.id.mail);

        HashMap<String, String> user = sessionManager.getUserDetail();

        String exNama=  user.get(sessionManager.NAME);
        String exEmail= user.get(sessionManager.EMAIL);

        nama.setText(exNama);
        email.setText(exEmail);
    }
}
