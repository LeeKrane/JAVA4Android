package com.example.labor09_recycler_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Comparator;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Klasse klasse = getIntent().getParcelableExtra("KLASSEN_EXTRA");
        setTitle(klasse.getName());
        klasse.getSchuelerList().sort(Comparator.comparing(Schueler::getKatNr));
        SchuelerAdapter schuelerAdapter = new SchuelerAdapter(klasse.getSchuelerList());
        RecyclerView rv_schueler = findViewById(R.id.rv_schueler);
        rv_schueler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_schueler.setLayoutManager(layoutManager);
        rv_schueler.setAdapter(schuelerAdapter);
    }
}