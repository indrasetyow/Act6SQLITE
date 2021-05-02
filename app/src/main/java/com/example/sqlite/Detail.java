package com.example.sqlite;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail extends AppCompatActivity {
    static TextView tvnama,tvtelp;
    String id,nama,telp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        tvnama = findViewById(R.id.tvNamaKontak);
        tvtelp = findViewById(R.id.tvNomorTelepon);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        telp = getIntent().getStringExtra("telp");

        setTitle("Detail Data");
        tvnama.setText(nama);
        tvtelp.setText(telp);
    }
}
