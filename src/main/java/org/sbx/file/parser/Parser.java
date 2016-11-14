package org.sbx.file.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.messages.impl.ApplicationDebugMessage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aloginov on 01.11.16.
 */
public class Parser {
    /**
     *  The class Parser is used for parsing data from ESK363 job log lines. It has three methods to return date,
     * log level and the number of items which have been found during parse() method execution.
     * The methods of the class are used by LoadESK363DataServlet to move ESK363 job log records from files to database.
     */
    private String targetString;
    private String regExp;
    private List<String> list;

    private static final Logger logger = LogManager.getLogger(Parser.class);

    public void setTargetString(String targetString){
        /**
         * The method sets string which will be used by parse() method to search necessary fields.
         */
        this.targetString = targetString;
    }

    public void setRegExp(String regExp){
        /**
         *  The method sets RegExp string which will be used by parse() method to compare it with target string
         * to search necessary fields.
         */
        this.regExp = regExp;
    }

    public Date getDate(String format){
        /**
         * Returns Date object found in target log string.
         * Depends on kind of string.
         */
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        logger.debug("Line 52 - dateFormat: " + dateFormat);
        String stringDate = "";
        if (list.size() != 0){
            stringDate = list.get(0);
            logger.debug("Line 56 - stringDate" + stringDate);
            try {
                date = dateFormat.parse(stringDate);
            } catch (ParseException ex){
                logger.error(ApplicationDebugMessage.CANNOT_PARSE_DATE.getMessage(), stringDate);
            }
        }

        return date;
    }

    public String getLogLevel(){
        /**
         * Returns log level String object found in target log string.
         * Example:
         *  - INFO;
         *  - WARN;
         *  - ERROR;
         *  etc.
         *
         *  Depends on kind of string.
         */
        if (list.size() != 0)
            return list.get(9);

        return null;
    }

    public Integer getItemCount(){
        /**
         * Returns the number of items loaded at the given time moment.
         * Depend on kind of string.
         */
        if (list.size() != 0)
            return Integer.parseInt(list.get(11));
        return null;
    }

    public void parse(){
        /**
         *  The method compares given string with the given regular expression, divides it into parts and puts the parts
         * into the list.
         * Does not depend on kind of string.
         */
        this.list = new ArrayList<String>();
        final Matcher matcher = Pattern.compile(regExp).matcher(targetString);
        if (matcher.find()){
            for (int i = 1; i <= matcher.groupCount(); i++)
                list.add(matcher.group(i));
        }
    }
}
