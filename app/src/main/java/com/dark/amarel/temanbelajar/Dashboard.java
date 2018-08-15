package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView materi_sekolah, keterampilan, bahasa_asing, komputerisasi, musik, olahraga;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //defining card
        materi_sekolah = (CardView) findViewById(R.id.materi_sekolah);
        keterampilan = (CardView) findViewById(R.id.keterampilan);
        bahasa_asing = (CardView) findViewById(R.id.bahasa_asing);
        komputerisasi = (CardView) findViewById(R.id.komputerisasi);
        musik = (CardView) findViewById(R.id.musik);
        olahraga = (CardView) findViewById(R.id.olahraga);

        //add Click Listener to the cards
        materi_sekolah.setOnClickListener(this);
        keterampilan.setOnClickListener(this);
        bahasa_asing.setOnClickListener(this);
        komputerisasi.setOnClickListener(this);
        musik.setOnClickListener(this);
        olahraga.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()){
        case R.id.materi_sekolah: i = new Intent(this, detail_materi_sekolah.class);startActivity(i);break;
        case R.id.keterampilan: i = new Intent(this, detail_keterampilan.class);startActivity(i);break;
        case R.id.bahasa_asing: i = new Intent(this, detail_bahasa_asing.class);startActivity(i);break;
        case R.id.komputerisasi: i = new Intent(this, detail_komputerisasi.class);startActivity(i);break;
        case R.id.musik: i = new Intent(this, detail_musik.class);startActivity(i);break;
        case R.id.olahraga: i = new Intent(this, detail_olahraga.class);startActivity(i);break;
        default:break;
    }
    }
}
