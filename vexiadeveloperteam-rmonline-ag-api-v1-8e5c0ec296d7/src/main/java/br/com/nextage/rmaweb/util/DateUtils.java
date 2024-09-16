package br.com.nextage.rmaweb.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static final LocalDate toLocalDate(Date dateToConvert) {

        if(dateToConvert == null) {
            return LocalDate.now(ZoneId.systemDefault());
        }
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static final String dateToStringWithFormatter(Date dateToConvert, String pattern) {

        if(dateToConvert instanceof java.sql.Date) {
            dateToConvert = new Date(dateToConvert.getTime());
        }

        if(pattern == null || pattern == "") {
            pattern = "dd/MM/yyyy";
        }

        final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(pattern);

        if(dateToConvert == null) {
            return LocalDate.now(ZoneId.systemDefault()).format(formatterDate);
        }

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate().format(formatterDate);
    }
}
