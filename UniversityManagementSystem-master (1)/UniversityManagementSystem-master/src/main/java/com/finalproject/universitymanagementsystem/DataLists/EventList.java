package com.finalproject.universitymanagementsystem.DataLists;

import com.finalproject.primary.Event;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    private static List<Event> eventList = new ArrayList<>();

    public static void addEvent(Event event) {
        eventList.add(event);
    }

    public static void removeEvent(Event event) { eventList.remove(event); }

    public static List<Event> getEventList() { return eventList; }
}
