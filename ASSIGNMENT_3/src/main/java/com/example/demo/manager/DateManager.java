package com.example.demo.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateManager {

    public Date convertStringToDate(String sDate1){
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date= formatDate.parse(sDate1);
            return date;
        } catch (ParseException e) {
            System.out.println("Wrong date format, please enter again (dd/MM/yyyy)");
        }
        return null;

    }

    public String convertDateToString(Date date){

        //DateFormat inputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

        return outputFormat.format(date);
    }

    public Date between(Date startInclusive, Date endExclusive) {
        long startMillis = startInclusive.getTime();
        long endMillis = endExclusive.getTime();
        long randomMillisSinceEpoch = ThreadLocalRandom
                .current()
                .nextLong(startMillis, endMillis);

        return new Date(randomMillisSinceEpoch);
    }

}