package com.example.labor10_tankapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.labor10_tankapp.TankvorgangSQLiteOpenHelper.*;

public class TankvorgangDAO {
    private static TankvorgangDAO sInstance;

    private SQLiteDatabase database;
    private final TankvorgangSQLiteOpenHelper helper;

    public TankvorgangDAO (Context context) {
        helper = new TankvorgangSQLiteOpenHelper(context);
    }

    public static TankvorgangDAO getInstance (Context context) {
        if (sInstance == null) {
            sInstance = new TankvorgangDAO(context.getApplicationContext());
            sInstance.open();
        }
        return sInstance;
    }

    public void open () {
        database = helper.getWritableDatabase();
    }

    public void close() {
        helper.close();
        database = null;
    }

    public Tankvorgang insertTankvorgang (Tankvorgang tankvorgang) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_TV_DATE, tankvorgang.getDate().toEpochDay());
        values.put(COLUMN_TV_KM_OLD, tankvorgang.getOldKm());
        values.put(COLUMN_TV_KM_NEW, tankvorgang.getNewKm());
        values.put(COLUMN_TV_LITERS, tankvorgang.getLiters());
        values.put(COLUMN_TV_PRICE_PER_LITER, tankvorgang.getPricePerLiter());
        return tankvorgang.withId(database.insert(TABLE_TANKVORGANG, null, values));
    }

    public List<Tankvorgang> getAllTankvorgaenge () {
        List<Tankvorgang> tankvorgangList = new ArrayList<>();
        Cursor cursor = database.query(TABLE_TANKVORGANG, COLUMNS, null, null, null, null, null);

        cursor.moveToPosition(-1);
        while (cursor.moveToNext()) {
            tankvorgangList.add(new Tankvorgang(
                    cursor.getLong(cursor.getColumnIndex(COLUMN_TV_ID)),
                    LocalDate.ofEpochDay(cursor.getLong(cursor.getColumnIndex(COLUMN_TV_DATE))),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_TV_KM_OLD)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_TV_KM_NEW)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_TV_LITERS)),
                    cursor.getDouble(cursor.getColumnIndex(COLUMN_TV_PRICE_PER_LITER))
            ));
        }

        cursor.close();
        return tankvorgangList;
    }

    public void deleteAllTankvorgaenge () {
        database.execSQL("DELETE FROM " + TABLE_TANKVORGANG + ";");
    }

    public Optional<Double> getMaxKm () {
        String sql = "SELECT * FROM " + TABLE_TANKVORGANG +
                " ORDER BY " + COLUMN_TV_KM_NEW + " DESC LIMIT 1;";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToLast();

        if (cursor.getCount() == 0)
            return Optional.empty();
        Optional<Double> max = Optional.of(cursor.getDouble(cursor.getColumnIndex(COLUMN_TV_KM_NEW)));
        cursor.close();
        return max;
    }
}
