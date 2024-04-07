package com.example.f1_schedule.race;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event {

    private String name;
    private LocalDate date;
    private String startTime;

    public Event(String name, String date, String startTime) {
        this.name = name;
        this.date = parseDate(date);
        this.startTime = startTime;
    }

    public static LocalDate parseDate(String dateString) {
        dateString = dateString.replaceAll("(?<=\\d)(st|nd|rd|th)\\b", "");
        dateString += " 2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        return LocalDate.parse(dateString, formatter);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        String result = "Event[";
        result += "name=" + name;
        result += ",date=" + date;
        result += ",startTime=" + startTime;
        result += "]";
        return result;
    }
}
