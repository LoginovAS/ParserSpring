package org.sbx.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.enums.EnumDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class Service {

    private static final Logger logger = LogManager.getLogger(Service.class);

    public static Date stringToDate(String stringDate, String stringFormat){
        DateFormat dateFormat = new SimpleDateFormat(stringFormat);
        Date resultDate = null;
        try {
            resultDate = dateFormat.parse(stringDate);
        } catch (ParseException ex) {
            logger.error(ex);
        }

        return resultDate;
    }

    public static String dateToString(Date date){
        DateFormat dateFormat = new SimpleDateFormat(EnumDateFormat.DATABASE.getFormat());
        return dateFormat.format(date);
    }
}
