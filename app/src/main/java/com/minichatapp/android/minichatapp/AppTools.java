package com.minichatapp.android.minichatapp;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mery on 27/03/2018.
 */

public  class AppTools {



    public static String getCurrentDefaultLocaleStr(Context context) {
        Locale localelang= localelang = context.getResources().getConfiguration().locale;
        return Locale.getDefault().toString();
    }

    public static String getDateStringMessageText(Date date){
    SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");
    String returnDate = fmtOut.format(date);
    returnDate= printDifference(date,new Date())+" ago, "+returnDate;
    return returnDate;
}
    public static String printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        //elapsedDays+" days, "+elapsedHours+" hours, "+
        String mes="";
          if(elapsedMinutes==0){
            mes=elapsedSeconds+" seconds";
           }else{
            mes=elapsedMinutes+" minutes ";
        }
          return  mes;

    }

}
