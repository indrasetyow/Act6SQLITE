package com.example.sqlite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlite.Detail;
import com.example.sqlite.MainActivity;
import com.example.sqlite.R;
import com.example.sqlite.TemanEdit;
import com.example.sqlite.database.DBController;
import com.example.sqlite.database.Teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context con;
    public TemanAdapter(ArrayList<Teman> listData){
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        con = parent.getContext();
        View v = inflater.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String id,nama,telp;

        DBController db = new DBController(con);
        nama = listData.get(position).getNama();
        id = listData.get(position).getId();
        telp = listData.get(position).getTelp();

        holder.nama.setTextColor(Color.BLUE);
        holder.nama.setTextSize(20);
        holder.nama.setText(nama);
        holder.telp.setText(telp);

        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pop = new PopupMenu(con,holder.card);
                pop.inflate(R.menu.popup_menu);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.lhtdata:
                                Intent d = new Intent(con, Detail.class);
                                d.putExtra("id",id);
                                d.putExtra("nama",nama);
                                d.putExtra("telp", telp);
                                con.startActivity(d);
                                break;

                            case R.id.editData:
                                Intent i = new Intent(con, TemanEdit.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nama);
                                i.putExtra("telp", telp);
                                con.startActivity(i);
                                break;

                            case R.id.delData:
                                HashMap<String,String> val = new HashMap<>();
                                val.put("id",id);
                                db.deleteData(val);
                                Intent in = new Intent(con, MainActivity.class);
                                con.startActivity(in);
                                break;
                        }
                        return true;
                    }
                });
                pop.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData != null ? listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView card;
        private TextView nama,telp;
        public TemanViewHolder(View v) {
            super(v);
            card = v.findViewById(R.id.card);
            nama = v.findViewById(R.id.textNama);
            telp = v.findViewById(R.id.textTelp);
        }


    }
}
