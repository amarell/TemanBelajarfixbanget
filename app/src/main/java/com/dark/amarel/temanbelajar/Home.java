package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private TextView nama, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //nama.findViewById(R.id.namekeren);
        nama = findViewById(R.id.namekeren);
        email= findViewById(R.id.mail);

        Intent intent = getIntent();
        String exNama=  intent.getStringExtra("name");
        String exEmail= intent.getStringExtra("email");

        nama.setText(exNama);
        email.setText(exEmail);
    }
}
