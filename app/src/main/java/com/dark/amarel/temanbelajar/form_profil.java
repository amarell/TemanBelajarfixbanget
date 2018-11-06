package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class form_profil extends AppCompatActivity {
private ImageView back_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_profil);

    /*Spinner spinner = (Spinner) findViewById(R.id.spinner) ;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.jenjang, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        /*back_logo=findViewById(R.id.back_logo);
        back_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(form_profil.this, MainActivity.class);
                startActivity(intent);
            }
        });*/

    }
}
