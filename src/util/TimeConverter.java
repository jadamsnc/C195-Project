/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author malic
 */
public class TimeConverter {
    public static String getUTCTime(String date) {
        String dateTime[] = date.split(" ", 2);
        String dateSplit[] = dateTime[0].split("-", 3);
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        String time[] = dateTime[1].split(":", 3);
        int hour = Integer.parseInt(time[0]);
        int minute = Integer.parseInt(time[1]);
        LocalDateTime ldt = LocalDateTime.of(year, month, day, hour, minute); 
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss");
        return "Locale.getDefault().toString:" + Locale.getDefault().toString()
                    + "\n\n"
                    + "ZoneOffset.systemDefault:" + ZoneOffset.systemDefault()
                    + "\n\n"
                    + "Local Date and Time:" + customFormatter.format(locZdt)
                    + "\n\n"
                    + "UTC Date and Time:" + customFormatter.format(utcZdt);
    }
    
}
