package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sqlite.database.DBController;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;

public class TemanEdit extends AppCompatActivity {
    private TextInputEditText tNama,tTelp;
    private Button saveBtn;
    String id,nama,telp;
    DBController controller = new DBController(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);

        saveBtn = findViewById(R.id.savbtn);
        tNama = findViewById(R.id.editnama);
        tTelp = findViewById(R.id.edittelp);

        id = getIntent().getStringExtra("id");
        nama = getIntent().getStringExtra("nama");
        telp = getIntent().getStringExtra("telp");

        setTitle("Edit");
        tNama.setText(nama);
        tTelp.setText(telp);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tNama.getText().toString().equals("") || tTelp.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Data belum lengkap!",Toast.LENGTH_LONG).show();
                }else{
                    nama = tNama.getText().toString();
                    telp = tTelp.getText().toString();
                    HashMap<String,String> val = new HashMap<>();
                    val.put("id",id);
                    val.put("nama",nama);
                    val.put("telp",telp);
                    controller.updateData(val);
                    callHome();
                }
            }
        });
    }
    public void callHome(){
        Intent i = new Intent(TemanEdit.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
