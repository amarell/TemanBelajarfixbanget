package com.dark.amarel.temanbelajar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormPesanTentor extends AppCompatActivity {
   private TextView jumlah, jam, hari;
   private Button pesen;
   private int tarif;
   private String id_mengajar, telpon, id_murid;

    private ProgressBar loading;
    private static String URL_DAFTAR = "http://192.168.43.64/temanbelajar/transaksi.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pesan_tentor);

        id_mengajar= getIntent().getExtras().getString("id_mengajar");
        telpon = getIntent().getExtras().getString("telpon");
        id_murid = getIntent().getExtras().getString("id_murid");
        tarif = Integer.parseInt(getIntent().getExtras().getString("tarif"));

        jumlah=findViewById(R.id.Et_jml_pertemuan);
        jam=findViewById(R.id.Et_jam_pertemuan);
        hari=findViewById(R.id.Et_hari_pertemuan);
        pesen=findViewById(R.id.btn_transaksi);

        pesen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaksi();
            }
        });





    }

    private void transaksi() {
        /*loading.setVisibility(View.VISIBLE);
        pesen.setVisibility(View.GONE);*/


        int mJumlah = Integer.parseInt(this.jumlah.getText().toString().trim());
        final String mJam = this.jam.getText().toString().trim();
        final String mHari = this.hari.getText().toString().trim();
        int total = mJumlah*this.tarif;
        final String id_mengajar=this.id_mengajar;
        final String telpon = this.telpon;
        final String id_murid=this.id_murid;

       final String total_harga = String.valueOf(total);
       final String jml_pertemuan = String.valueOf(mJumlah);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DAFTAR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")){
                                Toast.makeText(FormPesanTentor.this, "Transaksi Berhasil", Toast.LENGTH_SHORT).show();
                                /*loading.setVisibility(View.GONE);
                                pesen.setVisibility(View.VISIBLE);*/
                            }

                        } catch (JSONException e) {
                            Toast.makeText(FormPesanTentor.this, "Register gagal " + e.toString(), Toast.LENGTH_SHORT).show();

                        }


                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FormPesanTentor.this, " gagal " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("jumlah_pertemuan", jml_pertemuan);
                params.put("id_murid", id_murid);
                params.put("id_mengajar", id_mengajar);
                params.put("harga_total", total_harga);
                params.put("hari_les", mHari);
                params.put("jam_les", mJam);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
