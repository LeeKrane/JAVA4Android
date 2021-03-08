package com.example.labor09_recycler_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements KlassenOnClickListener {
    private final static int CSV_RESOURCE = R.raw.schueler_15_16;

    private KlassenAdapter klassenAdapter;
    private List<Klasse> klassen;
    private RecyclerView rv_klassen;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        klassen = Klasse.fromCSV(getResources().openRawResource(CSV_RESOURCE));
        klassen.sort(Comparator.comparing(Klasse::getName));
        klassenAdapter = new KlassenAdapter(klassen, this);
        rv_klassen = findViewById(R.id.rv_klassen);
        rv_klassen.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_klassen.setLayoutManager(layoutManager);
        rv_klassen.setAdapter(klassenAdapter);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("KLASSEN_EXTRA", klassen.get(position));
        startActivity(intent);
    }
}