package com.dark.amarel.temanbelajar;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class detail_transaksi extends AppCompatActivity {
    TextView exnama, exstatusbayar, exstatusles, exstatusbooking, exhari, exjam, exjmlpertemuan;
    Button batal;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        String nama = getIntent().getExtras().getString("namaguru");
        String statusbayar = getIntent().getExtras().getString("statusbayar");
        String statusles = getIntent().getExtras().getString("statusles");
        String statusbooking = getIntent().getExtras().getString("statusbooking");
        String hari = getIntent().getExtras().getString("hari");
        String jam = getIntent().getExtras().getString("jam");
        String jmlpertemuan = getIntent().getExtras().getString("jumlahpertemuan");
        String fotoguru =getIntent().getExtras().getString("foto_guru");

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsetoolbar);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setTitle(nama);

        exnama=findViewById(R.id.aa_rowname);
        exstatusbayar=findViewById(R.id.aa_statusbayar);
        exstatusles=findViewById(R.id.aa_statusles);
        exstatusbooking=findViewById(R.id.aa_statusbooking);
        exhari=findViewById(R.id.aa_hari);
        exjam=findViewById(R.id.aa_jam);
        exjmlpertemuan=findViewById(R.id.jmlpertemuan);
        img =findViewById(R.id.aa_thumbnail);

        batal=findViewById(R.id.btn_batal);

        if (statusbooking.equals("Approved")){
            batal.setVisibility(View.GONE);
        }


        exnama.setText("nama tentor: "+nama);
        exstatusbayar.setText("Status bayar anda: "+statusbayar);
        exstatusles.setText("Status Les anda: "+ statusles);
        exstatusbooking.setText("Status pemesanan anda sudah "+statusbooking);
        exhari.setText("Hari les anda: "+hari);
        exjam.setText("Jam Les anda: "+jam);
        exjmlpertemuan.setText("Jumlah pertemuan Les anda sebanyak "+ jmlpertemuan+ " kali.");



        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading);

        Glide.with(this).load(fotoguru).apply(requestOptions).into(img);

    }
}
