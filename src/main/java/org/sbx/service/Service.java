package org.sbx.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.sbx.enums.EnumDateFormat;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    public static JSONObject getJsonByRecordList(List<ESK363DBRecord> recordList){
        JSONObject topLevel = new JSONObject();
        JSONArray cols = new JSONArray();
        JSONArray rows = new JSONArray();

        // cols creation
        JSONObject colJsonObject = new JSONObject();
        colJsonObject.put("id", "");
        colJsonObject.put("label", "date");
        colJsonObject.put("type", "string");
        cols.add(colJsonObject);
        colJsonObject = new JSONObject();
        colJsonObject.put("id", "");
        colJsonObject.put("label", "items");
        colJsonObject.put("type", "number");
        cols.add(colJsonObject);

        // rows creation
        for (ESK363DBRecord record: recordList){
            JSONObject rowJsonObject = new JSONObject();
            JSONObject cellJsonObject = new JSONObject();
            JSONArray cellsJsonArray = new JSONArray();

            cellJsonObject.put("v", Service.dateToString(record.getRecordDate()));
            cellJsonObject.put("f", null);
            cellsJsonArray.add(cellJsonObject);
            cellJsonObject = new JSONObject();
            cellJsonObject.put("v", record.getItemCount());
            cellJsonObject.put("f", null);
            cellsJsonArray.add(cellJsonObject);

            rowJsonObject.put("c", cellsJsonArray);

            rows.add(rowJsonObject);
        }

        topLevel.put("rows", rows);
        topLevel.put("cols", cols);

        return topLevel;
    }
}
