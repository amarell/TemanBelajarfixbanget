package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class Dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView materi_sekolah, keterampilan, bahasa_asing, komputerisasi, musik, olahraga;
    private TextView nama, email;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sessionManager= new SessionManager(this);
        sessionManager.cekLogin();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //defining card3
        materi_sekolah =  findViewById(R.id.materi_sekolah);
        keterampilan =  findViewById(R.id.keterampilan);
        bahasa_asing =  findViewById(R.id.bahasa_asing);
        komputerisasi =  findViewById(R.id.komputerisasi);
        musik =  findViewById(R.id.musik);
        olahraga =  findViewById(R.id.olahraga);

        //add Click Listener to the cards
        materi_sekolah.setOnClickListener(this);
        keterampilan.setOnClickListener(this);
        bahasa_asing.setOnClickListener(this);
        komputerisasi.setOnClickListener(this);
        musik.setOnClickListener(this);
        olahraga.setOnClickListener(this);

        nama = findViewById(R.id.namauser);
        email= findViewById(R.id.mailuser);

        HashMap<String, String> user = sessionManager.getUserDetail();

        String exNama=  user.get(sessionManager.NAME);
        String exEmail= user.get(sessionManager.EMAIL);

        nama.setText(exNama);
        email.setText(exEmail);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        if (id == R.id.action_profile) {
            Toast.makeText(this, "update profil", Toast.LENGTH_SHORT).show();
            intent = new Intent(this, form_profil.class);
            startActivity(intent);
            return true;
        }else if (id ==R.id.action_cart) {
            Toast.makeText(this, "daftar Pemesanan", Toast.LENGTH_SHORT).show();
            return true;
        }else if (id == R.id.action_logout) {
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
            sessionManager.logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
