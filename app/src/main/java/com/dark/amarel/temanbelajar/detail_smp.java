package com.dark.amarel.temanbelajar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class detail_smp extends AppCompatActivity {
    private ImageView back_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_smp);

        back_logo=findViewById(R.id.back_logo_smp);
        back_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detail_smp.this, detail_materi_sekolah.class);
                startActivity(intent); }
        });
    }
}
