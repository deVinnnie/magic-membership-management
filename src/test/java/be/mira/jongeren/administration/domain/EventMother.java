package be.mira.jongeren.administration.domain;

import java.util.Calendar;

public class EventMother {

    public static Event createEvent(){
        Calendar c = Calendar.getInstance();
        c.set(2015, Calendar.MARCH,14);
        Event event = new Event(c.getTime(), EventType.MAIN_SEQUENCE);
        return event;
    }

    public static Event createEarlyEvent(){
        Calendar c = Calendar.getInstance();
        c.set(2015, Calendar.MARCH,4);
        Event event = new Event(c.getTime(), EventType.MAIN_SEQUENCE);
        return event;
    }
}
