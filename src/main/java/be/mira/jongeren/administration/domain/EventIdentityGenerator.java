package be.mira.jongeren.administration.domain;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EventIdentityGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if(obj instanceof Event) {
            Event event = (Event) obj;

            // Need Hibernate 5.2ish
            //Query query = session.createQuery("from Event as event where event.id = :id", Event.class);
            return generate(event.getDatum());
        }
        else{
            throw new UnsupportedOperationException();
        }
    }

    public Serializable generate(LocalDate date) {
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long generatedId = Long.parseLong(formattedDate + "0");
        return generatedId;
    }
}