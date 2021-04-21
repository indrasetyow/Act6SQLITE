package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlite.adapter.TemanAdapter;
import com.example.sqlite.database.DBController;
import com.example.sqlite.database.Teman;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TemanAdapter adapter;
    private ArrayList<Teman> temanArrayList;
    private FloatingActionButton fab;
    DBController controller = new DBController(this);
    String id,nm,tlp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);
        readData();
        adapter = new TemanAdapter(temanArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TemanBaru.class);
                startActivity(i);
            }
        });
    }

    public void readData(){
        ArrayList<HashMap<String,String>> listTeman = controller.getAllTeman();
        temanArrayList = new ArrayList<>();
//        Pindah hasil query
        for (int i = 0; i < listTeman.size(); i++){
            Teman t = new Teman();
            t.setId(listTeman.get(i).get("id").toString());
            t.setNama(listTeman.get(i).get("nama").toString());
            t.setTelp(listTeman.get(i).get("telp").toString());
//            Masukkan ke arrayList
            temanArrayList.add(t);
        }
    }
}