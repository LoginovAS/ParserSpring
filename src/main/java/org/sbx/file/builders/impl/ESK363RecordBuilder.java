package org.sbx.file.builders.impl;

import org.sbx.file.builders.Builder;
import org.sbx.spring.model.RecordImpl.ESK363DBRecord;

import java.util.Date;

/**
 * Created by aloginov on 01.11.16.
 */
public class ESK363RecordBuilder implements Builder {

    private ESK363DBRecord record;
    private Date date;
    private int itemCount;
    private String logLevel;

    public ESK363RecordBuilder(){
        this.record = new ESK363DBRecord();
    }

    public void addDate(Date date){
        record.setRecordDate(date);
    }

    public void addItemCount(int itemCount){
        record.setItemCount(itemCount);
    }

    public void setLogLevel(String logLevel){
        record.setLogLevel(logLevel);
    }

    public ESK363DBRecord build() {
        return record;
    }

}
