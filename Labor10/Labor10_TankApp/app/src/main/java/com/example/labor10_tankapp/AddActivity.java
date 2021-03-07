package com.example.labor10_tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    DatePicker datePicker;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        datePicker = findViewById(R.id.datePicker);
        LocalDate now = LocalDate.now();
        datePicker.init(now.getYear(), now.getMonthValue()-1, now.getDayOfMonth(), null);

        TankvorgangDAO.getInstance(this).getAllTankvorgaenge().stream()
                    .mapToDouble(Tankvorgang::getNewKm)
                    .max()
                    .ifPresent(d -> ((EditText) findViewById(R.id.editText_oldKm))
                            .setText(String.valueOf(d)));
    }

    public void onClick_btnSave (View view) {
        try {
            Tankvorgang tankvorgang = new Tankvorgang(
                    LocalDate.of(datePicker.getYear(), datePicker.getMonth()+1, datePicker.getDayOfMonth()),
                    Double.parseDouble(((EditText) findViewById(R.id.editText_oldKm)).getText().toString()),
                    Double.parseDouble(((EditText) findViewById(R.id.editText_newKm)).getText().toString()),
                    Double.parseDouble(((EditText) findViewById(R.id.editText_liters)).getText().toString()),
                    Double.parseDouble(((EditText) findViewById(R.id.editText_pricePerLiter)).getText().toString())
            );
            if (tankvorgang.getNewKm() < tankvorgang.getOldKm())
                MainActivity.tooltip(view, "New km must be more than old km!", Toast.LENGTH_LONG);
            else {
                TankvorgangDAO.getInstance(this).insertTankvorgang(tankvorgang);
                MainActivity.tooltip(view, "Success!", Toast.LENGTH_SHORT);
                finish();
            }
        } catch (NumberFormatException e) {
            MainActivity.tooltip(view, "Invalid or incomplete input!", Toast.LENGTH_LONG);
        }
    }
}