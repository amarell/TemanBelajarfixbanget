package com.dark.amarel.temanbelajar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class form_profil extends AppCompatActivity {
    private static final String TAG = form_profil.class.getSimpleName();
    private ImageView back_logo;
    SessionManager sessionManager;
    String idmurid;
    EditText nama, email, telpon, jk, jenjang, alamat, sekolah;
    Button button;
    private static String URL_READ = "http://192.168.43.64/temanbelajar/getdetailuser.php";
    private static String URL_SAVE = "http://192.168.43.64/temanbelajar/saveuser.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_profil);


        sessionManager = new SessionManager(this);
        sessionManager.cekLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();

        idmurid = user.get(sessionManager.ID_MURID);

        nama = findViewById(R.id.nama);
        email = findViewById(R.id.email);
        telpon = findViewById(R.id.telpon);
        jk = findViewById(R.id.jk);
        jenjang = findViewById(R.id.jenjang);
        alamat = findViewById(R.id.alamat);
        sekolah = findViewById(R.id.sekolah);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mnama = nama.getText().toString().trim();
                String memail = email.getText().toString().trim();
                String mtelpon = telpon.getText().toString().trim();
                String mjk = jk.getText().toString().trim();
                String mjenjang = jenjang.getText().toString().trim();
                String malamat = alamat.getText().toString().trim();
                String msekolah = sekolah.getText().toString().trim();

                if (mnama.isEmpty()){
                    nama.setError("Nama Harus Diisi");
                    nama.requestFocus();
                    return;
                }

                if (memail.isEmpty()){
                    email.setError("Email harus Diisi");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(memail).matches()){
                    email.setError("Masukan Email Yang Valid");
                    email.requestFocus();
                    return;
                }

                if (mtelpon.isEmpty()){
                    telpon.setError("No. Hp Harus Diisi");
                    telpon.requestFocus();
                    return;
                }

                if (mjk.isEmpty()){
                    jk.setError("Confirm Password tidak boleh kosong");
                    jk.requestFocus();
                    return;
                }
                if (malamat.isEmpty()){
                    alamat.setError("Confirm Password tidak boleh kosong");
                    alamat.requestFocus();
                    return;
                }
                if (mjenjang.isEmpty()){
                    jenjang.setError("Confirm Password tidak boleh kosong");
                    jenjang.requestFocus();
                    return;
                }
                if (msekolah.isEmpty()){
                    sekolah.setError("Confirm Password tidak boleh kosong");
                    sekolah.requestFocus();
                    return;
                }


                saveEdit();
            }
        });


    }

    private void getUserDetail() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String exnama = object.getString("nama").trim();
                                    String exemail = object.getString("email").trim();
                                    String exhp = object.getString("hp").trim();
                                    String expassword = object.getString("password").trim();
                                    String exalamat = object.getString("alamat").trim();
                                    String exjenjang = object.getString("jenjang").trim();
                                    String exsekolah = object.getString("sekolah").trim();
                                    String exfoto = object.getString("foto").trim();
                                    String exjk = object.getString("jk").trim();

                                    nama.setText(exnama);
                                    email.setText(exemail);
                                    telpon.setText(exhp);
                                    alamat.setText(exalamat);
                                    jenjang.setText(exjenjang);
                                    sekolah.setText(exsekolah);
                                    jk.setText(exjk);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(form_profil.this, "Gagal " + e.toString(), Toast.LENGTH_SHORT).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(form_profil.this, "Gagal cuy " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", idmurid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getUserDetail();
    }

    private void saveEdit(){

        final String nama = this.nama.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String telpon = this.telpon.getText().toString().trim();
        final String jk = this.jk.getText().toString().trim();
        final String jenjang = this.jenjang.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();
        final String sekolah = this.sekolah.getText().toString().trim();

        HashMap<String, String> user = sessionManager.getUserDetail();


        final String token = String.valueOf(user.get(sessionManager.TOKEN));
        final String id= idmurid;

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving broo...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SAVE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success= jsonObject.getString("success");
                            if (success.equals("1")){
                                Toast.makeText(form_profil.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                sessionManager.createSession(nama, email, id, token);
                            }
                        } catch (JSONException e) {
                            progressDialog.dismiss();
                            e.printStackTrace();
                            Toast.makeText(form_profil.this, "error "+e.toString() , Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(form_profil.this, "error "+error.toString() , Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("nama", nama);
                params.put("telpon", telpon);
                params.put("jk", jk);
                params.put("jenjang", jenjang);
                params.put("alamat", alamat);
                params.put("sekolah", sekolah);
                params.put("id", id);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}


