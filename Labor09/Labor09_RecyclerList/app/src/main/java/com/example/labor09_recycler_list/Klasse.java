package com.example.labor09_recycler_list;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Klasse implements Parcelable {
    private String name;
    private List<Schueler> schuelerList = new ArrayList<>();

    public Klasse (String name) {
        this(name, new ArrayList<>());
    }

    public Klasse (String name, List<Schueler> schuelerList) {
        this.name = name;
        this.schuelerList = schuelerList;
    }

    protected Klasse(Parcel in) {
        name = in.readString();
        in.readList(schuelerList, Schueler.class.getClassLoader());
    }

    public static final Creator<Klasse> CREATOR = new Creator<Klasse>() {
        @Override
        public Klasse createFromParcel(Parcel in) {
            return new Klasse(in);
        }

        @Override
        public Klasse[] newArray(int size) {
            return new Klasse[size];
        }
    };

    static List<Klasse> fromCSV (InputStream inputStream) {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines()
                    .map(s -> Schueler.fromCSV(s))
                    .collect(Collectors.groupingBy(Schueler::getKlasse, Collectors.toList()))
                    .entrySet().stream()
                    .map(e -> new Klasse(e.getKey(), e.getValue()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("Illegal resource!");
        }
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<Schueler> getSchuelerList () {
        return schuelerList;
    }

    public void setSchuelerList (List<Schueler> schuelerList) {
        this.schuelerList = schuelerList;
    }

    public void addSchueler (Schueler schueler) {
        this.schuelerList.add(schueler);
    }

    public void removeSchueler (Schueler schueler) {
        this.schuelerList.remove(schueler);
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klasse klasse = (Klasse) o;
        return Objects.equals(name, klasse.name);
    }

    @Override
    public int hashCode () {
        return Objects.hash(name);
    }

    @Override
    public String toString () {
        return name + " (" + schuelerList.size() + ")";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeList(schuelerList);
    }


}
