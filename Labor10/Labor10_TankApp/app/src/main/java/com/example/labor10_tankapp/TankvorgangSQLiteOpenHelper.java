package com.example.labor10_tankapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TankvorgangSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_TANKVORGANG = "tankvorgang";
    public static final String COLUMN_TV_ID = "tv_id";
    public static final String COLUMN_TV_DATE = "tv_date";
    public static final String COLUMN_TV_KM_OLD = "tv_km_old";
    public static final String COLUMN_TV_KM_NEW = "tv_km_new";
    public static final String COLUMN_TV_LITERS = "tv_liter";
    public static final String COLUMN_TV_PRICE_PER_LITER = "tv_price_per_liter";
    public static final String[] COLUMNS = {
                COLUMN_TV_ID,
                COLUMN_TV_DATE,
                COLUMN_TV_KM_OLD,
                COLUMN_TV_KM_NEW,
                COLUMN_TV_LITERS,
                COLUMN_TV_PRICE_PER_LITER
    };

    private static final String DATABASE_NAME = "tankvorgang.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table " + TABLE_TANKVORGANG + "("
            + COLUMN_TV_ID + " integer primary key autoincrement, "
            + COLUMN_TV_DATE + " date not null, "
            + COLUMN_TV_KM_OLD + " double not null, "
            + COLUMN_TV_KM_NEW + " double not null, "
            + COLUMN_TV_LITERS + " double not null, "
            + COLUMN_TV_PRICE_PER_LITER + " double not null"
            + ");";

    public TankvorgangSQLiteOpenHelper (@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TANKVORGANG);
        onCreate(db);
    }
}
