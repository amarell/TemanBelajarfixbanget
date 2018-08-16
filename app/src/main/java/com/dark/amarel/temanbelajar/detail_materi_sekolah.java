package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class detail_materi_sekolah extends AppCompatActivity {
    private ImageView back_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_materi_sekolah);

        back_logo=findViewById(R.id.back_logo_materi_sekolah);
        back_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detail_materi_sekolah.this, Dashboard.class);
                startActivity(intent); }
        });
    }
}
