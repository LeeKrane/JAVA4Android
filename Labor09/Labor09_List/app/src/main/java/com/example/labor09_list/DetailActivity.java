package com.example.labor09_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static void setValues(View view, Klasse klasse) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        List<String> schueler = intent.getStringArrayListExtra(MainActivity.SCHUELER_EXTRA);
        ArrayAdapter<String> schuelerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, schueler);
        ListView lv_schueler = findViewById(R.id.lv_schueler);
        lv_schueler.setAdapter(schuelerAdapter);
    }
}