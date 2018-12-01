package com.dark.amarel.temanbelajar.activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dark.amarel.temanbelajar.FormPesanTentor;
import com.dark.amarel.temanbelajar.R;
import com.dark.amarel.temanbelajar.SessionManager;

import java.util.HashMap;

public class DetailTentor extends AppCompatActivity {

    TextView tv_name, tv_pendidikan, tv_mapel, tv_deskripsi, tv_pengalaman,tv_prestasi;
    ImageView img;
    Button pesan;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tentor);

        sessionManager= new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();

        final String id_murid = user.get(sessionManager.ID_MURID);

        //getSupportActionBar().hide();

        //getSupportActionBar().hide();

        String nama = getIntent().getExtras().getString("nama_guru");
        String pendidikan = getIntent().getExtras().getString("pendidikan");
        String nama_mapel = getIntent().getExtras().getString("nama_mapel");
        String deskripsi = getIntent().getExtras().getString("deskripsi");
        String pengalaman = getIntent().getExtras().getString("pengalaman");
        String foto_guru =  getIntent().getExtras().getString("foto_guru");

        String prestasi = getIntent().getExtras().getString("prestasi");
        final String tarif = getIntent().getExtras().getString("tarif");
        final String telpon = getIntent().getExtras().getString("telpon");
        final String id_mengajar = getIntent().getExtras().getString("id_mengajar");



        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsetoolbar);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(nama);



        tv_name=findViewById(R.id.aa_rowname);
        tv_pendidikan= findViewById(R.id.aa_pendidikan);
        tv_mapel=findViewById(R.id.aa_namamapel);
        tv_deskripsi=findViewById(R.id.tentordeskripsi);
        tv_pengalaman= findViewById(R.id.aa_pengalaman);
        tv_prestasi=findViewById(R.id.aa_prestasi);
        pesan = findViewById(R.id.btn_pesantentor);
        img =findViewById(R.id.aa_thumbnail);

        tv_name.setText(nama);
        tv_pendidikan.setText(pendidikan);
        tv_mapel.setText(nama_mapel);
        tv_deskripsi.setText(deskripsi);
        tv_pengalaman.setText(pengalaman);
        tv_prestasi.setText(prestasi);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DetailTentor.this, FormPesanTentor.class);
                intent.putExtra("id_mengajar", id_mengajar);
                intent.putExtra("tarif", tarif);
                intent.putExtra("id_murid", id_murid);
                intent.putExtra("telpon", telpon);
                startActivity(intent);

            }
        });

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading);

        Glide.with(this).load(foto_guru).apply(requestOptions).into(img);


    }
}
