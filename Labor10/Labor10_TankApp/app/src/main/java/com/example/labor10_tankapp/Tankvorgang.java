package com.example.labor10_tankapp;

import java.time.LocalDate;

public class Tankvorgang {
    private final long id;
    private LocalDate date;
    private double oldKm;
    private double newKm;
    private double liters;
    private double pricePerLiter;

    public Tankvorgang (LocalDate date, double oldKm, double newKm, double liters, double pricePerLiter) {
        this(-1, date, oldKm, newKm, liters, pricePerLiter);
    }

    public Tankvorgang (long id, LocalDate date, double oldKm, double newKm, double liters, double pricePerLiter) {
        this.id = id;
        this.date = date;
        this.oldKm = oldKm;
        this.newKm = newKm;
        this.liters = liters;
        this.pricePerLiter = pricePerLiter;
    }

    public Tankvorgang clone (long id) {
        return new Tankvorgang(id, date, oldKm, newKm, liters, pricePerLiter);
    }

    public long getId () {
        return id;
    }

    public LocalDate getDate () {
        return date;
    }

    public double getOldKm () {
        return oldKm;
    }

    public double getNewKm () {
        return newKm;
    }

    public double getLiters () {
        return liters;
    }

    public double getPricePerLiter () {
        return pricePerLiter;
    }
}
