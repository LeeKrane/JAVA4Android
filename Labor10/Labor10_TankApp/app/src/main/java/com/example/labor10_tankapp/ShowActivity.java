package com.example.labor10_tankapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

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
        double pricePer100Km = tankvorgang.getLiters() * 100 * tankvorgang.getPricePerLiter()
                / (tankvorgang.getNewKm() - tankvorgang.getOldKm());

        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_price_per_100km)
                .setMessage(String.format(Locale.ENGLISH, "€ %.3f", pricePer100Km))
                .setNeutralButton("OK", (dialog, which) -> dialog.cancel())
                .create()
                .show();
    }
}