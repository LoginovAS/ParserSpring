package org.sbx.spring.model.RecordImpl;

import org.sbx.enums.EnumDateFormat;
import org.sbx.file.builders.Buildable;
import org.sbx.spring.model.Record;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363DBRecord implements Record, Buildable {

    private int recordId;
    private Date recordDate;
    private String logLevel;
    private int itemCount;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        DateFormat format = new SimpleDateFormat(EnumDateFormat.INPUT.getFormat());
        stringBuilder.append(format.format(getRecordDate())).append(" ")
                                                            .append(getLogLevel())
                                                            .append(" - ")
                                                            .append(((Integer)getItemCount()).toString());

        return stringBuilder.toString();
    }
}