package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.dark.amarel.temanbelajar.activities.ListGuru;

public class detail_materi_sekolah extends AppCompatActivity implements View.OnClickListener{
    CardView sd;
    int id_sd=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi_sekolah);

        sd = findViewById(R.id.sd);

        sd.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()){
            case R.id.sd: i=new Intent(this, ListGuru.class);i.putExtra("id", id_sd
                 );startActivity(i);break;


            default:break;
        }

    }
}
