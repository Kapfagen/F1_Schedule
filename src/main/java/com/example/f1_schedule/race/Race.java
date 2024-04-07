package com.example.f1_schedule.race;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Race {

    private String country;
    private LocalDate startDate;
    private LocalDate endDate;
    private String track;
    private List<Event> events;

    public Race(String country, String dateRange, String track, List<Event> events) {
        this.country = country;
        this.track = track;
        this.events = events;
        parseDateRange(dateRange);
    }

    private void parseDateRange(String dateRange) {
        String[] dates = dateRange.split(" - ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM");

        LocalDate startDate = parseDate(dates[0]);
        LocalDate endDate = parseDate(dates[1]);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static LocalDate parseDate(String dateString) {
        dateString = dateString.replaceAll("(?<=\\d)(st|nd|rd|th)\\b", "");
        dateString += " 2024";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
        return LocalDate.parse(dateString, formatter);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        String result = "Race[";
        result += "country=" + country;
        result += ",startDate=" + startDate;
        result += ",endDate=" + endDate;
        result += ",track=" + track;
        result += ",events=" + events;
        result += "]";
        return result;
    }
}
