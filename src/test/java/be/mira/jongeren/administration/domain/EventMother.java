package be.mira.jongeren.administration.domain;

import java.time.LocalDate;
import java.time.Month;

public class EventMother {

    public static Event createEvent(){
        LocalDate date = LocalDate.of(2015, Month.MARCH, 14);
        Event event = new Event(date, EventType.MAIN_SEQUENCE);
        return event;
    }

    public static Event createEarlyEvent(){
        LocalDate date = LocalDate.of(2015, Month.MARCH, 4);
        Event event = new Event(date, EventType.MAIN_SEQUENCE);
        return event;
    }

    public static Event createEventWithSingleParticipant() {
        Event.EventBuilder eventBuilder = new Event.EventBuilder();
        Person.PersonBuilder personBuilder = new Person.PersonBuilder();
        Event event = eventBuilder.build();

        event.addPartaking(
                new Partaking(
                        personBuilder.build(),
                        PartakingType.DEELNEMER,
                        event
                )
        );
        return event;
    }
}
