package com.example.labor10_tankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ShowActivity extends AppCompatActivity implements TankvorgangOnClickListener {
    private List<Tankvorgang> tankvorgaenge;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        tankvorgaenge = TankvorgangDAO.getInstance(this).getAllTankvorgaenge();
        TankvorgangAdapter adapter = new TankvorgangAdapter(tankvorgaenge, this);

        RecyclerView rv_tankvorgaenge = findViewById(R.id.rv_tankvorgaenge);
        rv_tankvorgaenge.setHasFixedSize(true);
        rv_tankvorgaenge.setLayoutManager(layoutManager);
        rv_tankvorgaenge.setAdapter(adapter);
    }

    @Override
    public void onClick (int position) {
        Tankvorgang tankvorgang = tankvorgaenge.get(position);
        // TODO:
        //  - Get View in here somehow,
        //  - Ask teacher for the meaning of "die zu diesem Eintrag durchnittlichen Benzinkosten pro 100km"
        MainActivity.tooltip(this, "", Toast.LENGTH_LONG);
    }
}