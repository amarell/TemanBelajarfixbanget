package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class login_murid extends AppCompatActivity {
    private Button mButtonStart;
    private EditText email, password;
    private Button btnlogin;
    private ProgressBar loading;
    private static String URL_LOGIN = "http://192.168.0.110/temanbelajar/login.php";
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_murid);

        sessionManager=new SessionManager(this);

        mButtonStart= (Button)findViewById(R.id.btn_daftar);
        email = findViewById(R.id.Et_email_login);
        password = findViewById(R.id.Et_pass_login);
        btnlogin = findViewById(R.id.btn_login);
        loading=findViewById(R.id.loading_login);

        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login_murid.this,daftar_aktivity.class);
                startActivity(intent);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (mEmail.isEmpty()){
                    email.setError("Email harus Diisi");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(mEmail).matches()){
                    email.setError("Masukan Email Yang Valid");
                    email.requestFocus();
                    return;
                }

                if (mPass.isEmpty()){
                    password.setError("Password harus diisi");
                    password.requestFocus();
                    return;
                }
                if (password.length()<6){
                    password.setError("Password minimal 6 karakter");
                    password.requestFocus();
                    return;
                }

                login(mEmail, mPass);
            }
        });
    }

    private void login(final String Em, final String Pass){
        loading.setVisibility(View.VISIBLE);
        btnlogin.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //dsfa
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nama = object.getString("nama").trim();
                                    String email = object.getString("email").trim();

                                    sessionManager.createSession(nama, email);

                                    Toast.makeText(login_murid.this, "Login Berhasil. \nNama Anda: "+nama+ "\nEmail anda: " + email, Toast.LENGTH_SHORT).show();
                                    loading.setVisibility(View.GONE);
                                    btnlogin.setVisibility(View.VISIBLE);
                                    Intent intent=new Intent(login_murid.this,Dashboard.class);
                                    intent.putExtra("name", nama);
                                    intent.putExtra("email", email);
                                    startActivity(intent);


                                }
                            }else if(success.equals("0")){
                                Toast.makeText(login_murid.this, "anda belum mendaftar", Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.GONE);
                                btnlogin.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(login_murid.this, "Gagal " + e.toString() , Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btnlogin.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login_murid.this, "Gagal bos " + error.toString() , Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", Em);
                params.put("password", Pass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
