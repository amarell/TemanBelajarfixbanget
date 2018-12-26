package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.dark.amarel.temanbelajar.activities.ListGuru;

public class detail_smp extends AppCompatActivity implements View.OnClickListener {
    private ImageView back_logo;
    CardView indo, inggris, mtk;
    String id_indo="3";
    String id_inggris="4";
    String id_mtk="5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_smp);

        back_logo=findViewById(R.id.back_logo_smp);
        back_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detail_smp.this, detail_materi_sekolah.class);
                startActivity(intent); }
        });
        indo=findViewById(R.id.smp_bahasa_indonesia);
        inggris=findViewById(R.id.smp_bahasa_inggris);
        mtk=findViewById(R.id.smp_matematika);

        mtk.setOnClickListener(this);
        inggris.setOnClickListener(this);
        indo.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.smp_bahasa_indonesia: i=new Intent(this, ListGuru.class);i.putExtra("id", id_indo
            );startActivity(i);break;
            case R.id.smp_bahasa_inggris: i=new Intent(this, ListGuru.class);i.putExtra("id", id_inggris
            );startActivity(i);break;
            case R.id.smp_matematika: i=new Intent(this, ListGuru.class);i.putExtra("id", id_mtk
            );startActivity(i);break;
            case R.id.sma: i=new Intent(this, detail_sma.class);startActivity(i);break;
            case R.id.sbmptn: i=new Intent(this, detail_sbmptn.class);startActivity(i);break;
            default:break;
        }

    }
}
