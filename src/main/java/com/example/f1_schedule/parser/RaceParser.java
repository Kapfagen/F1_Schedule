package com.example.f1_schedule.parser;

import com.example.f1_schedule.race.Event;
import com.example.f1_schedule.race.Race;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RaceParser {

    public List<Race> get() throws IOException {
        Document doc = Jsoup.connect("https://www.skysports.com/f1/schedule-results").get();
        Elements races = doc.select("a.f1-races__race:has(.f1-races__race-inner .f1-races__race-cell .standing-table:not(.f1-races__table--no-head))");
        List<Race> upcomingRaces = new ArrayList<>();

        for (Element race : races) {
            String country = race.select(".f1-races__race-name").text();
            String date = race.select(".f1-races__race-date").first().text();
            String track = race.select(".f1-races__track path").attr("d");
            List<Event> events = parseEvents(race);

            Race raceObj = new Race(country, date, track, events);
            upcomingRaces.add(raceObj);
        }

        return upcomingRaces;
    }

    private static List<Event> parseEvents(Element race) {
        List<Event> events = new ArrayList<>();
        Elements eventRows = race.select("tbody .standing-table__row");

        for (Element row : eventRows) {
            String eventName = row.select(".standing-table__cell").get(1).text();
            String eventDate = row.select(".standing-table__cell").get(0).text();
            String eventStartTime = row.select(".standing-table__cell").get(3).text();

            events.add(new Event(eventName, eventDate, eventStartTime));
        }

        return events;
    }
}
