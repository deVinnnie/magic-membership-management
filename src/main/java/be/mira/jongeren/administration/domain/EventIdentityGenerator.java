package be.mira.jongeren.administration.domain;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class EventIdentityGenerator extends IdentityGenerator {

    @Override
    public Serializable generate(SessionImplementor session, Object obj) {
        if(obj instanceof Event) {
            Event event = (Event) obj;

            SimpleDateFormat formatter = new SimpleDateFormat("YYYYMMdd");
            String formattedDate = formatter.format(event.getDatum());
            Long generatedId = Long.parseLong(formattedDate + "0");
            return generatedId;
        }
        else{
            throw new UnsupportedOperationException();
        }
    }
}