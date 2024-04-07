package com.example.f1_schedule.database;

import jakarta.persistence.*;

@Entity
@Table(name = "races")
public class RaceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String country;
    private String date;

    public RaceRecord() {
    }

    public RaceRecord(String country, String date) {
        this.country = country;
        this.date = date;
    }

    public RaceRecord(long id, String country, String date) {
        this.id = id;
        this.country = country;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
