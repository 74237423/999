package cts.phase3.controller.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: CTS2018_Phase_III
 * @author: Lijie
 * @description: ${description}
 * @create: 2018-06-17 15:00
 **/
public class DateCompare {

    public String DatePlus(String date, int i) {
        Calendar calendar = Calendar.getInstance();

        Date day = null;

        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        try {
            day = (Date)df.parse(date);
        } catch (ParseException e) {
            e.getStackTrace();
        }

        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, i);

        day = calendar.getTime();

        return df.format(day);
    }

    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(new Date());
    }

    public String getDate2() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

}
