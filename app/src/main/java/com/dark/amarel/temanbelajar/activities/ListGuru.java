package com.dark.amarel.temanbelajar.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dark.amarel.temanbelajar.R;
import com.dark.amarel.temanbelajar.adapters.RecyclerViewAdapter;
import com.dark.amarel.temanbelajar.models.DataGuru;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListGuru extends AppCompatActivity {

    private static String URL="http://192.168.0.110/temanbelajar/coba.php";
    private List<DataGuru> listGuru;
    private RecyclerView recyclerView;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_guru);

        Intent intent = getIntent();
         id = intent.getStringExtra("id");

        listGuru = new ArrayList<>();

        recyclerView=findViewById(R.id.rv);
        guruRequest();



    }

    private void guruRequest() {
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
                                    DataGuru dataGuru = new DataGuru();

                                    dataGuru.setId(object.getInt("id"));
                                    dataGuru.setNama(object.getString("nama"));
                                    dataGuru.setPendidikan(object.getString("pendidikan"));
                                    dataGuru.setNama_mapel(object.getString("nama_mapel"));
                                    dataGuru.setDeskripsi(object.getString("deskripsi"));
                                    dataGuru.setPengalaman(object.getString("pengalaman"));
                                    dataGuru.setPrestasi(object.getString("prestasi"));
                                    dataGuru.setFoto_profil(object.getString("foto_profil"));

                                    listGuru.add(dataGuru);
                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        setuprecyclerview(listGuru);

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
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setuprecyclerview(List<DataGuru> listGuru) {

        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, listGuru);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
