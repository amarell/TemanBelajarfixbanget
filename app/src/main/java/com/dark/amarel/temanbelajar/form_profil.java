package com.dark.amarel.temanbelajar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class form_profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_profil);

    Spinner spinner = (Spinner) findViewById(R.id.spinner) ;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.jenjang, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);


    }
}
