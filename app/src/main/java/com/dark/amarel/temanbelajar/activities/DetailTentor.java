package com.dark.amarel.temanbelajar.activities;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dark.amarel.temanbelajar.R;
public class DetailTentor extends AppCompatActivity {

    TextView tv_name, tv_pendidikan, tv_mapel, tv_deskripsi, tv_pengalaman;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tentor);

        //getSupportActionBar().hide();

        //getSupportActionBar().hide();

        String nama = getIntent().getExtras().getString("nama_guru");
        String pendidikan = getIntent().getExtras().getString("pendidikan");
        String nama_mapel = getIntent().getExtras().getString("nama_mapel");
        String deskripsi = getIntent().getExtras().getString("deskripsi");
        String pengalaman = getIntent().getExtras().getString("pengalaman");
        String foto_guru =  getIntent().getExtras().getString("foto_guru");



        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsetoolbar);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(nama);



        tv_name=findViewById(R.id.aa_rowname);
        tv_pendidikan= findViewById(R.id.aa_pendidikan);
        tv_mapel=findViewById(R.id.aa_namamapel);
        tv_deskripsi=findViewById(R.id.tentordeskripsi);
        tv_pengalaman= findViewById(R.id.aa_pengalaman);
        img =findViewById(R.id.aa_thumbnail);

        tv_name.setText(nama);
        tv_pendidikan.setText(pendidikan);
        tv_mapel.setText(nama_mapel);
        tv_deskripsi.setText(deskripsi);
        tv_pengalaman.setText(pengalaman);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading);

        Glide.with(this).load(foto_guru).apply(requestOptions).into(img);


    }
}
