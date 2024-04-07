package com.example.f1_schedule.race;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RaceDTO {

    private String country;
    private String date;
    private String track;
    private List<Event> events;
    private boolean isSaved;

    public RaceDTO(String country, String date, String track, List<Event> events, boolean isSaved) {
        this.country = country;
        this.date = date;
        this.track = track;
        this.events = events;
        this.isSaved = isSaved;
    }

    public RaceDTO(String country, LocalDate startDate, LocalDate endDate, String track, List<Event> events, boolean isSaved) {
        this.country = country;
        this.date = date;
        this.track = track;
        this.events = events;
        this.isSaved = isSaved;
    }

    public static String formatDateRange(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        String startFormatted = startDate.format(formatter);
        String endFormatted = endDate.format(formatter);

        if (startDate.getMonth().equals(endDate.getMonth())) {
            return startFormatted + " - " + endFormatted;
        } else {
            return startFormatted + " - " + endFormatted;
        }
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

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}
