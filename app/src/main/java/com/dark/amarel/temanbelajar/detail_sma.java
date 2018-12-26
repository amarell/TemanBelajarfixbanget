package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.dark.amarel.temanbelajar.activities.ListGuru;

public class detail_sma extends AppCompatActivity implements View.OnClickListener {
    CardView indo,inggris,mtk,biologi,fisika, kimia,sejarah;
    String id_indo="12";
    String id_inggris="13";
    String id_mtk="14";
    String id_biologi="15";
    String id_fisika="17";
    String id_kimia="16";
    String id_sejarah="20";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sma);

        indo=findViewById(R.id.sma_bahasa_indonesia);
        inggris=findViewById(R.id.sma_bahasa_inggris);
        mtk=findViewById(R.id.sma_matematika);
        biologi=findViewById(R.id.sma_biologi);
        kimia=findViewById(R.id.sma_kimia);
        fisika=findViewById(R.id.sma_fisika);
        sejarah=findViewById(R.id.sma_sejarah);

        indo.setOnClickListener(this);
        inggris.setOnClickListener(this);
        mtk.setOnClickListener(this);
        biologi.setOnClickListener(this);
        kimia.setOnClickListener(this);
        fisika.setOnClickListener(this);
        sejarah.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.sma_bahasa_indonesia: i=new Intent(this, ListGuru.class);i.putExtra("id", id_indo
            );startActivity(i);break;
            case R.id.sma_bahasa_inggris: i=new Intent(this, ListGuru.class);i.putExtra("id", id_inggris
            );startActivity(i);break;
            case R.id.sma_matematika: i=new Intent(this, ListGuru.class);i.putExtra("id", id_mtk
            );startActivity(i);break;
            case R.id.sma_biologi: i=new Intent(this, ListGuru.class);i.putExtra("id", id_biologi
            );startActivity(i);break;
            case R.id.sma_kimia: i=new Intent(this, ListGuru.class);i.putExtra("id", id_kimia
            );startActivity(i);break;
            case R.id.sma_fisika: i=new Intent(this, ListGuru.class);i.putExtra("id", id_fisika
            );startActivity(i);break;
            case R.id.sma_sejarah: i=new Intent(this, ListGuru.class);i.putExtra("id", id_sejarah
            );startActivity(i);break;
            default:break;
        }
    }
}
