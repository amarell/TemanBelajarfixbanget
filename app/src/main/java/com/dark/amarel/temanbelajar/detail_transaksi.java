package com.dark.amarel.temanbelajar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class detail_transaksi extends AppCompatActivity {
    TextView exnama, exstatusbayar, exstatusles, exstatusbooking, exhari, exjam, exjmlpertemuan;
    Button batal;
    ImageView img;
    String id_kursus, id_murid, token;
    SessionManager sessionManager;
    private static String URL_BATAL= "http://192.168.43.64/temanbelajar/batalpesan.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_murid = String.valueOf(user.get(sessionManager.ID_MURID));
        token = String.valueOf(user.get(sessionManager.TOKEN));

        id_kursus = getIntent().getExtras().getString("id_kursus");
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
        exstatusbooking.setText("Status pemesanan anda: "+statusbooking);
        exhari.setText("Hari les anda: "+hari);
        exjam.setText("Jam Les anda: "+jam);
        exjmlpertemuan.setText("Jumlah pertemuan Les anda sebanyak "+ jmlpertemuan+ " kali.");



        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading);

        Glide.with(this).load(fotoguru).apply(requestOptions).into(img);

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                batalPesan();
            }
        });

    }

    private void batalPesan(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_BATAL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject= new JSONObject(response);

                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(detail_transaksi.this, "Pemeasanan Berhasil Dibatalkan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(detail_transaksi.this, Dashboard.class);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            Toast.makeText(detail_transaksi.this, "terjadi kesalahan "+e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(detail_transaksi.this, "Periksa Koneksi anda "+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id_kursus);
                params.put("id_murid", id_murid);
                params.put("token", token);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
