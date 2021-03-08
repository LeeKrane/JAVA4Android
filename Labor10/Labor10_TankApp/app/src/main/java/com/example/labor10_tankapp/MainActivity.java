package com.example.labor10_tankapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;

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
        MainActivity mainActivity = this;
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_title_deletion)
                .setMessage(R.string.dialog_content_deletion)
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .setPositiveButton("Yes", (dialog, which) -> {
                    TankvorgangDAO.getInstance(mainActivity).deleteAllTankvorgaenge();
                    tooltip(view, "Success!", Toast.LENGTH_SHORT);
                })
                .create()
                .show();
    }

    static void tooltip (View view, String message, int length) {
        Toast.makeText(view.getContext(), message, length).show();
    }
}