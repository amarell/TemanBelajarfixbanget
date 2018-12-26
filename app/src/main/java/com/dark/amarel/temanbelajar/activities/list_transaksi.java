package com.dark.amarel.temanbelajar.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dark.amarel.temanbelajar.R;
import com.dark.amarel.temanbelajar.SessionManager;
import com.dark.amarel.temanbelajar.adapters.ListTransaksiAdapter;
import com.dark.amarel.temanbelajar.adapters.RecyclerViewAdapter;
import com.dark.amarel.temanbelajar.models.DataGuru;
import com.dark.amarel.temanbelajar.models.DataListTransaksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class list_transaksi extends AppCompatActivity {
    private static String URL="http://192.168.43.64/temanbelajar/listtransaksi.php";
    private List<DataListTransaksi> dataListTransaksi;
    private RecyclerView recyclerView;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_transaksi);

        dataListTransaksi= new ArrayList<>();
        recyclerView=findViewById(R.id.rv2);

        transaksiRequest();
    }

    private void transaksiRequest() {
        sessionManager= new SessionManager(this);

        HashMap<String, String> user = sessionManager.getUserDetail();

         String id_murid = String.valueOf(user.get(sessionManager.ID_MURID));
        final String token = String.valueOf(user.get(sessionManager.TOKEN));
       final  String id_murid2 = id_murid;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            if (success.equals("1")){
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    DataListTransaksi dataTransaksi =new DataListTransaksi();

                                    dataTransaksi.setId_kursus(object.getString("id_kursus"));
                                    dataTransaksi.setNama_tentor(object.getString("nama"));
                                    dataTransaksi.setTelpon_tentor(object.getString("telpon"));
                                    dataTransaksi.setStatus_bayar(object.getString("status_bayar"));
                                    dataTransaksi.setStatus_les(object.getString("status_les"));
                                    dataTransaksi.setNama_mapel(object.getString("nama_mapel"));
                                    dataTransaksi.setJumlah_pertemuan(object.getString("jumlah_pertemuan"));
                                    dataTransaksi.setHari_les(object.getString("hari_les"));
                                    dataTransaksi.setJam_les(object.getString("jam_les"));
                                    dataTransaksi.setStatus_booking(object.getString("status_booking"));
                                    dataTransaksi.setFoto_profil(object.getString("foto_profil"));

                                    dataListTransaksi.add(dataTransaksi);


                                }
                            }else if (success.equals("0")){
                            Toast.makeText(list_transaksi.this, "Belum Ada transaksi", Toast.LENGTH_SHORT).show();
                        }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        setuprecyclerview(dataListTransaksi);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id_murid2);
                params.put("token", token);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void setuprecyclerview(List<DataListTransaksi> listTransaksis) {

        ListTransaksiAdapter myadapter = new ListTransaksiAdapter(this, listTransaksis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
