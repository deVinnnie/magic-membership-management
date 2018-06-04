package be.mira.jongeren.administration.util.template_engine;

import be.mira.jongeren.administration.util.date.DateUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {

    public Date asDate(LocalDate localDate) {
        return DateUtils.asDate(localDate);
    }

    public LocalDate asLocalDate(Date date) {
        return DateUtils.asLocalDate(date);
    }
}
