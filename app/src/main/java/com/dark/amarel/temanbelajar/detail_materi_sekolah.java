package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.dark.amarel.temanbelajar.activities.ListGuru;

public class detail_materi_sekolah extends AppCompatActivity implements View.OnClickListener{
    CardView sd,smp,sma,sbmptn;
    String id_sd="2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi_sekolah);

        sd = findViewById(R.id.sd);
        smp = findViewById(R.id.smp);
        sma = findViewById(R.id.sma);
        sbmptn = findViewById(R.id.sbmptn);

        sd.setOnClickListener(this);
        smp.setOnClickListener(this);
        sma.setOnClickListener(this);
        sbmptn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId()){
            case R.id.sd: i=new Intent(this, ListGuru.class);i.putExtra("id", id_sd
                 );startActivity(i);break;
            case R.id.smp: i=new Intent(this, detail_smp.class);startActivity(i);break;
            case R.id.sma: i=new Intent(this, detail_sma.class);startActivity(i);break;
            case R.id.sbmptn: i=new Intent(this, detail_sbmptn.class);startActivity(i);break;
            default:break;
        }

    }
}
