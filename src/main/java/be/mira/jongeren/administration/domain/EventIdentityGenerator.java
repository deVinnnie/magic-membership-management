package be.mira.jongeren.administration.domain;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

public class EventIdentityGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if(obj instanceof Event) {
            Event event = (Event) obj;
            String formattedDate = event.getDatum().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
            Long generatedId = Long.parseLong(formattedDate + "0");
            return generatedId;
        }
        else{
            throw new UnsupportedOperationException();
        }
    }
}