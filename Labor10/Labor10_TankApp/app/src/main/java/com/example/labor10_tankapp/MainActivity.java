package com.example.labor10_tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick_btnAdd (View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void onClick_btnShow (View view) {
        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);
    }

    public void onClick_btnDelete (View view) {
        TankvorgangDAO.getInstance(this).deleteAllTankvorgaenge();
        tooltip(view, "Success!", Toast.LENGTH_SHORT);
    }

    static void tooltip (View view, String message, int length) {
        Toast.makeText(view.getContext(), message, length).show();
    }
}