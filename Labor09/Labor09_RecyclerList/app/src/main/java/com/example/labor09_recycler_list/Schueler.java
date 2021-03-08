package com.example.labor09_recycler_list;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Locale;

public class Schueler implements Parcelable {
    private int katNr;
    private String klasse;
    private String vorname;
    private String nachname;
    private char geschlecht;

    public Schueler (int katNr, String klasse, String vorname, String nachname, char geschlecht) {
        this.katNr = katNr;
        this.klasse = klasse;
        this.vorname = vorname;
        this.nachname = nachname;
        this.geschlecht = geschlecht;
    }

    protected Schueler(Parcel in) {
        katNr = in.readInt();
        klasse = in.readString();
        vorname = in.readString();
        nachname = in.readString();
        geschlecht = (char) in.readInt();
    }

    public static final Creator<Schueler> CREATOR = new Creator<Schueler>() {
        @Override
        public Schueler createFromParcel(Parcel in) {
            return new Schueler(in);
        }

        @Override
        public Schueler[] newArray(int size) {
            return new Schueler[size];
        }
    };

    public static Schueler fromCSV (String line) {
        String[] split = line.split(";");
        if (split.length != 5)
            throw new IllegalArgumentException("Corrupted CSV file!");
        return new Schueler(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4].charAt(0));
    }

    public int getKatNr () {
        return katNr;
    }

    public void setKatNr (int katNr) {
        this.katNr = katNr;
    }

    public String getKlasse () {
        return klasse;
    }

    public void setKlasse (String klasse) {
        this.klasse = klasse;
    }

    public String getVorname () {
        return vorname;
    }

    public void setVorname (String vorname) {
        this.vorname = vorname;
    }

    public String getNachname () {
        return nachname;
    }

    public void setNachname (String nachname) {
        this.nachname = nachname;
    }

    public char getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(char geschlecht) {
        this.geschlecht = geschlecht;
    }

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "%02d %s %s", katNr, vorname, nachname);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(katNr);
        dest.writeString(klasse);
        dest.writeString(vorname);
        dest.writeString(nachname);
        dest.writeInt((int) geschlecht);
    }
}
